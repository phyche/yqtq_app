package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_girl")
public class Girl extends BaseEntity{

    /*@ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;*/
    @Column(name = "city_id")
    private Integer cityId;

    @Transient
    private String cityName;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "interest")
    private String interest;

    @Column(name = "favorite_team")
    private String favoriteTeam;

    @Column(name = "profession")
    private String profession;

    @Column(name = "label")
    private String label;

    @Column(name = "age")
    private String age;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Transient
    private Integer orderNum;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
