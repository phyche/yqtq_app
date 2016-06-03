package com.sixmac.dao;

import com.sixmac.entity.MessageOrderBall;
import com.sixmac.entity.MessageWatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:00.
 */
public interface MessageWatchingDao extends JpaRepository<MessageWatching, Integer> {

    @Query("select a from MessageWatching a where a.toUser.id = ?1 ")
    public List<MessageWatching> findByToUserId(Integer userId);
}