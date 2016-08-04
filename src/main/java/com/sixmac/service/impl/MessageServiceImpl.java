package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.MessageDao;
import com.sixmac.entity.Message;
import com.sixmac.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:42.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public Page<Message> find(int pageNum, int pageSize) {
        return messageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Message> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Message getById(Long id) {
        return messageDao.findOne(id);
    }

    @Override
    public Message deleteById(Long id) {
        Message message = getById(id);
        messageDao.delete(message);
        return message;
    }

    @Override
    public Message create(Message message) {
        return messageDao.save(message);
    }

    @Override
    public Message update(Message message) {
        return messageDao.save(message);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Message getByType(Integer type) {
        return messageDao.getByType(type);
    }
}