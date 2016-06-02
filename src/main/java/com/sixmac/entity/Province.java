package com.sixmac.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_province")
public class Province {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province")
    private String province;

    @Transient
    private List<City> list;

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
