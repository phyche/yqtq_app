package com.sixmac.dao;

import com.sixmac.entity.UserVip;
import com.sixmac.entity.VipLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/12 0012 上午 10:07.
 */
public interface UserVipDao extends JpaRepository<UserVip, Long> {

    @Query("select a from UserVip a where a.userId = ?1 ")
    public UserVip findByUserId(Long userId);
}