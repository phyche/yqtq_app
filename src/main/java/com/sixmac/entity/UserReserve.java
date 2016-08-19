package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@Entity
@Table(name = "t_user_reserve_join")
public class UserReserve extends BaseEntity{

    @Transient
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**/
    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

//    @Column(name = "reserve_id")
//    private Long reserveId;

    @Transient
    private String content;

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   /* public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }*/

//    public Long getReserveId() {
//        return reserveId;
//    }
//
//    public void setReserveId(Long reserveId) {
//        this.reserveId = reserveId;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
