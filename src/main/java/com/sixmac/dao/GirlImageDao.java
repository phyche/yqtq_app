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
public interface GirlImageDao extends JpaRepository<GirlImage, Integer>, JpaSpecificationExecutor<GirlImage> {

    //根据宝贝id筛选相册
    @Query("select a from GirlImage a where a.girl.id = ?1 ")
    public List<GirlImage> findByGirlId(Integer girlId);

    //根据图片类型筛选图片
    @Query("select a from GirlImage a where a.type = ?1 ")
    public List<GirlImage> findByType(Integer type);

}