package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.VipLevelDao;
import com.sixmac.entity.VipLevel;
import com.sixmac.service.VipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 4:43.
 */
@Service
public class VipLevelServiceImpl implements VipLevelService {

    @Autowired
    private VipLevelDao vipLevelDao;

    @Override
    public List<VipLevel> findAll() {
        return vipLevelDao.findAll();
    }

    @Override
    public Page<VipLevel> find(int pageNum, int pageSize) {
        return vipLevelDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<VipLevel> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public VipLevel getById(int id) {
        return vipLevelDao.findOne(id);
    }

    @Override
    public VipLevel deleteById(int id) {
        VipLevel vipLevel = getById(id);
        vipLevelDao.delete(vipLevel);
        return vipLevel;
    }

    @Override
    public VipLevel create(VipLevel vipLevel) {
        return vipLevelDao.save(vipLevel);
    }

    @Override
    public VipLevel update(VipLevel vipLevel) {
        return vipLevelDao.save(vipLevel);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public VipLevel findBylevel(Integer level) {
        return vipLevelDao.findBylevel(level);
    }
}