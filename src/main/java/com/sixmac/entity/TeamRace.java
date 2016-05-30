package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_team_race")
public class TeamRace extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "visiting_team_id")
    private Team visitingTeam;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "status")
    private Integer status = 0;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Column(name = "start_date")
    private Long startTime;


    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }


    public Team getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(Team visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
