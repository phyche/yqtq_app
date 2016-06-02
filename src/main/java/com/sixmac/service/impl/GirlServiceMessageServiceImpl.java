package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlServiceMessageDao;
import com.sixmac.entity.GirlServiceMessage;
import com.sixmac.service.GirlServiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:43.
 */
@Service
public class GirlServiceMessageServiceImpl implements GirlServiceMessageService {

    @Autowired
    private GirlServiceMessageDao girlServiceMessageDao;

    @Override
    public List<GirlServiceMessage> findAll() {
        return girlServiceMessageDao.findAll();
    }

    @Override
    public Page<GirlServiceMessage> find(int pageNum, int pageSize) {
        return girlServiceMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<GirlServiceMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public GirlServiceMessage getById(int id) {
        return girlServiceMessageDao.findOne(id);
    }

    @Override
    public GirlServiceMessage deleteById(int id) {
        GirlServiceMessage girlServiceMessage = getById(id);
        girlServiceMessageDao.delete(girlServiceMessage);
        return girlServiceMessage;
    }

    @Override
    public GirlServiceMessage create(GirlServiceMessage girlServiceMessage) {
        return girlServiceMessageDao.save(girlServiceMessage);
    }

    @Override
    public GirlServiceMessage update(GirlServiceMessage girlServiceMessage) {
        return girlServiceMessageDao.save(girlServiceMessage);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}