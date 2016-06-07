package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.UserOtherDao;
import com.sixmac.entity.UserOther;
import com.sixmac.service.UserOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007 上午 10:28.
 */
@Service
public class UserOtherServiceImpl implements UserOtherService {

    @Autowired
    private UserOtherDao userOtherDao;

    @Override
    public List<UserOther> findAll() {
        return userOtherDao.findAll();
    }

    @Override
    public Page<UserOther> find(int pageNum, int pageSize) {
        return userOtherDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<UserOther> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public UserOther getById(int id) {
        return userOtherDao.findOne(id);
    }

    @Override
    public UserOther deleteById(int id) {
        UserOther userOther = getById(id);
        userOtherDao.delete(userOther);
        return userOther;
    }

    @Override
    public UserOther create(UserOther userOther) {
        return userOtherDao.save(userOther);
    }

    @Override
    public UserOther update(UserOther userOther) {
        return userOtherDao.save(userOther);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}