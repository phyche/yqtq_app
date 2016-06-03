package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Entity
@Table(name = "t_system_message")
public class SystemMessage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
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
