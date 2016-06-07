package com.sixmac.service;

import com.sixmac.entity.PostImage;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 10:40.
 */
public interface PostImageService extends ICommonService<PostImage> {

    public List<PostImage> findByPostId(Long postId);
}