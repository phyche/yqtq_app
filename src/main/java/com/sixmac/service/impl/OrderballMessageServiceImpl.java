package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.OrderballMessageDao;
import com.sixmac.entity.OrderballMessage;
import com.sixmac.service.OrderballMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:43.
 */
@Service
public class OrderballMessageServiceImpl implements OrderballMessageService {

    @Autowired
    private OrderballMessageDao orderballMessageDao;

    @Override
    public List<OrderballMessage> findAll() {
        return orderballMessageDao.findAll();
    }

    @Override
    public Page<OrderballMessage> find(int pageNum, int pageSize) {
        return orderballMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<OrderballMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public OrderballMessage getById(Long id) {
        return orderballMessageDao.findOne(id);
    }

    @Override
    public OrderballMessage deleteById(Long id) {
        OrderballMessage orderballMessage = getById(id);
        orderballMessageDao.delete(orderballMessage);
        return orderballMessage;
    }

    @Override
    public OrderballMessage create(OrderballMessage orderballMessage) {
        return orderballMessageDao.save(orderballMessage);
    }

    @Override
    public OrderballMessage update(OrderballMessage orderballMessage) {
        return orderballMessageDao.save(orderballMessage);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}