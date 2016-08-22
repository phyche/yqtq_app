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

    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

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

    @ManyToOne
    @JoinColumn(name = "girl_user_id")
    private GirlUser girlUser;

    @Column(name = "insurance_num")
    private Integer insuranceNum;

    @Column(name = "message_id")
    private Long messageId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public GirlUser getGirlUser() {
        return girlUser;
    }

    public void setGirlUser(GirlUser girlUser) {
        this.girlUser = girlUser;
    }

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public Integer getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(Integer insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
