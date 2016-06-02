package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_area")
public class Area {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area")
    private String area;

    @Column(name = "city_id")
    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
