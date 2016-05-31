package com.sixmac.dao;

import com.sixmac.entity.EventInformation;
import com.sixmac.entity.GirlComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 4:08.
 */
public interface EventInformationDao extends JpaRepository<EventInformation, Integer>, JpaSpecificationExecutor<EventInformation> {

    //根据赛事id查询赛事资讯
    @Query("select a from EventInformation a where a.hostRace.id = ?1 ")
    public EventInformation findByRaceId(Integer raceId);
}