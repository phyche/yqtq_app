package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SiteDao;
import com.sixmac.entity.Site;
import com.sixmac.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 3:53.
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;

    @Override
    public List<Site> findAll() {
        return siteDao.findAll();
    }

    @Override
    public Page<Site> find(int pageNum, int pageSize) {
        return siteDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Site> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Site getById(int id) {
        return siteDao.findOne(id);
    }

    @Override
    public Site deleteById(int id) {
        Site site = getById(id);
        siteDao.delete(site);
        return site;
    }

    @Override
    public Site create(Site site) {
        return siteDao.save(site);
    }

    @Override
    public Site update(Site site) {
        return siteDao.save(site);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<Site> findByStadiumId(Integer stadiumId) {
        return siteDao.findByStadiumId(stadiumId);
    }
}