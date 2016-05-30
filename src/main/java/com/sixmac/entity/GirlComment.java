package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
@Entity
@Table(name = "t_girl_comment")
public class GirlComment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "girl_id")
    private Girl girl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "star")
    private Integer star;

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
