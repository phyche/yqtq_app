package com.sixmac.dao;

import com.sixmac.entity.Reserve;
import com.sixmac.entity.Stadium;
import com.sixmac.entity.UserReserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019 上午 11:53.
 */
public interface ReserveDao extends JpaRepository<Reserve, Integer>, JpaSpecificationExecutor<Reserve> {

    //根据用户id筛选预定
    @Query("select a from Reserve a where a.user.id = ?1 ")
    public List<Reserve> findByUserId(Integer userId);

    //根据球场id筛选预定
    @Query("select a from Reserve a where a.stadium.id = ?1 ")
    public List<Reserve> findByStadiumId(Integer stadiumId);


    //根据时间筛选约球
    @Query("select a from Reserve a where a.startTime = ?1 ")
    public List<Reserve> findListByTime(Date time);


}