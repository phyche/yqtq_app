package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.WatchingRaceDao;
import com.sixmac.entity.Team;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.WatchingRaceService;
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
 * Created by Administrator on 2016/5/23 0023 上午 11:20.
 */
@Service
public class WatchingRaceServiceImpl implements WatchingRaceService {

    @Autowired
    private WatchingRaceDao watchingRaceDao;

    @Override
    public List<WatchingRace> findAll() {
        return watchingRaceDao.findAll();
    }

    @Override
    public Page<WatchingRace> find(int pageNum, int pageSize) {
        return watchingRaceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<WatchingRace> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public WatchingRace getById(int id) {
        return watchingRaceDao.findOne(id);
    }

    @Override
    public WatchingRace deleteById(int id) {
        WatchingRace watchingRace = getById(id);
        watchingRaceDao.delete(watchingRace);
        return watchingRace;
    }

    @Override
    public WatchingRace create(WatchingRace watchingRace) {
        return watchingRaceDao.save(watchingRace);
    }

    @Override
    public WatchingRace update(WatchingRace watchingRace) {
        return watchingRaceDao.save(watchingRace);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<WatchingRace> page(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<WatchingRace> page = watchingRaceDao.findAll(new Specification<WatchingRace>() {
            @Override
            public Predicate toPredicate(Root<WatchingRace> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

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