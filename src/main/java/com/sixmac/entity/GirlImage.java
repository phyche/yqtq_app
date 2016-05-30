package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Entity
@Table(name = "t_girl_image")
public class GirlImage extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "girl_id")
    private Girl girl;

    @Column(name = "type")
    private Integer type;

    @Column(name = "url")
    private String url;

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
