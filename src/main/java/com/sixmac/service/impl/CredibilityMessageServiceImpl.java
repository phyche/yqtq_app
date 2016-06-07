package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.CredibilityMessageDao;
import com.sixmac.entity.CredibilityMessage;
import com.sixmac.service.CredibilityMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:42.
 */
@Service
public class CredibilityMessageServiceImpl implements CredibilityMessageService {

    @Autowired
    private CredibilityMessageDao credibilityMessageDao;

    @Override
    public List<CredibilityMessage> findAll() {
        return credibilityMessageDao.findAll();
    }

    @Override
    public Page<CredibilityMessage> find(int pageNum, int pageSize) {
        return credibilityMessageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<CredibilityMessage> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public CredibilityMessage getById(Long id) {
        return credibilityMessageDao.findOne(id);
    }

    @Override
    public CredibilityMessage deleteById(Long id) {
        CredibilityMessage credibilityMessage = getById(id);
        credibilityMessageDao.delete(credibilityMessage);
        return credibilityMessage;
    }

    @Override
    public CredibilityMessage create(CredibilityMessage credibilityMessage) {
        return credibilityMessageDao.save(credibilityMessage);
    }

    @Override
    public CredibilityMessage update(CredibilityMessage credibilityMessage) {
        return credibilityMessageDao.save(credibilityMessage);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}