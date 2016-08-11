package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.TeamMemberDao;
import com.sixmac.entity.TeamMember;
import com.sixmac.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 9:43.
 */
@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Override
    public List<TeamMember> findAll() {
        return teamMemberDao.findAll();
    }

    @Override
    public Page<TeamMember> find(int pageNum, int pageSize) {
        return teamMemberDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<TeamMember> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public TeamMember getById(Long id) {
        return teamMemberDao.findOne(id);
    }

    @Override
    public TeamMember deleteById(Long id) {
        TeamMember teamMember = getById(id);
        teamMemberDao.delete(teamMember);
        return teamMember;
    }

    @Override
    public TeamMember create(TeamMember teamMember) {
        return teamMemberDao.save(teamMember);
    }

    @Override
    public TeamMember update(TeamMember teamMember) {
        return teamMemberDao.save(teamMember);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<TeamMember> findByUserId(Long userId) {
        return teamMemberDao.findByUserId(userId);
    }

    /*@Override
    public List<TeamMember> findByTeamId(Long teamId) {
        return teamMemberDao.findByTeamId(teamId);
    }*/
}