package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.VipLevelMessageDao;
import com.sixmac.entity.VipLevelMessage;
import com.sixmac.service.VipLevelMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:08.
 */
@Service
public class VipLevelMessageServiceImpl implements VipLevelMessageService {

    @Autowired
    private VipLevelMessageDao vipLevelMessageDao;

    @Override
    public List<VipLevelMessage> findAll() {
        return vipLevelMessageDao.findAll();
    }

    @Override
    public Page<VipLevelMessage> find(int pageNum, int pageSize) {
        return vipLevelMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<VipLevelMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public VipLevelMessage getById(Long id) {
        return vipLevelMessageDao.findOne(id);
    }

    @Override
    public VipLevelMessage deleteById(Long id) {
        VipLevelMessage vipLevelMessage = getById(id);
        vipLevelMessageDao.delete(vipLevelMessage);
        return vipLevelMessage;
    }

    @Override
    public VipLevelMessage create(VipLevelMessage vipLevelMessage) {
        return vipLevelMessageDao.save(vipLevelMessage);
    }

    @Override
    public VipLevelMessage update(VipLevelMessage vipLevelMessage) {
        return vipLevelMessageDao.save(vipLevelMessage);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}