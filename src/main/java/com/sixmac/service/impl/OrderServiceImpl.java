package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.OrderDao;
import com.sixmac.entity.Order;
import com.sixmac.pay.excute.PayRequest;
import com.sixmac.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24 0024 下午 5:54.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

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
    public Order getById(int id) {
        return orderDao.findOne(id);
    }

    @Override
    public Order deleteById(int id) {
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
    public void deleteAll(int[] ids) {
        for (int id : ids) {
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
}