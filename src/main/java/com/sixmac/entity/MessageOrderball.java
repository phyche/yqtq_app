package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Entity
@Table(name = "t_message_orderball")
public class MessageOrderBall extends BaseEntity{

    @ManyToOne
    @JoinColumn( name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

    @Column(name = "status")
    private Integer status;

    @Transient
    private String content;

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

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
