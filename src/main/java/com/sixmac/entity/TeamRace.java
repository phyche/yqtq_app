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

    /*@ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;*/

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "status")
    private Integer status = 0;

    /*@ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;*/
    @Column(name = "address")
    private String address;

    @Column(name = "start_date")
    private Long startTime;

    @Transient
    private String content;


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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
