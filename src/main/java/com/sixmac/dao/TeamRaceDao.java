package com.sixmac.dao;

import com.sixmac.entity.TeamRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018 下午 4:46.
 */
public interface TeamRaceDao extends JpaRepository<TeamRace, Long> {

    /*//根据球队id筛选队赛
    @Query("select a from TeamRace a where a.teamId = ?1 ")
    public List<TeamRace> findByTeamId(Integer teamId);*/

    //根据主队id查询队赛
    @Query("select a from TeamRace a where a.homeTeam.id = ?1")
    public List<TeamRace> findHomeId(Long homeId);

    //根据客队id查询队赛
    @Query("select a from TeamRace a where a.visitingTeam.id = ?1")
    public List<TeamRace> findVisitingId(Long visitingId);

}