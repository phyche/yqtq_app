/*
package com.sixmac.dao;

import com.sixmac.entity.MessageJoin;
import com.sixmac.entity.MessageOrderBall;
import com.sixmac.entity.MessageTeam;
import com.sixmac.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

*/
/**
 * Created by Administrator on 2016/6/3 0003 下午 2:31.
 *//*

public interface MessageTeamDao extends JpaRepository<MessageTeam, Integer> {

    //根据主队查询球队约战消息
    @Query("select a from MessageTeam a where a.homeTeam = ?1 ")
    public List<MessageTeam> findByHomeTeam(Team team);

    //根据客队查询球队约战消息
    @Query("select a from MessageTeam a where a.visitingTeam = ?1 ")
    public List<MessageTeam> findByVisitingTeam(Team team);
}*/
