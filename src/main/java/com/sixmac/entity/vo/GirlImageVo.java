package com.sixmac.entity.vo;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class GirlImageVo {

    private Long id;
    private Long girlId;
    private String url;
    private String name;
    private Integer age;
    private Double weight;
    private Double height;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGirlId() {
        return girlId;
    }

    public void setGirlId(Long girlId) {
        this.girlId = girlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
