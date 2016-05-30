package com.sixmac.entity.vo;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class WatchBallVo {

    private Integer id;

    private String homeTeamName;

    private String homeTeamAvater;

    private String vTeamName;

    private String vTeamAvater;

    private Integer status;

    private Long startTime;

    private String stadiumName;

    private Integer addressId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getHomeTeamAvater() {
        return homeTeamAvater;
    }

    public void setHomeTeamAvater(String homeTeamAvater) {
        this.homeTeamAvater = homeTeamAvater;
    }

    public String getvTeamName() {
        return vTeamName;
    }

    public void setvTeamName(String vTeamName) {
        this.vTeamName = vTeamName;
    }

    public String getvTeamAvater() {
        return vTeamAvater;
    }

    public void setvTeamAvater(String vTeamAvater) {
        this.vTeamAvater = vTeamAvater;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
