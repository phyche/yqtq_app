package com.sixmac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
@Entity
@Table(name = "t_message_record")
public class MessageRecord extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "type")
    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
