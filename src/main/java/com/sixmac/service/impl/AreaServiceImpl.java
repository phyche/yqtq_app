package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.AreaDao;
import com.sixmac.entity.Area;
import com.sixmac.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:00.
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> findAll() {
        return areaDao.findAll();
    }

    @Override
    public Page<Area> find(int pageNum, int pageSize) {
        return areaDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Area> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Area getById(int id) {
        return areaDao.findOne(id);
    }

    @Override
    public Area deleteById(int id) {
        Area area = getById(id);
        areaDao.delete(area);
        return area;
    }

    @Override
    public Area create(Area area) {
        return areaDao.save(area);
    }

    @Override
    public Area update(Area area) {
        return areaDao.save(area);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}