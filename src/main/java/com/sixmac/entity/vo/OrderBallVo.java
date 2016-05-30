package com.sixmac.entity.vo;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class OrderBallVo {

    private Integer id;

    private String name;

    private String avater;

    private String stadiumName;

    private Integer type;

    private Integer lackNum;

    private Long startTime;

    private Integer payWay;

    private Double price;

    private String insuranceName;

    private Double insurancePrice;

    private Double sumPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLackNum() {
        return lackNum;
    }

    public void setLackNum(Integer lackNum) {
        this.lackNum = lackNum;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
