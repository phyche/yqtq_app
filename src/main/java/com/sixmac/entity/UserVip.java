package com.sixmac.entity;

import org.hibernate.metamodel.relational.Schema;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
@Entity
@Table(name = "t_user_vip")
public class UserVip extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "vip_end_date")
    private Long endDate;

    @Transient
    private String status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
