package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SystemMessageDao;
import com.sixmac.entity.SystemMessage;
import com.sixmac.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 5:59.
 */
@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    private SystemMessageDao systemMessageDao;

    @Override
    public List<SystemMessage> findAll() {
        return systemMessageDao.findAll();
    }

    @Override
    public Page<SystemMessage> find(int pageNum, int pageSize) {
        return systemMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SystemMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public SystemMessage getById(int id) {
        return systemMessageDao.findOne(id);
    }

    @Override
    public SystemMessage deleteById(int id) {
        SystemMessage systemMessage = getById(id);
        systemMessageDao.delete(systemMessage);
        return systemMessage;
    }

    @Override
    public SystemMessage create(SystemMessage systemMessage) {
        return systemMessageDao.save(systemMessage);
    }

    @Override
    public SystemMessage update(SystemMessage systemMessage) {
        return systemMessageDao.save(systemMessage);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<SystemMessage> findByToUserId(Integer userId) {
        return systemMessageDao.findByToUserId(userId);
    }
}