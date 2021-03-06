package com.sixmac.dao;

import com.sixmac.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 5:59.
 */
public interface CityDao extends JpaRepository<City, Long> {

    @Query("select a from City a where a.cityId = ?1")
    public City getByCityId(Long cityId);

    @Query("select a from City a where a.provinceId = ?1")
    public List<City> getByProvinceId(Long provinceId);
}