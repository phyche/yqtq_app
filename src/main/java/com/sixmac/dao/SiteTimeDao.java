package com.sixmac.dao;

import com.sixmac.entity.SiteTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006 上午 10:34.
 */
public interface SiteTimeDao extends JpaRepository<SiteTime, Integer> {

    @Query("select a from SiteTime a where a.site.id = ?1 ")
    public List<SiteTime> findBySiteId(Integer siteId);

    @Query("select a from SiteTime a where a.site.id = ?1 and a.startTime <= ?2 and a.endTime >= ?2 ")
    public SiteTime findBySiteAndTime(Integer siteId, Long time);
}