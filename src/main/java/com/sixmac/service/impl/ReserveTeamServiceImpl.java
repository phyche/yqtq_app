package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.ReserveTeamDao;
import com.sixmac.entity.ReserveTeam;
import com.sixmac.service.ReserveTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 6:30.
 */
@Service
public class ReserveTeamServiceImpl implements ReserveTeamService {

    @Autowired
    private ReserveTeamDao reserveTeamDao;

    @Override
    public List<ReserveTeam> findAll() {
        return reserveTeamDao.findAll();
    }

    @Override
    public Page<ReserveTeam> find(int pageNum, int pageSize) {
        return reserveTeamDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<ReserveTeam> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public ReserveTeam getById(Long id) {
        return reserveTeamDao.findOne(id);
    }

    @Override
    public ReserveTeam deleteById(Long id) {
        ReserveTeam reserveTeam = getById(id);
        reserveTeamDao.delete(reserveTeam);
        return reserveTeam;
    }

    @Override
    public ReserveTeam create(ReserveTeam reserveTeam) {
        return reserveTeamDao.save(reserveTeam);
    }

    @Override
    public ReserveTeam update(ReserveTeam reserveTeam) {
        return reserveTeamDao.save(reserveTeam);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}