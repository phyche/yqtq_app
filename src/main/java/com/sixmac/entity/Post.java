package com.sixmac.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_post")
public class Post extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "share_count")
    private Integer shareNum = 0;

    @Column(name = "report_count")
    private Integer reportNum = 0;

    @Column(name = "status")
    private Integer status;

    @Transient
    private Integer commentNum = 0;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    Set<PostImage> postImages = new HashSet<PostImage>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    Set<PostComment> postCommentList = new HashSet<PostComment>();

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

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Set<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(Set<PostImage> postImages) {
        this.postImages = postImages;
    }

    public Set<PostComment> getPostCommentList() {
        return postCommentList;
    }

    public void setPostCommentList(Set<PostComment> postCommentList) {
        this.postCommentList = postCommentList;
    }
}
