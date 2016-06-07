package com.sixmac.service.impl;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.Constant;
import com.sixmac.dao.HostRaceDao;
import com.sixmac.entity.HostRace;
import com.sixmac.entity.Reserve;
import com.sixmac.service.HostRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 11:21.
 */
@Service
public class HostRaceServiceImpl implements HostRaceService {

    @Autowired
    private HostRaceDao hostRaceDao;

    @Override
    public List<HostRace> findAll() {
        return hostRaceDao.findAll();
    }

    @Override
    public Page<HostRace> find(int pageNum, int pageSize) {
        return hostRaceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<HostRace> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public HostRace getById(Long id) {
        return hostRaceDao.findOne(id);
    }

    @Override
    public HostRace deleteById(Long id) {
        HostRace hostRace = getById(id);
        hostRaceDao.delete(hostRace);
        return hostRace;
    }

    @Override
    public HostRace create(HostRace hostRace) {
        return hostRaceDao.save(hostRace);
    }

    @Override
    public HostRace update(HostRace hostRace) {
        return hostRaceDao.save(hostRace);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

}