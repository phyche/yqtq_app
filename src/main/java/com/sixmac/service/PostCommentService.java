package com.sixmac.service;

import com.sixmac.entity.PostComment;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020 下午 6:11.
 */
public interface PostCommentService extends ICommonService<PostComment> {

    public List<PostComment> findByPostId(Integer postId);

    public List<PostComment> findByFuserId(Integer userId);

    public List<PostComment> findByToUserId(Integer userId);
}