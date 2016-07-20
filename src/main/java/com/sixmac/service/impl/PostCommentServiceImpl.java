package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.PostCommentDao;
import com.sixmac.entity.BigRace;
import com.sixmac.entity.PostComment;
import com.sixmac.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020 下午 6:11.
 */
@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Autowired
    private PostCommentDao postCommentDao;

    @Override
    public List<PostComment> findAll() {
        return postCommentDao.findAll();
    }

    @Override
    public Page<PostComment> find(int pageNum, int pageSize) {
        return postCommentDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<PostComment> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public PostComment getById(Long id) {
        return postCommentDao.findOne(id);
    }

    @Override
    public PostComment deleteById(Long id) {
        PostComment postComment = getById(id);
        postCommentDao.delete(postComment);
        return postComment;
    }

    @Override
    public PostComment create(PostComment postComment) {
        return postCommentDao.save(postComment);
    }

    @Override
    public PostComment update(PostComment postComment) {
        return postCommentDao.save(postComment);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<PostComment> findByPostId(Long postId) {
        return postCommentDao.findByPostId(postId);
    }

    @Override
    public List<PostComment> findByFuserId(Long userId) {
        return postCommentDao.findByFuserId(userId);
    }

    @Override
    public List<PostComment> findByToUserId(Long userId) {
        return postCommentDao.findByToUserId(userId);
    }


}