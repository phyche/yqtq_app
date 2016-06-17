package com.sixmac.service;

import com.sixmac.entity.City;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:00.
 */
public interface CityService extends ICommonService<City> {

    public City getByCityId(Long cityId);

    public List<City> getByProvinceId(Long provinceId);
}