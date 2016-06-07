package com.sixmac.dao;

import com.sixmac.entity.MessageJoin;
import com.sixmac.entity.MessageTeam;
import com.sixmac.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003 下午 2:33.
 */
public interface MessageJoinDao extends JpaRepository<MessageJoin, Long> {

    //根据球队查询等待用户处理的申请加入球队消息
    @Query("select a from MessageJoin a where a.team = ?1 and a.status = 0 ")
    public List<MessageJoin> findByTeam(Team team);
}