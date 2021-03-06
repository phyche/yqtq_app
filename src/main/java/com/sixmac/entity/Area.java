package com.sixmac.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_area")
public class Area {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "area")
    private String area;

    @Column(name = "city_id")
    private Long cityId;

    @Transient
    private List<Stadium> stadiumList = new ArrayList<Stadium>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public List<Stadium> getStadiumList() {
        return stadiumList;
    }

    public void setStadiumList(List<Stadium> stadiumList) {
        this.stadiumList = stadiumList;
    }
}
