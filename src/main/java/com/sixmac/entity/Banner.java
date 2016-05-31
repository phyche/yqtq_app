package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_system_banner")
public class Banner extends BaseEntity{

    @Column(name = "avater")
    private String avater;

    @Column(name = "type")
    private Integer type;

    @Column(name = "to_id")
    private Integer toId;

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }
}
