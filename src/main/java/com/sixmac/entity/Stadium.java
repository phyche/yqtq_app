package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_stadium")
public class Stadium extends BaseEntity{

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "description")
    private String description;

    @Column(name = "site_type")
    private String siteType;

    @Column(name = "sod_type")
    private String sodType;

    @Column(name = "light")
    private Integer light;

    @Column(name = "park")
    private Integer park;

    @Column(name = "giving")
    private String giving;

    @Column(name = "avater")
    private String avater;

    @Column(name = "address")
    private String address;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getSodType() {
        return sodType;
    }

    public void setSodType(String sodType) {
        this.sodType = sodType;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Integer getPark() {
        return park;
    }

    public void setPark(Integer park) {
        this.park = park;
    }

    public String getGiving() {
        return giving;
    }

    public void setGiving(String giving) {
        this.giving = giving;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
