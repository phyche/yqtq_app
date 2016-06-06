package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SiteTimeDao;
import com.sixmac.entity.SiteTime;
import com.sixmac.service.SiteTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006 上午 10:35.
 */
@Service
public class SiteTimeServiceImpl implements SiteTimeService {

    @Autowired
    private SiteTimeDao siteTimeDao;

    @Override
    public List<SiteTime> findAll() {
        return siteTimeDao.findAll();
    }

    @Override
    public Page<SiteTime> find(int pageNum, int pageSize) {
        return siteTimeDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SiteTime> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public SiteTime getById(int id) {
        return siteTimeDao.findOne(id);
    }

    @Override
    public SiteTime deleteById(int id) {
        SiteTime siteTime = getById(id);
        siteTimeDao.delete(siteTime);
        return siteTime;
    }

    @Override
    public SiteTime create(SiteTime siteTime) {
        return siteTimeDao.save(siteTime);
    }

    @Override
    public SiteTime update(SiteTime siteTime) {
        return siteTimeDao.save(siteTime);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<SiteTime> findBySiteId(Integer siteId) {
        return siteTimeDao.findBySiteId(siteId);
    }

    @Override
    public SiteTime findBySiteAndTime(Integer siteId, Long time) {
        return siteTimeDao.findBySiteAndTime(siteId,time);
    }
}