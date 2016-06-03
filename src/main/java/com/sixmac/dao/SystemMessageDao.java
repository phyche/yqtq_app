package com.sixmac.dao;

import com.sixmac.entity.MessageWatching;
import com.sixmac.entity.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 5:59.
 */
public interface SystemMessageDao extends JpaRepository<SystemMessage, Integer> {

    @Query("select a from SystemMessage a where a.toUser.id = ?1 ")
    public List<SystemMessage> findByToUserId(Integer userId);
}