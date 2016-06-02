package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Entity
@Table(name = "t_message_add")
public class MessageAdd extends BaseEntity {

    @ManyToOne
    @JoinColumn( name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column(name = "status")
    private Integer status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
