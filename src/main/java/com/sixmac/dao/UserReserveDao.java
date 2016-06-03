package com.sixmac.dao;

import com.sixmac.entity.Reserve;
import com.sixmac.entity.UserReserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017 下午 2:46.
 */
public interface UserReserveDao extends JpaRepository<UserReserve, Integer>, JpaSpecificationExecutor<UserReserve> {

    //根据球场预定id查询所有加入的用户
    @Query("select a from UserReserve a where a.reserve.id = ?1")
    public List<UserReserve> findByReserverId(Integer reserveId);

    //根据用户id查询所有的预定
    @Query("select a from UserReserve a where a.user.id = ?1 ")
    public List<UserReserve> findByUserId(Integer userId);

}