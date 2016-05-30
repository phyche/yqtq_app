package com.sixmac.service.impl;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.Constant;
import com.sixmac.dao.UserReserveDao;
import com.sixmac.entity.UserReserve;
import com.sixmac.service.UserReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020 上午 10:10.
 */
@Service
public class UserReserveServiceImpl implements UserReserveService {

    @Autowired
    private UserReserveDao userReserveDao;

    @Override
    public List<UserReserve> findAll() {
        return userReserveDao.findAll();
    }

    @Override
    public Page<UserReserve> find(int pageNum, int pageSize) {
        return userReserveDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<UserReserve> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public UserReserve getById(int id) {
        return userReserveDao.findOne(id);
    }

    @Override
    public UserReserve deleteById(int id) {
        UserReserve userReserve = getById(id);
        userReserveDao.delete(userReserve);
        return userReserve;
    }

    @Override
    public UserReserve create(UserReserve userReserve) {
        return userReserveDao.save(userReserve);
    }

    @Override
    public UserReserve update(UserReserve userReserve) {
        return userReserveDao.save(userReserve);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<UserReserve> findByReserverId(Integer reserveId) {
        return userReserveDao.findByReserverId(reserveId);
    }

    @Override
    public List<UserReserve> findByUserId(Integer userId) {
        return userReserveDao.findByUserId(userId);
    }
}