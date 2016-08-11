package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
@Entity
@Table(name = "t_host_race_join")
public class HostJoin extends BaseEntity{

    @Column(name = "host_race_id")
    private Long hostRaceId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getHostRaceId() {
        return hostRaceId;
    }

    public void setHostRaceId(Long hostRaceId) {
        this.hostRaceId = hostRaceId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
