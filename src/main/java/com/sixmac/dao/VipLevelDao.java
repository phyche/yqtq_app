package com.sixmac.dao;

import com.sixmac.entity.VipLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 4:41.
 */
public interface VipLevelDao extends JpaRepository<VipLevel, Long>, JpaSpecificationExecutor<VipLevel> {

    @Query("select a from VipLevel a where a.level = ?1 ")
    public VipLevel findBylevel(Integer level);

}