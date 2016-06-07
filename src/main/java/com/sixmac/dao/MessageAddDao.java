package com.sixmac.dao;

import com.sixmac.entity.MessageAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 2:59.
 */
public interface MessageAddDao extends JpaRepository<MessageAdd, Long> {

    //根据用户id查询用户添加成功或者失败的好友消息
    @Query("select a from MessageAdd a where a.user.id = ?1 and a.status != 0")
    public List<MessageAdd> findByUserId(Long userId);

    //根据用户id查询用户添加成功的好友消息
    @Query("select a from MessageAdd a where a.user.id = ?1 and a.status = 1")
    public List<MessageAdd> findUserId(Long userId);

    //根据用户id查询等待用户处理的好友添加消息
    @Query("select a from MessageAdd a where a.toUser.id = ?1 and a.status = 0 ")
    public List<MessageAdd> findByToUserId(Long userId);
}