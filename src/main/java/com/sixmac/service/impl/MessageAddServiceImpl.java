package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.MessageAddDao;
import com.sixmac.entity.MessageAdd;
import com.sixmac.service.MessageAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 2:59.
 */
@Service
public class MessageAddServiceImpl implements MessageAddService {

    @Autowired
    private MessageAddDao messageAddDao;

    @Override
    public List<MessageAdd> findAll() {
        return messageAddDao.findAll();
    }

    @Override
    public Page<MessageAdd> find(int pageNum, int pageSize) {
        return messageAddDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<MessageAdd> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public MessageAdd getById(Long id) {
        return messageAddDao.findOne(id);
    }

    @Override
    public MessageAdd deleteById(Long id) {
        MessageAdd messageAdd = getById(id);
        messageAddDao.delete(messageAdd);
        return messageAdd;
    }

    @Override
    public MessageAdd create(MessageAdd messageAdd) {
        return messageAddDao.save(messageAdd);
    }

    @Override
    public MessageAdd update(MessageAdd messageAdd) {
        return messageAddDao.save(messageAdd);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<MessageAdd> findByUserId(Long userId) {
        return messageAddDao.findByUserId(userId);
    }

    @Override
    public List<MessageAdd> findByToUserId(Long userId) {
        return messageAddDao.findByToUserId(userId);
    }

    @Override
    public List<MessageAdd> findUserId(Long userId) {
        return messageAddDao.findUserId(userId);
    }
}