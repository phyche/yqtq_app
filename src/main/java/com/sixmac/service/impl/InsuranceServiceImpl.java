package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.InsuranceDao;
import com.sixmac.entity.Insurance;
import com.sixmac.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 上午 10:37.
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceDao insuranceDao;

    @Override
    public List<Insurance> findAll() {
        return insuranceDao.findAll();
    }

    @Override
    public Page<Insurance> find(int pageNum, int pageSize) {
        return insuranceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Insurance> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Insurance getById(int id) {
        return insuranceDao.findOne(id);
    }

    @Override
    public Insurance deleteById(int id) {
        Insurance insurance = getById(id);
        insuranceDao.delete(insurance);
        return insurance;
    }

    @Override
    public Insurance create(Insurance insurance) {
        return insuranceDao.save(insurance);
    }

    @Override
    public Insurance update(Insurance insurance) {
        return insuranceDao.save(insurance);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}