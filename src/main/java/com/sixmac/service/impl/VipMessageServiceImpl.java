package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.VipMessageDao;
import com.sixmac.entity.VipMessage;
import com.sixmac.service.VipMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:45.
 */
@Service
public class VipMessageServiceImpl implements VipMessageService {

    @Autowired
    private VipMessageDao vipMessageDao;

    @Override
    public List<VipMessage> findAll() {
        return vipMessageDao.findAll();
    }

    @Override
    public Page<VipMessage> find(int pageNum, int pageSize) {
        return vipMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<VipMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public VipMessage getById(int id) {
        return vipMessageDao.findOne(id);
    }

    @Override
    public VipMessage deleteById(int id) {
        VipMessage vipMessage = getById(id);
        vipMessageDao.delete(vipMessage);
        return vipMessage;
    }

    @Override
    public VipMessage create(VipMessage vipMessage) {
        return vipMessageDao.save(vipMessage);
    }

    @Override
    public VipMessage update(VipMessage vipMessage) {
        return vipMessageDao.save(vipMessage);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}