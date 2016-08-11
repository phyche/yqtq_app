package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
@Entity
@Table(name = "t_girl_comment")
public class GirlComment extends BaseEntity{

    @Column(name = "girl_id")
    private Long girlId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "star")
    private Double star;

    public Long getGirlId() {
        return girlId;
    }

    public void setGirlId(Long girlId) {
        this.girlId = girlId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }
}
