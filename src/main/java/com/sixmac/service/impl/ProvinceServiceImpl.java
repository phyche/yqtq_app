package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.AreaDao;
import com.sixmac.dao.CityDao;
import com.sixmac.dao.ProvinceDao;
import com.sixmac.entity.Area;
import com.sixmac.entity.City;
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

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AreaDao areaDao;

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
    public Province getById(Long id) {
        return provinceDao.findOne(id);
    }

    @Override
    public Province deleteById(Long id) {
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
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Province getByProvinceId(Long provinceId) {
        return provinceDao.getByProvinceId(provinceId);
    }

    @Override
    public List<Province> findList() {
        List<Province> list = provinceDao.findAll();
        for (Province province : list) {
            List<City> cityList = cityDao.getByProvinceId(province.getProvinceId());
            for (City city : cityList) {
                province.getCityList().add(city);
                List<Area> areaList = areaDao.getByCityId(city.getCityId());
                for (Area area : areaList) {
                    city.getAreaList().add(area);
                }
            }
        }
        return list;
    }
}