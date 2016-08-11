package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlDao;
import com.sixmac.entity.Girl;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.GirlService;
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
 * Created by Administrator on 2016/5/23 0023 下午 1:58.
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlDao girlDao;

    @Override
    public List<Girl> findAll() {
        return girlDao.findAll();
    }

    @Override
    public Page<Girl> find(int pageNum, int pageSize) {
        return girlDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Girl> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Girl getById(Long id) {
        return girlDao.findOne(id);
    }

    @Override
    public Girl deleteById(Long id) {
        Girl girl = getById(id);
        girlDao.delete(girl);
        return girl;
    }

    @Override
    public Girl create(Girl girl) {
        return girlDao.save(girl);
    }

    @Override
    public Girl update(Girl girl) {
        return girlDao.save(girl);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<Girl> page(Integer status, Long cityId) {
        return girlDao.page(status, cityId);
    }
}