package com.sixmac.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_area")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "father_id",referencedColumnName = "city_id")
    private List<Area> list;

    public List<Area> getList() {
        return list;
    }

    public void setList(List<Area> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
