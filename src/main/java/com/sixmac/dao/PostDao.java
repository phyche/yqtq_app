package com.sixmac.dao;

import com.sixmac.entity.Post;
import com.sixmac.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019 下午 1:43.
 */
public interface PostDao extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    //根据用户id筛选队赛
    @Query("select a from Post a where a.user.id = ?1 ")
    public List<Post> findByUserId(Integer userId);

}