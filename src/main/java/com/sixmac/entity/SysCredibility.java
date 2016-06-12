package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
@Entity
@Table(name = "t_system_vip_credibility")
public class SysCredibility extends BaseEntity {

    @Column(name = "credibility")
    private Integer credibility;

    @Column(name = "action")
    private Integer action;

    public Integer getCredibility() {
        return credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
