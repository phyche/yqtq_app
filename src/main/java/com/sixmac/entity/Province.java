package com.sixmac.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Entity
@Table(name = "t_province")
public class Province {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "father_id",referencedColumnName = "province_id")
    private List<City> list;

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
