package com.sixmac.dao;

import com.sixmac.entity.MessageAdd;
import com.sixmac.entity.MessagePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 2:59.
 */
public interface MessageAddDao extends JpaRepository<MessageAdd, Integer> {

    @Query("select a from MessageAdd a where a.user.id = ?1 and a.status != 0")
    public List<MessageAdd> findByUserId(Integer userId);

    @Query("select a from MessageAdd a where a.toUser.id = ?1 and a.status = 0 ")
    public List<MessageAdd> findByToUserId(Integer userId);
}