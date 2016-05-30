package com.sixmac.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_reserve")
public class Reserve extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @OneToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "match_type")
    private Integer matchType =3;

    @Column(name = "price")
    private double price;

    @Column(name = "payment")
    private Integer payment = 0;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SysInsurance insurance;

    @Column(name = "start_date")
    private Long startTime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "reserve_id")
    private List<UserReserve> list;

    @Transient
    private String content;

    @Transient
    private Integer joinCount;

    @Transient
    private Integer lackCount;

    @Transient
    private Double avePrice;

    @Transient
    private Double sumPrice;

    public Integer getLackCount() {
        return lackCount;
    }

    public void setLackCount(Integer lackCount) {
        this.lackCount = lackCount;
    }

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public List<UserReserve> getList() {
        return list;
    }

    public void setList(List<UserReserve> list) {
        this.list = list;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public SysInsurance getInsurance() {
        return insurance;
    }

    public void setInsurance(SysInsurance insurance) {
        this.insurance = insurance;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(Double avePrice) {
        this.avePrice = avePrice;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
