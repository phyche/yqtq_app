package com.sixmac.dao;

import com.sixmac.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 3:51.
 */
public interface SiteDao extends JpaRepository<Site, Integer>, JpaSpecificationExecutor<Site> {

    @Query("select a from GirlUser a where a.stadium.id = ?1 ")
    public List<Site> findByStadiumId(Integer stadiumId);
}