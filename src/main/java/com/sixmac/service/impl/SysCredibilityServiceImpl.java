package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SysCredibilityDao;
import com.sixmac.entity.SysCredibility;
import com.sixmac.service.SysCredibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12 0012 上午 11:48.
 */
@Service
public class SysCredibilityServiceImpl implements SysCredibilityService {

    @Autowired
    private SysCredibilityDao sysCredibilityDao;

    @Override
    public List<SysCredibility> findAll() {
        return sysCredibilityDao.findAll();
    }

    @Override
    public Page<SysCredibility> find(int pageNum, int pageSize) {
        return sysCredibilityDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SysCredibility> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public SysCredibility getById(Long id) {
        return sysCredibilityDao.findOne(id);
    }

    @Override
    public SysCredibility deleteById(Long id) {
        SysCredibility sysCredibility = getById(id);
        sysCredibilityDao.delete(sysCredibility);
        return sysCredibility;
    }

    @Override
    public SysCredibility create(SysCredibility sysCredibility) {
        return sysCredibilityDao.save(sysCredibility);
    }

    @Override
    public SysCredibility update(SysCredibility sysCredibility) {
        return sysCredibilityDao.save(sysCredibility);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public SysCredibility findByAction(Integer action) {
        return sysCredibilityDao.findByAction(action);
    }
}