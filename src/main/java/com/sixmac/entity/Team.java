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

    @OneToMany
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany
    @JoinColumn(name = "province_id")
    private Province province;

    @OneToMany
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "avater")
    private String avater;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
