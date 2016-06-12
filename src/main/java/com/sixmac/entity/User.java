package com.sixmac.entity;

import com.sixmac.annotation.Exclude;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_user_info")
public class User extends BaseEntity{

    @Column(name = "mobile")
    private String mobile;

    @Exclude
    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "age")
    private Integer age = 0;

    @Column(name = "height")
    private Double height = 0.0;

    @Column(name = "weight")
    private Double weight = 0.0;

    @Column(name = "position")
    private Integer position = 0;

    @Column(name = "credibility")
    private Integer credibility = 0;

    @Column(name = "vip_level")
    private Integer vipNum = 0;

    @Column(name = "integral")
    private Integer integral = 0;

    @Column(name = "experience")
    private Integer experience = 0;

    @Column(name = "avater")
    private String avater;

    /*@ManyToOne
    @JoinColumn(name = "provice_id",referencedColumnName = "province_id")
    private Province province;*/

    @Column(name = "vip_end_date")
    private Long endDate;

   /* @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;*/

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "gender")
    private Integer gender = 0;

    @Column(name = "birthday")
    private Long birthday;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getCredibility() {
        return credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public Integer getVipNum() {
        return vipNum;
    }

    public void setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }
}
