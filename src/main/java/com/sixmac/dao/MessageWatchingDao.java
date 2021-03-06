package com.sixmac.dao;

import com.sixmac.entity.MessageOrderBall;
import com.sixmac.entity.MessageWatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:00.
 */
public interface MessageWatchingDao extends JpaRepository<MessageWatching, Long> {

    //根据用户id查询邀请用户看球消息
    @Query("select a from MessageWatching a where a.toUser.id = ?1 order by a.createDate desc ")
    public List<MessageWatching> findByToUserId(Long userId);
}