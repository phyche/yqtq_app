package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.MessageJoinDao;
import com.sixmac.entity.MessageJoin;
import com.sixmac.entity.Team;
import com.sixmac.service.MessageJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003 下午 2:34.
 */
@Service
public class MessageJoinServiceImpl implements MessageJoinService {

    @Autowired
    private MessageJoinDao messageJoinDao;

    @Override
    public List<MessageJoin> findAll() {
        return messageJoinDao.findAll();
    }

    @Override
    public Page<MessageJoin> find(int pageNum, int pageSize) {
        return messageJoinDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<MessageJoin> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public MessageJoin getById(Long id) {
        return messageJoinDao.findOne(id);
    }

    @Override
    public MessageJoin deleteById(Long id) {
        MessageJoin messageJoin = getById(id);
        messageJoinDao.delete(messageJoin);
        return messageJoin;
    }

    @Override
    public MessageJoin create(MessageJoin messageJoin) {
        return messageJoinDao.save(messageJoin);
    }

    @Override
    public MessageJoin update(MessageJoin messageJoin) {
        return messageJoinDao.save(messageJoin);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<MessageJoin> findByTeam(Team team) {
        return messageJoinDao.findByTeam(team);
    }
}