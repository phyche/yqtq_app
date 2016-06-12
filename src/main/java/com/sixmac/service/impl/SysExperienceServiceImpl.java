package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SysExperienceDao;
import com.sixmac.entity.SysExperience;
import com.sixmac.service.SysExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12 0012 上午 11:44.
 */
@Service
public class SysExperienceServiceImpl implements SysExperienceService {

    @Autowired
    private SysExperienceDao sysExperienceDao;

    @Override
    public List<SysExperience> findAll() {
        return sysExperienceDao.findAll();
    }

    @Override
    public Page<SysExperience> find(int pageNum, int pageSize) {
        return sysExperienceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SysExperience> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public SysExperience getById(Long id) {
        return sysExperienceDao.findOne(id);
    }

    @Override
    public SysExperience deleteById(Long id) {
        SysExperience sysExperience = getById(id);
        sysExperienceDao.delete(sysExperience);
        return sysExperience;
    }

    @Override
    public SysExperience create(SysExperience sysExperience) {
        return sysExperienceDao.save(sysExperience);
    }

    @Override
    public SysExperience update(SysExperience sysExperience) {
        return sysExperienceDao.save(sysExperience);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public SysExperience findByAction(Integer action) {
        return sysExperienceDao.findByAction(action);
    }
}