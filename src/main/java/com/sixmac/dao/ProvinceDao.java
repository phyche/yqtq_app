package com.sixmac.dao;

import com.sixmac.entity.City;
import com.sixmac.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:00.
 */
public interface ProvinceDao extends JpaRepository<Province, Long> {

    @Query("select a from Province a where a.provinceId = ?1 ")
    public Province getByProvinceId(Long provinceId);
}