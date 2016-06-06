/*
package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.MessageTeamDao;
import com.sixmac.entity.MessageTeam;
import com.sixmac.entity.Team;
import com.sixmac.service.MessageTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

*/
/**
 * Created by Administrator on 2016/6/3 0003 下午 2:31.
 *//*

@Service
public class MessageTeamServiceImpl implements MessageTeamService {

    @Autowired
    private MessageTeamDao messageTeamDao;

    @Override
    public List<MessageTeam> findAll() {
        return messageTeamDao.findAll();
    }

    @Override
    public Page<MessageTeam> find(int pageNum, int pageSize) {
        return messageTeamDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<MessageTeam> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public MessageTeam getById(int id) {
        return messageTeamDao.findOne(id);
    }

    @Override
    public MessageTeam deleteById(int id) {
        MessageTeam messageTeam = getById(id);
        messageTeamDao.delete(messageTeam);
        return messageTeam;
    }

    @Override
    public MessageTeam create(MessageTeam messageTeam) {
        return messageTeamDao.save(messageTeam);
    }

    @Override
    public MessageTeam update(MessageTeam messageTeam) {
        return messageTeamDao.save(messageTeam);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<MessageTeam> findByHomeTeam(Team team) {
        return messageTeamDao.findByHomeTeam(team);
    }

    @Override
    public List<MessageTeam> findByVisitingTeam(Team team) {
        return messageTeamDao.findByVisitingTeam(team);
    }
}*/
