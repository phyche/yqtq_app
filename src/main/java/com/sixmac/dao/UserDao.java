package com.sixmac.dao;

import com.sixmac.entity.User;
import com.sixmac.entity.UserOther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/5/17 0017 下午 4:52.
 */
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("select a from User a where a.mobile = ?1 ")
    public User findByMobile(String mobile);

    @Query("select a from User a where a.nickname = ?1 ")
    public User findByName(String nickname);

    @Query("select a from UserOther a where a.openId = ?1 and a.type = ?2")
    public UserOther iTLogin(String openId, Integer type);
}