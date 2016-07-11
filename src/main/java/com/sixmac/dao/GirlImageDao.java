package com.sixmac.dao;

import com.sixmac.entity.GirlImage;
import com.sixmac.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 2:09.
 */
public interface GirlImageDao extends JpaRepository<GirlImage, Long>, JpaSpecificationExecutor<GirlImage> {

    //根据宝贝id筛选相册
    @Query("select a from GirlImage a where a.girl.id = ?1 ")
    public List<GirlImage> findByGirlId(Long girlId);

    //根据图片类型筛选图片
    @Query("select a from GirlImage a where a.type = ?1 ")
    public List<GirlImage> findByType(Integer type);

    //根据宝贝id,图片类型筛选图片
    @Query("select a from GirlImage a where a.type = ?2 and a.girl.id = ?1")
    public List<GirlImage> find(Long girlId, Integer type);

    //根据图片类型、状态、城市筛选图片
    @Query("select a from GirlImage a where a.type = ?1 and a.girl.status = ?2 and a.girl.cityId = ?3")
    public List<GirlImage> page(Integer type, Integer status, Long cityId);

}