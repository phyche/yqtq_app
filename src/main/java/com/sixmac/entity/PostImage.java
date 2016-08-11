package com.sixmac.entity;

import com.sixmac.utils.ConfigUtil;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@Entity
@Table(name = "t_post_image")
public class PostImage extends BaseEntity{

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "avater")
    private String avater;

    @Column(name = "status")
    private Integer status;


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getAvater() {
        return this.avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
