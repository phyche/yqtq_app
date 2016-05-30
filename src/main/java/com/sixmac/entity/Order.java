package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
@Entity
@Table(name = "t_order")
public class Order extends BaseEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "stadiumname")
    private String stadiumname;

    @Column(name = "price")
    private Double price;

    @Column(name = "sn")
    private String sn;

    @Column(name = "status")
    private Integer status = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
