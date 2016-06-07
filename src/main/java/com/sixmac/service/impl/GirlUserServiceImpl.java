package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlUserDao;
import com.sixmac.entity.GirlUser;
import com.sixmac.service.GirlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 2:30.
 */
@Service
public class GirlUserServiceImpl implements GirlUserService {

    @Autowired
    private GirlUserDao girlUserDao;

    @Override
    public List<GirlUser> findAll() {
        return girlUserDao.findAll();
    }

    @Override
    public Page<GirlUser> find(int pageNum, int pageSize) {
        return girlUserDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<GirlUser> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public GirlUser getById(Long id) {
        return girlUserDao.findOne(id);
    }

    @Override
    public GirlUser deleteById(Long id) {
        GirlUser girlUser = getById(id);
        girlUserDao.delete(girlUser);
        return girlUser;
    }

    @Override
    public GirlUser create(GirlUser girlUser) {
        return girlUserDao.save(girlUser);
    }

    @Override
    public GirlUser update(GirlUser girlUser) {
        return girlUserDao.save(girlUser);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<GirlUser> findByUserId(Long userId) {
        return girlUserDao.findByUserId(userId);
    }

    @Override
    public List<GirlUser> findByGirlId(Long girlId) {
        return girlUserDao.findByGirlId(girlId);
    }

}