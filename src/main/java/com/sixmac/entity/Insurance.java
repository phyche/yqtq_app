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

    @ManyToOne
    @JoinColumn(name = "reserve_team_id")
    private ReserveTeam reserveTeam;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SysInsurance sysInsurance;

    @Column(name = "num")
    private Integer num;

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

    public SysInsurance getSysInsurance() {
        return sysInsurance;
    }

    public void setSysInsurance(SysInsurance sysInsurance) {
        this.sysInsurance = sysInsurance;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public ReserveTeam getReserveTeam() {
        return reserveTeam;
    }

    public void setReserveTeam(ReserveTeam reserveTeam) {
        this.reserveTeam = reserveTeam;
    }
}
