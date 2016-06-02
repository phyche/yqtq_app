package com.sixmac.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_city")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city")
    private String city;

    @Column(name = "province_id")
    private Integer provinceId;

    @Transient
    private List<Area> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public List<Area> getList() {
        return list;
    }

    public void setList(List<Area> list) {
        this.list = list;
    }
}
