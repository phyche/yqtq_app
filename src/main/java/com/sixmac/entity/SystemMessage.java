package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Entity
@Table(name = "t_system_message")
public class SystemMessage extends BaseEntity {

    @Column(name = "to_user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
