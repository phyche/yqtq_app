package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Embeddable
public class AreaComposeId implements Serializable {

    private static final long serialVersionUID = -8244428669541698412L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "area_id")
    private Integer areaId;

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

    @Override
    public String toString() {
        return "AreaComposeId{" +
                "id=" + id +
                ", areaId=" + areaId +
                '}';
    }
}
