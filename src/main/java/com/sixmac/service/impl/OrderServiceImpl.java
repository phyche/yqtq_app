package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.*;
import com.sixmac.entity.*;
import com.sixmac.pay.excute.PayRequest;
import com.sixmac.service.*;
import com.sixmac.utils.CommonUtils;
import com.sixmac.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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
    private SysExperienceDao sysExperienceDao;

    @Autowired
    private SysCredibilityDao sysCredibilityDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GirlUserDao girlUserDao;

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private UserReserveDao userReserveDao;

    @Autowired
    private VipLevelDao vipLevelDao;

    @Autowired
    private InsuranceDao insuranceDao;

    @Autowired
    private MessageRecordDao messageRecordDao;

    @Autowired
    private MessageOrderBallDao messageOrderBallDao;

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private SysInsuranceDao sysInsuranceDao;

    @Autowired
    private SiteTimeDao siteTimeDao;

    @Autowired
    private UserVipDao userVipDao;

    @Autowired
    private GirlDao girlDao;

    @Autowired
    private BigRaceDao bigRaceDao;

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
    @Transactional
    public void changeOrderStatus(String orderNum, Integer type) {
        Order orders = iFindOneByOrderNum(orderNum);
        orders.setStatus(Constant.ORDERS_STATUS_001);
        orders.setType(type);
        orders.setPayTime(new Date().getTime());

        if (orders.getAction() != 1) {
            User user = orders.getUser();
            user.setCredibility(user.getCredibility() + sysCredibilityDao.findByAction(orders.getAction()).getCredibility());
            user.setExperience(user.getExperience() + sysExperienceDao.findByAction(orders.getAction()).getExperience());

            userDao.save(user);
            List<VipLevel> levelList = vipLevelDao.findAll();
            for (VipLevel vipLevel : levelList) {
                if (user.getExperience() < vipLevel.getExperience()) {
                    user.setVipNum(vipLevel.getLevel() - 1);
                    userDao.save(user);
                }
            }
            //userDao.changeIntegral(user);
        }

        if (orders.getAction() == 1) {
            orders.getReserve().setJoinCount(orders.getReserve().getJoinCount() + 1);
            reserveDao.save(orders.getReserve());
            if (orders.getReserve().getJoinCount() == orders.getReserve().getMatchType() * 2) {

                List<UserReserve> userReserveList = userReserveDao.findByReserverId(orders.getReserve().getId());
                for (UserReserve userReserve : userReserveList) {

                    SysExperience sysExperience = sysExperienceDao.findByAction(1);
                    userReserve.getUser().setExperience(userReserve.getUser().getExperience() + sysExperience.getExperience());
                    SysCredibility sysCredibility = sysCredibilityDao.findByAction(1);
                    userReserve.getUser().setCredibility(userReserve.getUser().getCredibility() + sysCredibility.getCredibility());

                    List<VipLevel> levelList = vipLevelDao.findAll();
                    for (VipLevel vipLevel : levelList) {
                        if (userReserve.getUser().getExperience() < vipLevel.getExperience()) {
                            userReserve.getUser().setVipNum(vipLevel.getLevel() - 1);
                            userDao.save(userReserve.getUser());
                        }
                    }
                    //userService.changeIntegral(userReserve.getUser());
                    userDao.save(userReserve.getUser());
                }
            }

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(orders.getUser());
//            userReserve.setReserveId(orders.getReserve().getId());
            userReserve.setReserve(orders.getReserve());
            userReserve.setStatus(0);
            userReserveDao.save(userReserve);

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(orders.getReserve().getUser().getId());
            messageRecord.setStatus(0);
            messageRecord.setMessageId(userReserve.getId());
            messageRecord.setType(1);
            messageRecordDao.save(messageRecord);

            if (orders.getMessageId() != null) {
                MessageOrderBall messageOrderBall = messageOrderBallDao.findOne(orders.getMessageId());
                messageOrderBall.setStatus(1);
                messageOrderBallDao.save(messageOrderBall);
            }
        }

        Double preferente = 1.0;
        if (orders.getUser().getVipNum() != 0) {
            preferente = vipLevelDao.findBylevel(orders.getUser().getVipNum()).getPreferente();
        }
        if (orders.getAction() == 2 && orders.getReserve() != null) {

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(orders.getUser());
//            userReserve.setReserveId(orders.getReserve().getId());
            userReserve.setReserve(orders.getReserve());
            userReserve.setStatus(1);
            userReserveDao.save(userReserve);

            if (orders.getReserve().getInsurance() != null) {
                Insurance insurance = new Insurance();
                insurance.setUserId(orders.getUser().getId());
                insurance.setReserveId(orders.getReserve().getId());
                insurance.setSysInsurance(orders.getReserve().getInsurance());
                insurance.setInsuranceNum(orders.getInsuranceNum());
                insurance.setMoney(orders.getReserve().getInsurance().getPrice() * preferente);
                insuranceDao.save(insurance);
            }
        } /*else if (orders.getAction() == 2 && orders.getReserveTeam() != null && orders.getReserveTeam().getInsurance() != null) {
            Insurance insurance = new Insurance();
            insurance.setUserId(orders.getUser().getId());
            insurance.setNum(0);
            insurance.setReserveTeamId(orders.getReserveTeam().getId());
            insurance.setSysInsurance(orders.getReserveTeam().getInsurance());
            insurance.setMoney(orders.getReserveTeam().getInsurance().getPrice() * preferente);
            insuranceService.create(insurance);
        }*/

        if (orders.getAction() == 3) {
            orders.getGirlUser().setStatus(0);
            girlUserDao.save(orders.getGirlUser());
        }
    }

    @Override
    public List<Order> findByUserIdAndAction(Integer action, Long userId) {
        return orderDao.findByUserIdAndAction(action, userId);
    }

    @Override
    @Transactional
    public Order payConfirm(HttpServletResponse response, Long userId, Long siteId, Integer start, Long time, Integer end, Integer type, Long insuranceId, Integer status, Double price, Integer num) {
        String sn = CommonUtils.generateSn(); // 订单号

        Site site = siteDao.findOne(siteId);
        SiteTime siteTime = new SiteTime();
        siteTime.setSite(site);
        siteTime.setStartTime(time + start * 1000 * 3600);
        siteTime.setEndTime(time + end * 1000 * 3600);
        siteTimeDao.save(siteTime);

        //Reserve reserve = null;
        Reserve reserve = new Reserve();
        reserve.setStadium(site.getStadium());
        reserve.setSiteId(siteId);
        reserve.setUser(userDao.findOne(userId));
        if (insuranceId != null) {
            reserve.setInsurance(sysInsuranceDao.findOne(insuranceId));
        }
        if (type == 0) {
            //将球场预订表的支付方式设置为全额支付
            reserve.setPayment(0);
        } else if (type == 1) {
            //将球场预订表的支付方式设置为AA支付
            reserve.setPayment(1);
        }
        reserve.setPrice(price);
        reserve.setMatchType(site.getType());
        reserve.setStartTime(siteTime.getStartTime());
        reserve.setPayStatus(0);
        reserve.setCityId(site.getStadium().getCityId());
        reserve.setType(0);
        reserve.setReserveType(status);
        reserveDao.save(reserve);
        /*if (status == 0) {

        }else if (status == 1) {
            reserveTeam = new ReserveTeam();
            reserveTeam.setStatus(0);
            reserveTeam.setSite(site);
            reserveTeam.setUser(userService.getById(userId));
            reserveTeam.setStartTime(siteTime.getStartTime());
            if (insuranceId != null) {
                reserveTeam.setInsurance(sysInsuranceService.getById(insuranceId));
            }
            reserveTeam.setPrice(price);
            reserveTeamService.create(reserveTeam);
        }*/

        Order order = new Order();
        order.setUser(userDao.findOne(userId));
        //order.setStadium(reserve.getSite().getStadium());
        order.setReserve(reserve);
        //order.setReserveTeam(reserveTeam);
        order.setPrice(price);
        order.setAction(2);
        order.setSn(sn);
        order.setInsuranceNum(num);
        orderDao.save(order);

        return order;
    }

    @Override
    @Transactional
    public Order pay(HttpServletResponse response, Long userId, Integer num, Long endDate, Double price) throws ParseException {
        UserVip userVip = null;

        if (userVipDao.findByUserId(userId) == null) {
            userVip = new UserVip();
        } else {
            userVip = userVipDao.findByUserId(userId);
        }
        userVip.setUserId(userId);
        userVip.setDuration(num);
        if (num == 1) {
            if (userVip != null && userVip.getEndDate()!= null && userVip.getEndDate() >= System.currentTimeMillis()) {
                endDate = DateUtils.dateAddYear(DateUtils.longToDate(userVip.getEndDate(),"yyyy-MM-dd HH:mm:ss"),1).getTime();
            }else {
                endDate = DateUtils.dateAddYear(DateUtils.longToDate(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"),1).getTime();
            }

        } else if (num == 2) {
            if (userVip != null && userVip.getEndDate() >= System.currentTimeMillis()) {
                endDate = DateUtils.dateAddYear(DateUtils.longToDate(userVip.getEndDate(),"yyyy-MM-dd HH:mm:ss"),1).getTime();
            }else {
                endDate = DateUtils.dateAddYear(DateUtils.longToDate(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"),1).getTime();
            }
        } else if (num == 3) {
            if (userVip != null && userVip.getEndDate() >= System.currentTimeMillis()) {
                endDate = DateUtils.dateAddYear(DateUtils.longToDate(userVip.getEndDate(),"yyyy-MM-dd HH:mm:ss"),1).getTime();
            }else {
                endDate = DateUtils.dateAddYear(DateUtils.longToDate(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"),1).getTime();
            }
        }
        userVip.setEndDate(endDate);
        userVipDao.save(userVip);
        User user = userDao.findOne(userId);
        user.setEndDate(endDate);
        userDao.save(user);

        String sn = CommonUtils.generateSn(); // 订单号
        Order order = new Order();
        order.setUser(userDao.findOne(userId));
        order.setPrice(price);
        order.setSn(sn);
        orderDao.save(order);
        return order;
    }

    @Override
    @Transactional
    public Order orderGirl(HttpServletResponse response, Long userId, Long girlId, Long sceneId, Double tip) {
        GirlUser girlUser = new GirlUser();
        girlUser.setUserId(userId);
        girlUser.setGirl(girlDao.findOne(girlId));
        girlUser.setBigRace(bigRaceDao.findOne(sceneId));
        girlUser.setStartDate(bigRaceDao.findOne(sceneId).getStartDate());
        girlUser.setStadium(bigRaceDao.findOne(sceneId).getStadium());
        girlUser.setPrice(girlDao.findOne(girlId).getPrice());
        girlUser.setTip(tip);
        girlUser.setStatus(3);
        girlUserDao.save(girlUser);

        // 生成订单
        String sn = CommonUtils.generateSn(); // 订单号

        User user = userDao.findOne(girlUser.getUserId());
        Order order = new Order();
        order.setUser(user);
        //order.setStadium(girlUser.getStadium());
        order.setGirlUser(girlUser);

        VipLevel vipLevel = vipLevelDao.findBylevel(user.getVipNum());
        if (user.getVipNum() != 0) {
            order.setPrice((girlUser.getPrice() + girlUser.getTip()) * vipLevel.getPreferente());
        } else {
            order.setPrice(girlUser.getPrice() + girlUser.getTip());
        }
        order.setSn(sn);
        order.setAction(3);
        orderDao.save(order);

        return order;
    }
}