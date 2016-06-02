package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.ProvinceDao;
import com.sixmac.entity.Province;
import com.sixmac.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:01.
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public Page<Province> find(int pageNum, int pageSize) {
        return provinceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Province> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Province getById(int id) {
        return provinceDao.findOne(id);
    }

    @Override
    public Province deleteById(int id) {
        Province province = getById(id);
        provinceDao.delete(province);
        return province;
    }

    @Override
    public Province create(Province province) {
        return provinceDao.save(province);
    }

    @Override
    public Province update(Province province) {
        return provinceDao.save(province);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Province getByProvinceId(Integer provinceId) {
        return provinceDao.getByProvinceId(provinceId);
    }
}