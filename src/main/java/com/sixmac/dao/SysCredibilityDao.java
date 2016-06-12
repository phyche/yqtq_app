package com.sixmac.dao;

import com.sixmac.entity.SysCredibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/12 0012 上午 11:48.
 */
public interface SysCredibilityDao extends JpaRepository<SysCredibility, Long> {

    @Query("select a from SysCredibility a where a.action = ?1 ")
    public SysCredibility findByAction(Integer action);
}