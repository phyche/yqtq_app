package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.OrderDao;
import com.sixmac.entity.Order;
import com.sixmac.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}