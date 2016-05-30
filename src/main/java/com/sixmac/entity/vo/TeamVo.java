package com.sixmac.entity.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class TeamVo {

    private Integer id;

    private String name;

    private String avater;

    private Integer count;

    private Integer declareNum;

    private Integer battleNum;

    private List<UserVo> member;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(Integer declareNum) {
        this.declareNum = declareNum;
    }

    public Integer getBattleNum() {
        return battleNum;
    }

    public void setBattleNum(Integer battleNum) {
        this.battleNum = battleNum;
    }

    public List<UserVo> getMember() {
        return member;
    }

    public void setMember(List<UserVo> member) {
        this.member = member;
    }
}
