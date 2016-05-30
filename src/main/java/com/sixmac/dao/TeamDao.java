package com.sixmac.dao;

import com.sixmac.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018 上午 9:55.
 */
public interface TeamDao extends JpaRepository<Team, Integer>, JpaSpecificationExecutor<Team> {

    @Query("select a from Team a where a.leaderUser.id = ?1 ")
    public Team findListByLeaderId(Integer leaderId);
}