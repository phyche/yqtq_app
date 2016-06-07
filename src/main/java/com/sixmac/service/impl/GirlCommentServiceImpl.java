package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlCommentDao;
import com.sixmac.entity.GirlComment;
import com.sixmac.service.GirlCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026 上午 10:36.
 */
@Service
public class GirlCommentServiceImpl implements GirlCommentService {

    @Autowired
    private GirlCommentDao girlCommentDao;

    @Override
    public List<GirlComment> findAll() {
        return girlCommentDao.findAll();
    }

    @Override
    public Page<GirlComment> find(int pageNum, int pageSize) {
        return girlCommentDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<GirlComment> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public GirlComment getById(Long id) {
        return girlCommentDao.findOne(id);
    }

    @Override
    public GirlComment deleteById(Long id) {
        GirlComment girlComment = getById(id);
        girlCommentDao.delete(girlComment);
        return girlComment;
    }

    @Override
    public GirlComment create(GirlComment girlComment) {
        return girlCommentDao.save(girlComment);
    }

    @Override
    public GirlComment update(GirlComment girlComment) {
        return girlCommentDao.save(girlComment);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<GirlComment> findByGirlId(Long girlId) {
        return girlCommentDao.findByGirlId(girlId);
    }
}