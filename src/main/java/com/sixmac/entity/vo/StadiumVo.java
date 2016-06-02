package com.sixmac.entity.vo;

import com.sixmac.entity.City;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class StadiumVo {

    private Integer id;

    private String name;

    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
