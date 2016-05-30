package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
@Entity
@Table(name = "t_system_insurance")
public class SysInsurance extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
