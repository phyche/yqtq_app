package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlImageDao;
import com.sixmac.entity.GirlImage;
import com.sixmac.service.GirlImageService;
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
 * Created by Administrator on 2016/5/23 0023 下午 2:12.
 */
@Service
public class GirlImageServiceImpl implements GirlImageService {

    @Autowired
    private GirlImageDao girlImageDao;

    @Override
    public List<GirlImage> findAll() {
        return girlImageDao.findAll();
    }

    @Override
    public Page<GirlImage> find(int pageNum, int pageSize) {
        return girlImageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<GirlImage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public GirlImage getById(int id) {
        return girlImageDao.findOne(id);
    }

    @Override
    public GirlImage deleteById(int id) {
        GirlImage girlImage = getById(id);
        girlImageDao.delete(girlImage);
        return girlImage;
    }

    @Override
    public GirlImage create(GirlImage girlImage) {
        return girlImageDao.save(girlImage);
    }

    @Override
    public GirlImage update(GirlImage girlImage) {
        return girlImageDao.save(girlImage);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<GirlImage> findByGirlId(Integer girlId) {
        return girlImageDao.findByGirlId(girlId);
    }

    @Override
    public List<GirlImage> findByType(Integer type) {
        return girlImageDao.findByType(type);
    }

    @Override
    public Page<GirlImage> page(final Integer type, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<GirlImage> page = girlImageDao.findAll(new Specification<GirlImage>() {
            @Override
            public Predicate toPredicate(Root<GirlImage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (type != null) {
                    Predicate pre = cb.equal(root.get("type").as(Integer.class), type);
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
}