package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.CityDao;
import com.sixmac.entity.City;
import com.sixmac.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:00.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public Page<City> find(int pageNum, int pageSize) {
        return cityDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<City> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public City getById(Long id) {
        return cityDao.findOne(id);
    }

    @Override
    public City deleteById(Long id) {
        City city = getById(id);
        cityDao.delete(city);
        return city;
    }

    @Override
    public City create(City city) {
        return cityDao.save(city);
    }

    @Override
    public City update(City city) {
        return cityDao.save(city);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public City getByCityId(Long cityId) {
        return cityDao.getByCityId(cityId);
    }
}