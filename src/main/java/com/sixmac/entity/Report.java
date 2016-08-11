package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
@Entity
@Table(name = "t_system_report")
public class Report extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "content")
    private String content;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
