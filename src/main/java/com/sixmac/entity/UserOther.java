package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
@Entity
@Table(name = "t_user_other")
public class UserOther extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "type")
    private Integer type;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
