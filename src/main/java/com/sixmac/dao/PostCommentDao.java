package com.sixmac.dao;

import com.sixmac.entity.PostComment;
import com.sixmac.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020 下午 6:11.
 */
public interface PostCommentDao extends JpaRepository<PostComment, Integer> {

    //根据帖子id筛选评论
    @Query("select a from PostComment a where a.post.id = ?1 ")
    public List<PostComment> findByPostId(Integer postId);

    //根据评论人Id筛选评论
    @Query("select a from PostComment a where a.fUser.id = ?1 ")
    public List<PostComment> findByFuserId(Integer userId);
}