package com.sixmac.dao;

import com.sixmac.entity.Girl;
import com.sixmac.entity.GirlImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 1:57.
 */
public interface GirlDao extends JpaRepository<Girl, Long>, JpaSpecificationExecutor<Girl> {

    //根据图片类型、状态、城市筛选图片
    @Query("select a from Girl a where  a.status = ?1 and a.cityId = ?2")
    public List<Girl> page(Integer status, Long cityId);
}