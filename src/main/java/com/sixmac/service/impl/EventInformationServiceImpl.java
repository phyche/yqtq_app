package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.EventInformationDao;
import com.sixmac.entity.EventInformation;
import com.sixmac.service.EventInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 4:09.
 */
@Service
public class EventInformationServiceImpl implements EventInformationService {

    @Autowired
    private EventInformationDao eventInformationDao;

    @Override
    public List<EventInformation> findAll() {
        return eventInformationDao.findAll();
    }

    @Override
    public Page<EventInformation> find(int pageNum, int pageSize) {
        return eventInformationDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<EventInformation> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public EventInformation getById(int id) {
        return eventInformationDao.findOne(id);
    }

    @Override
    public EventInformation deleteById(int id) {
        EventInformation eventInformation = getById(id);
        eventInformationDao.delete(eventInformation);
        return eventInformation;
    }

    @Override
    public EventInformation create(EventInformation eventInformation) {
        return eventInformationDao.save(eventInformation);
    }

    @Override
    public EventInformation update(EventInformation eventInformation) {
        return eventInformationDao.save(eventInformation);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public EventInformation findByRaceId(Integer raceId) {
        return eventInformationDao.findByRaceId(raceId);
    }
}