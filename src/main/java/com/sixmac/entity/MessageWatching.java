package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Entity
@Table(name = "t_message_watching")
public class MessageWatching extends BaseEntity {

    @ManyToOne
    @JoinColumn( name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column(name = "type")
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "big_race_id")
    private BigRace bigRace;

    @ManyToOne
    @JoinColumn(name = "watching_race_id")
    private WatchingRace watchingRace;

    @Transient
    private String content;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigRace getBigRace() {
        return bigRace;
    }

    public void setBigRace(BigRace bigRace) {
        this.bigRace = bigRace;
    }

    public WatchingRace getWatchingRace() {
        return watchingRace;
    }

    public void setWatchingRace(WatchingRace watchingRace) {
        this.watchingRace = watchingRace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
