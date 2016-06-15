package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_big_race")
public class BigRace extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "team1name")
    private String team1name;

    @Column(name = "avater1")
    private String avater1;

    @Column(name = "team2name")
    private String team2name;

    @Column(name = "avater2")
    private String avater2;

    @Column(name = "start_Date")
    private Long startDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Transient
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam1name() {
        return team1name;
    }

    public void setTeam1name(String team1name) {
        this.team1name = team1name;
    }

    public String getAvater1() {
        return avater1;
    }

    public void setAvater1(String avater1) {
        this.avater1 = avater1;
    }

    public String getTeam2name() {
        return team2name;
    }

    public void setTeam2name(String team2name) {
        this.team2name = team2name;
    }

    public String getAvater2() {
        return avater2;
    }

    public void setAvater2(String avater2) {
        this.avater2 = avater2;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
