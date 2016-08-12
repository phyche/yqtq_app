package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_team_member")
public class TeamMember extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "team_id")
    private Long teamId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
