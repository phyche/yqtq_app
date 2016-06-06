package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
@Entity
@Table(name = "t_site_manage")
public class SiteManage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @Column(name = "start_date")
    private Long startTime;

    @Column(name = "end_date")
    private Long endTime;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
