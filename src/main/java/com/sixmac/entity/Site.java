package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
@Entity
@Table(name = "t_stadium_sub")
public class Site extends BaseEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "type")
    private Integer type;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
