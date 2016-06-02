package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.ServiceMessageDao;
import com.sixmac.entity.ServiceMessage;
import com.sixmac.service.ServiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:44.
 */
@Service
public class ServiceMessageServiceImpl implements ServiceMessageService {

    @Autowired
    private ServiceMessageDao serviceMessageDao;

    @Override
    public List<ServiceMessage> findAll() {
        return serviceMessageDao.findAll();
    }

    @Override
    public Page<ServiceMessage> find(int pageNum, int pageSize) {
        return serviceMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<ServiceMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public ServiceMessage getById(int id) {
        return serviceMessageDao.findOne(id);
    }

    @Override
    public ServiceMessage deleteById(int id) {
        ServiceMessage serviceMessage = getById(id);
        serviceMessageDao.delete(serviceMessage);
        return serviceMessage;
    }

    @Override
    public ServiceMessage create(ServiceMessage serviceMessage) {
        return serviceMessageDao.save(serviceMessage);
    }

    @Override
    public ServiceMessage update(ServiceMessage serviceMessage) {
        return serviceMessageDao.save(serviceMessage);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}