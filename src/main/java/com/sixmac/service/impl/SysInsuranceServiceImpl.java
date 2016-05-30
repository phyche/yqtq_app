package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.SysInsuranceDao;
import com.sixmac.entity.SysInsurance;
import com.sixmac.service.SysInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 1:53.
 */
@Service
public class SysInsuranceServiceImpl implements SysInsuranceService {

    @Autowired
    private SysInsuranceDao sysInsuranceDao;

    @Override
    public List<SysInsurance> findAll() {
        return sysInsuranceDao.findAll();
    }

    @Override
    public Page<SysInsurance> find(int pageNum, int pageSize) {
        return sysInsuranceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<SysInsurance> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public SysInsurance getById(int id) {
        return sysInsuranceDao.findOne(id);
    }

    @Override
    public SysInsurance deleteById(int id) {
        SysInsurance insurance = getById(id);
        sysInsuranceDao.delete(insurance);
        return insurance;
    }

    @Override
    public SysInsurance create(SysInsurance insurance) {
        return sysInsuranceDao.save(insurance);
    }

    @Override
    public SysInsurance update(SysInsurance insurance) {
        return sysInsuranceDao.save(insurance);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}