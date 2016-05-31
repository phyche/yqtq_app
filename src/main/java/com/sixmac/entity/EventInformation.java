package com.sixmac.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_event_information")
public class EventInformation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "race_id")
    private HostRace hostRace;

    @Column(name = "content")
    private String content;

    public HostRace getHostRace() {
        return hostRace;
    }

    public void setHostRace(HostRace hostRace) {
        this.hostRace = hostRace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
