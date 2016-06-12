package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.MessageOrderBallDao;
import com.sixmac.entity.MessageOrderBall;
import com.sixmac.service.MessageOrderBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:40.
 */
@Service
public class MessageOrderBallServiceImpl implements MessageOrderBallService {

    @Autowired
    private MessageOrderBallDao messageOrderBallDao;

    @Override
    public List<MessageOrderBall> findAll() {
        return messageOrderBallDao.findAll();
    }

    @Override
    public Page<MessageOrderBall> find(int pageNum, int pageSize) {
        return messageOrderBallDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<MessageOrderBall> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public MessageOrderBall getById(Long id) {
        return messageOrderBallDao.findOne(id);
    }

    @Override
    public MessageOrderBall deleteById(Long id) {
        MessageOrderBall messageOrderBall = getById(id);
        messageOrderBallDao.delete(messageOrderBall);
        return messageOrderBall;
    }

    @Override
    public MessageOrderBall create(MessageOrderBall messageOrderBall) {
        return messageOrderBallDao.save(messageOrderBall);
    }

    @Override
    public MessageOrderBall update(MessageOrderBall messageOrderBall) {
        return messageOrderBallDao.save(messageOrderBall);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<MessageOrderBall> findByToUserId(Long userId) {
        return messageOrderBallDao.findByToUserId(userId);
    }
}