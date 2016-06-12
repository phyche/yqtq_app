package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
@Entity
@Table(name = "t_order")
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "stadiumname")
    private String stadiumname;

    @Column(name = "price")
    private Double price;

    @Column(name = "sn")
    private String sn;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "pay_date")
    private Long payTime;

    @Column(name = "type")
    private Integer type;

    @Column(name = "action")
    private Integer action;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStadiumname() {
        return stadiumname;
    }

    public void setStadiumname(String stadiumname) {
        this.stadiumname = stadiumname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
