package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SiteManageDao;
import com.sixmac.entity.SiteManage;
import com.sixmac.service.SiteManageService;
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
public class SiteManageServiceImpl implements SiteManageService {

    @Autowired
    private SiteManageDao siteManageDao;

    @Override
    public List<SiteManage> findAll() {
        return siteManageDao.findAll();
    }

    @Override
    public Page<SiteManage> find(int pageNum, int pageSize) {
        return siteManageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SiteManage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public SiteManage getById(Long id) {
        return siteManageDao.findOne(id);
    }

    @Override
    public SiteManage deleteById(Long id) {
        SiteManage siteManage = getById(id);
        siteManageDao.delete(siteManage);
        return siteManage;
    }

    @Override
    public SiteManage create(SiteManage siteManage) {
        return siteManageDao.save(siteManage);
    }

    @Override
    public SiteManage update(SiteManage siteManage) {
        return siteManageDao.save(siteManage);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<SiteManage> findBySiteAndTime(Long siteId, Long time) {
        return siteManageDao.findBySiteAndTime(siteId,time);
    }
}