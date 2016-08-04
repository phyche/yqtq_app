package com.sixmac.dao;

import com.sixmac.entity.City;
import com.sixmac.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:42.
 */
public interface MessageDao extends JpaRepository<Message, Long> {

    @Query("select a from Message a where a.type = ?1")
    public Message getByType(Integer type);
}