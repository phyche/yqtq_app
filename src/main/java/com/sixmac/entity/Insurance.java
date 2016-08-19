package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_insurance")
public class Insurance extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reserve_id")
    private Long reserveId;

    @Column(name = "reserve_team_id")
    private Long reserveTeamId;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SysInsurance sysInsurance;

    @Column(name = "num")
    private Integer num = 0;

    @Column(name = "money")
    private Double money = 0.0;

    @Column(name = "insurance_num")
    private Integer insuranceNum;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public Long getReserveTeamId() {
        return reserveTeamId;
    }

    public void setReserveTeamId(Long reserveTeamId) {
        this.reserveTeamId = reserveTeamId;
    }

    public Integer getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(Integer insuranceNum) {
        this.insuranceNum = insuranceNum;
    }
}
