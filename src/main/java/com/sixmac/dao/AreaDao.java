package com.sixmac.dao;

import com.sixmac.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 5:59.
 */
public interface AreaDao extends JpaRepository<Area, Long> {

    @Query("select a from Area a where a.areaId = ?1 ")
    public Area getByAreaId(Long areaId);

    @Query("select a from Area a where a.cityId = ?1 ")
    public List<Area> getByCityId(Long cityId);
}