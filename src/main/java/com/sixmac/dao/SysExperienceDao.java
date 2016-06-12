package com.sixmac.dao;

import com.sixmac.entity.SysExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/12 0012 上午 11:44.
 */
public interface SysExperienceDao extends JpaRepository<SysExperience, Long> {

    @Query("select a from SysExperience a where a.action = ?1 ")
    public SysExperience findByAction(Integer action);
}