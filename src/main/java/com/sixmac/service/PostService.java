package com.sixmac.service;

import com.sixmac.entity.Post;
import com.sixmac.entity.PostComment;
import com.sixmac.entity.Stadium;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019 下午 1:43.
 */
public interface PostService extends ICommonService<Post> {

    //帖子列表翻页
    public Page<Post> page(Integer pageNum, Integer pageSize);

    //根据用户ID查找帖子
    public List<Post> findByUserId(Long userId);

    public Page<Post> page(Long userId, Integer pageNum, Integer pageSize);
}