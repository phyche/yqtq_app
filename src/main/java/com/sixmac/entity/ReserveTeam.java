package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_reserve_team")
public class ReserveTeam extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "site_id")
    private Site site;

    /*@ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;*/

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private Integer status = 0;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SysInsurance insurance;

    @Column(name = "start_date")
    private Long startTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SysInsurance getInsurance() {
        return insurance;
    }

    public void setInsurance(SysInsurance insurance) {
        this.insurance = insurance;
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
}
