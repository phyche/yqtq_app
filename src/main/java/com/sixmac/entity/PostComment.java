package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@Entity
@Table(name = "t_post_comment")
public class PostComment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User tUser;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "content")
    private String content;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getfUser() {
        return fUser;
    }

    public void setfUser(User fUser) {
        this.fUser = fUser;
    }

    public User gettUser() {
        return tUser;
    }

    public void settUser(User tUser) {
        this.tUser = tUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
