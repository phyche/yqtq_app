package com.sixmac.dao;

import com.sixmac.entity.Reserve;
import com.sixmac.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 9:41.
 */
public interface TeamMemberDao extends JpaRepository<TeamMember, Long> {

    //根据用户id筛选球队成员
    @Query("select a from TeamMember a where a.user.id = ?1 ")
    public List<TeamMember> findByUserId(Long userId);

    //根据球队id筛选球队成员
    @Query("select a from TeamMember a where a.team.id = ?1 ")
    public List<TeamMember> findByTeamId(Long teamId);
}