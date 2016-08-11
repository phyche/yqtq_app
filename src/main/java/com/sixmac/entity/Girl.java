package com.sixmac.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_girl")
public class Girl extends BaseEntity{

    @Column(name = "city_id")
    private Long cityId;

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
    private Integer age;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Transient
    private Integer orderNum;

    @Transient
    private Double aveStar = 0.0;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "girl_id")
    Set<GirlImage> girlImageList = new HashSet<GirlImage>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "girl_id")
    Set<GirlComment> girlComments = new HashSet<GirlComment>();

    @Column
    private Integer status;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<GirlImage> getGirlImageList() {
        return girlImageList;
    }

    public void setGirlImageList(Set<GirlImage> girlImageList) {
        this.girlImageList = girlImageList;
    }

    public Double getAveStar() {
        return aveStar;
    }

    public void setAveStar(Double aveStar) {
        this.aveStar = aveStar;
    }

    public Set<GirlComment> getGirlComments() {
        return girlComments;
    }

    public void setGirlComments(Set<GirlComment> girlComments) {
        this.girlComments = girlComments;
    }
}
