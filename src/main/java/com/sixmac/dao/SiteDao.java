package com.sixmac.dao;

import com.sixmac.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 3:51.
 */
public interface SiteDao extends JpaRepository<Site, Long>, JpaSpecificationExecutor<Site> {

    @Query("select a from Site a where a.stadium.id = ?1 ")
    public List<Site> findByStadiumId(Long stadiumId);

    @Query("select a from Site a where a.type = ?1 and a.stadium.id != null ")
    public List<Site> findByType(Integer type);
}