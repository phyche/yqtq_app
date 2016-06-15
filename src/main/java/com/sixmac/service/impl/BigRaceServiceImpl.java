package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.BigRaceDao;
import com.sixmac.entity.BigRace;
import com.sixmac.entity.HostRace;
import com.sixmac.service.BigRaceService;
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
 * Created by Administrator on 2016/5/23 0023 下午 12:05.
 */
@Service
public class BigRaceServiceImpl implements BigRaceService {

    @Autowired
    private BigRaceDao bigRaceDao;

    @Override
    public List<BigRace> findAll() {
        return bigRaceDao.findAll();
    }

    @Override
    public Page<BigRace> find(int pageNum, int pageSize) {
        return bigRaceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<BigRace> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public BigRace getById(Long id) {
        return bigRaceDao.findOne(id);
    }

    @Override
    public BigRace deleteById(Long id) {
        BigRace bigRace = getById(id);
        bigRaceDao.delete(bigRace);
        return bigRace;
    }

    @Override
    public BigRace create(BigRace bigRace) {
        return bigRaceDao.save(bigRace);
    }

    @Override
    public BigRace update(BigRace bigRace) {
        return bigRaceDao.save(bigRace);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<BigRace> page(final Long cityId, final Integer status, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<BigRace> page = bigRaceDao.findAll(new Specification<BigRace>() {
            @Override
            public Predicate toPredicate(Root<BigRace> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (cityId != null) {
                    Predicate pre = cb.equal(root.get("stadium").get("cityId").as(Integer.class), cityId);
                    predicateList.add(pre);
                }

                if (status != null) {
                    Predicate pre = cb.equal(root.get("status").as(Integer.class), status);
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