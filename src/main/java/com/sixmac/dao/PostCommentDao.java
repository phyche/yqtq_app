package com.sixmac.dao;

import com.sixmac.entity.BigRace;
import com.sixmac.entity.PostComment;
import com.sixmac.entity.Reserve;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020 下午 6:11.
 */
public interface PostCommentDao extends JpaRepository<PostComment, Long>, JpaSpecificationExecutor<PostComment> {

    //根据帖子id筛选评论
    @Query("select a from PostComment a where a.postId = ?1 order by a.createDate desc ")
    public List<PostComment> findByPostId(Long postId);

    /*//根据评论人Id筛选评论
    @Query("select a from PostComment a where a.fUser.id = ?1 order by a.createDate desc")
    public List<PostComment> findByFuserId(Long userId);*/

    @Query("select a from PostComment a where a.tUser.id = ?1 order by a.createDate desc")
    public List<PostComment> findByToUserId(Long userId);
}