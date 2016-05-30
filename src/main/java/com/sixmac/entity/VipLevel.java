package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
@Entity
@Table(name = "t_system_vip_level")
public class VipLevel extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "system_vip_id")
    private SysVip sysVip;

    @Column(name = "level")
    private Integer level;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "preferente")
    private Double preferente;

    @Column(name = "type")
    private Integer type;

    public SysVip getSysVip() {
        return sysVip;
    }

    public void setSysVip(SysVip sysVip) {
        this.sysVip = sysVip;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Double getPreferente() {
        return preferente;
    }

    public void setPreferente(Double preferente) {
        this.preferente = preferente;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
