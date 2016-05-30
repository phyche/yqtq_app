package com.sixmac.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
@Entity
@Table(name = "t_host_race_join")
public class HostJoin extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "host_race_id")
    private HostRace hostRace;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public HostRace getHostRace() {
        return hostRace;
    }

    public void setHostRace(HostRace hostRace) {
        this.hostRace = hostRace;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
