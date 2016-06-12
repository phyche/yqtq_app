package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.UserVipDao;
import com.sixmac.entity.UserVip;
import com.sixmac.service.UserVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12 0012 上午 10:07.
 */
@Service
public class UserVipServiceImpl implements UserVipService {

    @Autowired
    private UserVipDao userVipDao;

    @Override
    public List<UserVip> findAll() {
        return userVipDao.findAll();
    }

    @Override
    public Page<UserVip> find(int pageNum, int pageSize) {
        return userVipDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<UserVip> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public UserVip getById(Long id) {
        return userVipDao.findOne(id);
    }

    @Override
    public UserVip deleteById(Long id) {
        UserVip userVip = getById(id);
        userVipDao.delete(userVip);
        return userVip;
    }

    @Override
    public UserVip create(UserVip userVip) {
        return userVipDao.save(userVip);
    }

    @Override
    public UserVip update(UserVip userVip) {
        return userVipDao.save(userVip);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public UserVip findByUserId(Long userId) {
        return userVipDao.findByUserId(userId);
    }
}