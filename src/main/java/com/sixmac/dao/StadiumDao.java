package com.sixmac.dao;
import com.sixmac.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017 下午 5:26.
 */
public interface StadiumDao extends JpaRepository<Stadium, Long>, JpaSpecificationExecutor<Stadium> {

    //根据区域、类型筛选场地
    @Query("select a from Stadium a where a.cityId = ?1 and a.type = ?2")
    public List<Stadium> findByArea(Long area,Integer type);

}