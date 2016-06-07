package com.sixmac.dao;

import com.sixmac.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 10:40.
 */
public interface PostImageDao extends JpaRepository<PostImage, Long> {

    //根据帖子id筛选帖子图片
    @Query("select a from PostImage a where a.post.id = ?1 ")
    public List<PostImage> findByPostId(Long postId);

}