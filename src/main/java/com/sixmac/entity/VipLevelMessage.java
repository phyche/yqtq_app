package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Entity
@Table(name = "t_vip_leve_message")
public class VipLevelMessage extends BaseEntity {

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
