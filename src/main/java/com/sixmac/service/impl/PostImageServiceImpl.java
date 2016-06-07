package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.PostImageDao;
import com.sixmac.entity.PostImage;
import com.sixmac.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 10:41.
 */
@Service
public class PostImageServiceImpl implements PostImageService {

    @Autowired
    private PostImageDao postImageDao;

    @Override
    public List<PostImage> findAll() {
        return postImageDao.findAll();
    }

    @Override
    public Page<PostImage> find(int pageNum, int pageSize) {
        return postImageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<PostImage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public PostImage getById(Long id) {
        return postImageDao.findOne(id);
    }

    @Override
    public PostImage deleteById(Long id) {
        PostImage postImage = getById(id);
        postImageDao.delete(postImage);
        return postImage;
    }

    @Override
    public PostImage create(PostImage postImage) {
        return postImageDao.save(postImage);
    }

    @Override
    public PostImage update(PostImage postImage) {
        return postImageDao.save(postImage);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<PostImage> findByPostId(Long postId) {
        return postImageDao.findByPostId(postId);
    }
}