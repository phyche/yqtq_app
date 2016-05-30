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

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SysInsurance insurance;

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
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

}
