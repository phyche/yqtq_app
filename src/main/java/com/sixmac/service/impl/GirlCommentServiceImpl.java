package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlCommentDao;
import com.sixmac.entity.GirlComment;
import com.sixmac.entity.GirlUser;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.GirlCommentService;
import com.sixmac.service.GirlUserService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026 上午 10:36.
 */
@Service
public class GirlCommentServiceImpl implements GirlCommentService {

    @Autowired
    private GirlCommentDao girlCommentDao;

    @Autowired
    private GirlUserService girlUserService;

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

    @Override
    public Page<GirlComment> page(final Long girlId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<GirlComment> page = girlCommentDao.findAll(new Specification<GirlComment>() {
            @Override
            public Predicate toPredicate(Root<GirlComment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (girlId != null) {
                    Predicate pre = cb.equal(root.get("girlId").as(Long.class), girlId);
                    predicateList.add(pre);
                }

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    @Override
    public void comment(HttpServletResponse response, Double star, String content, Long watchingId) {
        GirlUser girlUser = girlUserService.getById(watchingId);

        if (girlUser.getStatus() == 1) {

            GirlComment girlComment = new GirlComment();
            girlComment.setUserId(girlUser.getUserId());
            girlComment.setGirlId(girlUser.getGirl().getId());
            girlComment.setStar(star);
            girlComment.setContent(content);
            girlCommentDao.save(girlComment);
        }
        girlUser.setStatus(2);
        girlUserService.update(girlUser);
    }
}