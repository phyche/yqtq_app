package com.sixmac.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_team")
public class Team extends BaseEntity{

    @Column(name = "name")
    private String name;

    /*@ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "city_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;*/

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "area_id")
    private Integer areaId;

    @Transient
    private String areaName;

    @Transient
    private String cityName;

    @Transient
    private String provinceName;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "avater")
    private String avater;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "leader_user_id")
    private User leaderUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_team_member",joinColumns = {@JoinColumn(name = "team_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")})
    private List<User> list;

    @Column(name = "declare_num")
    private Integer declareNum = 0;

    @Column(name = "battle_num")
    private Integer battleNum = 0;

    @Transient
    private Integer count;

    @Transient
    private Integer sum;

    @Transient
    private Double aveAge;

    @Transient
    private Double aveHeight;

    @Transient
    private Double aveWeight;

    @Transient
    private Integer num;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public User getLeaderUser() {
        return leaderUser;
    }

    public void setLeaderUser(User leaderUser) {
        this.leaderUser = leaderUser;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public Double getAveAge() {
        return aveAge;
    }

    public void setAveAge(Double aveAge) {
        this.aveAge = aveAge;
    }

    public Double getAveHeight() {
        return aveHeight;
    }

    public void setAveHeight(Double aveHeight) {
        this.aveHeight = aveHeight;
    }

    public Double getAveWeight() {
        return aveWeight;
    }

    public void setAveWeight(Double aveWeight) {
        this.aveWeight = aveWeight;
    }

    public Integer getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(Integer declareNum) {
        this.declareNum = declareNum;
    }

    public Integer getBattleNum() {
        return battleNum;
    }

    public void setBattleNum(Integer battleNum) {
        this.battleNum = battleNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
