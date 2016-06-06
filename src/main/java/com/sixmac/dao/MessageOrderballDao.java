package com.sixmac.dao;

import com.sixmac.entity.MessageOrderBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:39.
 */
public interface MessageOrderBallDao extends JpaRepository<MessageOrderBall, Integer> {

    //根据用户id查询等待用户处理的好友约球消息
    @Query("select a from MessageOrderBall a where a.toUser.id = ?1 and a.status = 0 ")
    public List<MessageOrderBall> findByToUserId(Integer userId);
}