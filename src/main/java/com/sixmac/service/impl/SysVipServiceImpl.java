package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SysVipDao;
import com.sixmac.entity.SysVip;
import com.sixmac.service.SysVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012 12:07.
 */
@Service
public class SysVipServiceImpl implements SysVipService {

    @Autowired
    private SysVipDao sysVipDao;

    @Override
    public List<SysVip> findAll() {
        return sysVipDao.findAll();
    }

    @Override
    public Page<SysVip> find(int pageNum, int pageSize) {
        return sysVipDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SysVip> find(int pageNum) {
        return null;
    }

    @Override
    public SysVip getById(Long id) {
        return sysVipDao.findOne(id);
    }

    @Override
    public SysVip deleteById(Long id) {
        SysVip sysVip = getById(id);
        sysVipDao.delete(sysVip);
        return sysVip;
    }

    @Override
    public SysVip create(SysVip sysVip) {
        return sysVipDao.save(sysVip);
    }

    @Override
    public SysVip update(SysVip sysVip) {
        return sysVipDao.save(sysVip);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}