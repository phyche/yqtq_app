package com.sixmac.entity.vo;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class UserVo {

    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
}
