package com.sixmac.service.impl;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.Constant;
import com.sixmac.dao.ReserveDao;
import com.sixmac.entity.Reserve;
import com.sixmac.entity.UserReserve;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.ReserveService;
import com.sixmac.utils.DateUtils;
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
 * Created by Administrator on 2016/5/19 0019 上午 11:54.
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveDao reserveDao;

    @Override
    public List<Reserve> findAll() {
        return reserveDao.findAll();
    }

    @Override
    public Page<Reserve> find(int pageNum, int pageSize) {
        return reserveDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Reserve> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Reserve getById(Long id) {
        return reserveDao.findOne(id);
    }

    @Override
    public Reserve deleteById(Long id) {
        Reserve booking = getById(id);
        reserveDao.delete(booking);
        return booking;
    }

    @Override
    public Reserve create(Reserve booking) {
        return reserveDao.save(booking);
    }

    @Override
    public Reserve update(Reserve booking) {
        return reserveDao.save(booking);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<Reserve> findByUserId(Long userId) {
        return reserveDao.findByUserId(userId);
    }

    @Override
    public Page<Reserve> page(final Integer timelimit, final Integer type, final Long areaId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Reserve> page = reserveDao.findAll(new Specification<Reserve>() {
            @Override
            public Predicate toPredicate(Root<Reserve> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if(timelimit != null) {
                    long beforeDate = DateUtils.dateAddDay(timelimit * -1).getTime();
                    predicateList.add(cb.between(root.get("startTime").as(Long.class),beforeDate,System.currentTimeMillis()));
                }

                if (type != null) {
                    Predicate pre = cb.equal(root.get("matchType").as(Integer.class), type);
                    predicateList.add(pre);
                }

                if (areaId != null) {
                    Predicate pre = cb.equal(root.get("stadium").get("area").get("areaId").as(Integer.class), areaId);
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