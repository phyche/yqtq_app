package com.sixmac.dao;

import com.sixmac.entity.SiteManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006 上午 10:35.
 */
public interface SiteManageDao extends JpaRepository<SiteManage, Long> {

    @Query("select a from SiteManage a where a.site.id = ?1 and a.startTime <= ?2 and a.endTime >= ?2 order by a.startTime asc")
    public List<SiteManage> findBySiteAndTime(Long siteId, Long time);
}