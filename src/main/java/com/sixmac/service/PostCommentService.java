package com.sixmac.service;

import com.sixmac.entity.BigRace;
import com.sixmac.entity.PostComment;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020 下午 6:11.
 */
public interface PostCommentService extends ICommonService<PostComment> {

    public List<PostComment> findByPostId(Long postId);

    public List<PostComment> findByFuserId(Long userId);

    public List<PostComment> findByToUserId(Long userId);

}