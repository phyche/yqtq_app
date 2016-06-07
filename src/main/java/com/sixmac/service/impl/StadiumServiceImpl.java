package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.StadiumDao;
import com.sixmac.entity.Stadium;
import com.sixmac.service.StadiumService;
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
 * Created by Administrator on 2016/5/17 0017 下午 5:41.
 */
@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumDao stadiumDao;

    @Override
    public List<Stadium> findAll() {
        return stadiumDao.findAll();
    }

    @Override
    public Page<Stadium> find(int pageNum, int pageSize) {
        return stadiumDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Stadium> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Stadium getById(Long id) {
        return stadiumDao.findOne(id);
    }

    @Override
    public Stadium deleteById(Long id) {
        Stadium booking = getById(id);
        stadiumDao.delete(booking);
        return booking;
    }

    @Override
    public Stadium create(Stadium booking) {
        return stadiumDao.save(booking);
    }

    @Override
    public Stadium update(Stadium booking) {
        return stadiumDao.save(booking);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Stadium> page(final Long areaId, final Integer type, Integer pageNum, Integer pageSize) {

        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Stadium> page = stadiumDao.findAll(new Specification<Stadium>() {
            @Override
            public Predicate toPredicate(Root<Stadium> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (areaId != null) {
                    Predicate pre = cb.equal(root.get("areaId").as(Integer.class), areaId);
                    predicateList.add(pre);
                }

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