package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Entity
@Table(name = "t_credibility_message")
public class CredibilityMessage extends BaseEntity {

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
