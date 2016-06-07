package com.sixmac.dao;

import com.sixmac.entity.MessageJoin;
import com.sixmac.entity.MessageOrderBall;
import com.sixmac.entity.MessageTeam;
import com.sixmac.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MessageTeamDao extends JpaRepository<MessageTeam, Integer> {

    //根据球队查询邀请加入球队的消息
    @Query("select a from MessageTeam a where a.team = ?1 and a.status != 0")
    public List<MessageTeam> findByTeam(Team team);

    //根据用户id查询被邀请加入球队的消息
    @Query("select a from MessageTeam a where a.toUser.id = ?1 and a.status = 0")
    public List<MessageTeam> findByToUserId(Integer userId);

}
