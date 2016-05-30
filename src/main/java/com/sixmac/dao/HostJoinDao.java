package com.sixmac.dao;

import com.sixmac.entity.HostJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/25 0025 上午 11:13.
 */
public interface HostJoinDao extends JpaRepository<HostJoin, Integer>, JpaSpecificationExecutor<HostJoin> {

    //根据赛事筛选报名参加的球队
    @Query("select a from HostJoin a where a.hostRace.id = ?1 ")
    public List<HostJoin> findByHostRaceId(Integer raceId);

}