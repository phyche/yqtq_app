package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_watching_race")
public class WatchingRace extends BaseEntity{

    @Column(name = "name")
    private String name;

    /*@ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;*/

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "avater")
    private String avater;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
