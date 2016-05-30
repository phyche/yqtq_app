package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@Entity
@Table(name = "t_user_reserve_join")
public class UserReserve extends BaseEntity{

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

    @Column(name = "title")
    private String title;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
