package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.OrderDao;
import com.sixmac.entity.*;
import com.sixmac.pay.excute.PayRequest;
import com.sixmac.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24 0024 下午 5:54.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SysExperienceService sysExperienceService;

    @Autowired
    private SysCredibilityService sysCredibilityService;

    @Autowired
    private UserService userService;

    @Autowired
    private GirlUserService girlUserService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private UserReserveService userReserveService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private InsuranceService insuranceService;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Page<Order> find(int pageNum, int pageSize) {
        return orderDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Order> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.findOne(id);
    }

    @Override
    public Order deleteById(Long id) {
        Order order = getById(id);
        orderDao.delete(order);
        return order;
    }

    @Override
    public Order create(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderDao.save(order);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Map<String, Object> getPayInfo(HttpServletRequest request, HttpServletResponse response, String orderNum) {
        Order orders = orderDao.iFindOneByOrderNum(orderNum);

        // 微信
        String prepayid = null; //预支付款ID
        request.setAttribute("fee", orders.getPrice());
        request.setAttribute("sn", orderNum);
        request.setAttribute("prepayid", prepayid);
        Map<String, Object> params = PayRequest.pay(request, response);

        return params;
    }

    @Override
    public Order iFindOneByOrderNum(String orderNum) {
        return orderDao.iFindOneByOrderNum(orderNum);
    }

    @Override
    public void changeOrderStatus(String orderNum, Integer type) {
        Order orders = iFindOneByOrderNum(orderNum);
        orders.setStatus(Constant.ORDERS_STATUS_001);
        orders.setType(type);
        orders.setPayTime(new Date().getTime());

        if (orders.getAction() != 1) {
            User user = orders.getUser();
            user.setCredibility(user.getCredibility() + sysCredibilityService.findByAction(orders.getAction()).getCredibility());
            user.setExperience(user.getExperience() + sysExperienceService.findByAction(orders.getAction()).getExperience());

            userService.update(user);
            userService.changeIntegral(user);
        }

        if (orders.getAction() == 1) {
            orders.getReserve().setJoinCount(orders.getReserve().getJoinCount() + 1);
            reserveService.update(orders.getReserve());
            if (orders.getReserve().getJoinCount() == orders.getReserve().getMatchType() * 2) {

                List<UserReserve> userReserveList = userReserveService.findByReserverId(orders.getReserve().getId());
                for (UserReserve userReserve : userReserveList) {

                    SysExperience sysExperience = sysExperienceService.findByAction(1);
                    userReserve.getUser().setExperience(userReserve.getUser().getExperience() + sysExperience.getExperience());
                    SysCredibility sysCredibility = sysCredibilityService.findByAction(1);
                    userReserve.getUser().setCredibility(userReserve.getUser().getCredibility() + sysCredibility.getCredibility());

                    userService.changeIntegral(userReserve.getUser());
                    userService.update(userReserve.getUser());
                }
            }

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(orders.getUser());
            userReserve.setReserveId(orders.getReserve().getId());
            //userReserve.setReserve(reserve);
            userReserve.setStatus(0);
            userReserveService.create(userReserve);
        }

        Double preferente = 1.0;
        if (orders.getUser().getVipNum() != 0) {
            preferente = vipLevelService.findBylevel(orders.getUser().getVipNum()).getPreferente();
        }
        if (orders.getAction() == 2 && orders.getReserve() != null) {

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(orders.getUser());
            userReserve.setReserveId(orders.getReserve().getId());
            //userReserve.setReserve(reserve);
            userReserve.setStatus(1);
            userReserveService.create(userReserve);

            if (orders.getReserve().getInsurance() != null) {
                Insurance insurance = new Insurance();
                insurance.setUserId(orders.getUser().getId());
                insurance.setReserveId(orders.getReserve().getId());
                insurance.setSysInsurance(orders.getReserve().getInsurance());

                insurance.setMoney(orders.getReserve().getInsurance().getPrice() * preferente);
                insuranceService.create(insurance);
            }
        } else if (orders.getAction() == 2 && orders.getReserveTeam() != null && orders.getReserveTeam().getInsurance() != null) {
            Insurance insurance = new Insurance();
            insurance.setUserId(orders.getUser().getId());
            insurance.setReserveTeamId(orders.getReserveTeam().getId());
            insurance.setSysInsurance(orders.getReserveTeam().getInsurance());
            insurance.setMoney(orders.getReserveTeam().getInsurance().getPrice() * preferente);
            insuranceService.create(insurance);
        }

        if (orders.getAction() == 3) {
            orders.getGirlUser().setStatus(0);
            girlUserService.update(orders.getGirlUser());
        }
    }
}