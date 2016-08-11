package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_girl_user")
public class GirlUser extends BaseEntity{


    @ManyToOne
    @JoinColumn(name = "girl_id")
    private Girl girl;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Column(name = "start_date")
    private Long startDate;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private BigRace bigRace;

    @Column(name = "price")
    private Double price;

    @Column(name = "tip")
    private Double tip;

    @Column(name = "status")
    private Integer status = 0;

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public BigRace getBigRace() {
        return bigRace;
    }

    public void setBigRace(BigRace bigRace) {
        this.bigRace = bigRace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
