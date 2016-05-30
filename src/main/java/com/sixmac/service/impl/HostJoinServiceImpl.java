package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.HostJoinDao;
import com.sixmac.entity.HostJoin;
import com.sixmac.service.HostJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/25 0025 上午 11:15.
 */
@Service
public class HostJoinServiceImpl implements HostJoinService {

    @Autowired
    private HostJoinDao hostJoinDao;

    @Override
    public List<HostJoin> findAll() {
        return hostJoinDao.findAll();
    }

    @Override
    public Page<HostJoin> find(int pageNum, int pageSize) {
        return hostJoinDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<HostJoin> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public HostJoin getById(int id) {
        return hostJoinDao.findOne(id);
    }

    @Override
    public HostJoin deleteById(int id) {
        HostJoin hostJoin = getById(id);
        hostJoinDao.delete(hostJoin);
        return hostJoin;
    }

    @Override
    public HostJoin create(HostJoin hostJoin) {
        return hostJoinDao.save(hostJoin);
    }

    @Override
    public HostJoin update(HostJoin hostJoin) {
        return hostJoinDao.save(hostJoin);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<HostJoin> findByHostRaceId(Integer raceId) {
        return hostJoinDao.findByHostRaceId(raceId);
    }
}