package com.sixmac.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Entity
@Table(name = "t_activity")
public class Activity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "avater")
    private String avater;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "description")
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
