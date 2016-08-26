package com.sixmac.dao;

import com.sixmac.entity.MessageOrderBall;
import com.sixmac.entity.MessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022 15:09.
 */
public interface MessageRecordDao extends JpaRepository<MessageRecord, Long> {

    //根据用户id查询消息
    @Query("select a from MessageRecord a where a.userId = ?1 and a.status = 0 ")
    public List<MessageRecord> findByUserId(Long userId);

    //根据messageId查询消息
    @Query("select a from MessageRecord a where a.messageId = ?1 and a.type = ?2")
    public List<MessageRecord> findByMessageId(Long messageId, Integer type);
}