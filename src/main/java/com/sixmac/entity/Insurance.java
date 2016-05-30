package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_insurance")
public class Insurance extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

    @Column(name = "money")
    private Double money;

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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
