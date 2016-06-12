package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
@Entity
@Table(name = "t_system_vip_experience")
public class SysExperience extends BaseEntity {

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "action")
    private Integer action;

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
