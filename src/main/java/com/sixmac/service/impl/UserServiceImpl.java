package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.CityDao;
import com.sixmac.dao.UserDao;
import com.sixmac.dao.UserOtherDao;
import com.sixmac.entity.User;
import com.sixmac.entity.UserOther;
import com.sixmac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017 下午 4:52.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserOtherDao userOtherDao;

    @Autowired
    private CityDao cityDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page<User> find(int pageNum, int pageSize) {
        return userDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<User> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public User getById(int id) {
        return userDao.findOne(id);
    }

    @Override
    public User deleteById(int id) {
        User user = getById(id);
        userDao.delete(user);
        return user;
    }

    @Override
    public User create(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    @Override
    public User iTLogin(Integer type, String openId, String head, String nickname) {
        UserOther userOther = userDao.iTLogin(openId, type);
        User user = null;
        // 如果第三方用户信息不存在，则执行注册操作
        if (null == userOther) {
            // 此处执行注册操作
            user = new User();
            user.setNickname(nickname);
            user.setAvater(head);
            user.setCityId(cityDao.findOne(1).getCityId());
            user.setCredibility(0);
            user.setStatus(0);
            user.setCreateDate(new Date().getTime());

            userDao.save(user);

            // 注册完毕后，添加该用户的第三方信息
            UserOther others = new UserOther();
            others.setType(type);
            others.setOpenId(openId);
            others.setUser(user);

            userOtherDao.save(others);
        } else {
            user = userOther.getUser();
        }

        return user;
    }

}