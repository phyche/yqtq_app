package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
@Entity
@Table(name = "t_system_vip")
public class SysVip extends BaseEntity{

    @Column(name = "price")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
