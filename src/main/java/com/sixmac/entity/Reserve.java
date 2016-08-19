package com.sixmac.entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_reserve")
public class Reserve extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SysInsurance insurance;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "reserve_id")
//    private List<UserReserve> userReservelist;

    @Transient
    private List<UserReserve> userReservelist;

    @Column(name = "site_id")
    private Long siteId;

    /*@ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;*/

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "match_type")
    private Integer matchType;

    @Column(name = "price")
    private double price;

    @Column(name = "payment")
    private Integer payment = 0;

    @Column(name = "start_date")
    private long startTime;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "title")
    private String title;

    @Transient
    private String content;

    @Column(name = "joinCount")
    private Integer joinCount = 0;

    @Transient
    private Integer lackCount;

    @Transient
    private Double avePrice;

    @Transient
    private Double sumPrice;

    @Column(name = "pay_status")
    private Integer payStatus;

    @Column(name = "type")
    private Integer type;

    @Transient
    private double distance;

    @Column(name = "reserve_type")
    private Integer reserveType;

    /*@Transient
    private Map<String, Object> stadiumMap = new HashMap<String, Object>();*/

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public List<UserReserve> getUserReservelist() {
        return userReservelist;
    }

    public void setUserReservelist(List<UserReserve> userReservelist) {
        this.userReservelist = userReservelist;
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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLackCount() {
        return lackCount;
    }

    public void setLackCount(Integer lackCount) {
        this.lackCount = lackCount;
    }

    /*public Map<String, Object> getStadiumMap() {
        return stadiumMap;
    }

    public void setStadiumMap(Map<String, Object> stadiumMap) {
        this.stadiumMap = stadiumMap;
    }*/

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Integer getReserveType() {
        return reserveType;
    }

    public void setReserveType(Integer reserveType) {
        this.reserveType = reserveType;
    }
}
