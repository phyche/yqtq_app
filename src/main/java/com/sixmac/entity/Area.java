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
    private Long areaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}
