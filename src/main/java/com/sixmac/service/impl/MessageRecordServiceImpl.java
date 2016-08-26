package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.MessageRecordDao;
import com.sixmac.entity.MessageRecord;
import com.sixmac.service.MessageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022 15:09.
 */
@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    @Autowired
    private MessageRecordDao messageRecordDao;

    @Override
    public List<MessageRecord> findAll() {
        return messageRecordDao.findAll();
    }

    @Override
    public Page<MessageRecord> find(int pageNum, int pageSize) {
        return messageRecordDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<MessageRecord> find(int pageNum) {
        return null;
    }

    @Override
    public MessageRecord getById(Long id) {
        return messageRecordDao.findOne(id);
    }

    @Override
    public MessageRecord deleteById(Long id) {
        MessageRecord messageRecord = getById(id);
        messageRecordDao.delete(messageRecord);
        return messageRecord;
    }

    @Override
    public MessageRecord create(MessageRecord messageRecord) {
        return messageRecordDao.save(messageRecord);
    }

    @Override
    public MessageRecord update(MessageRecord messageRecord) {
        return messageRecordDao.save(messageRecord);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<MessageRecord> findByUserId(Long userId) {
        return messageRecordDao.findByUserId(userId);
    }

    @Override
    public List<MessageRecord> findByMessageId(Long messageId, Integer type) {
        return messageRecordDao.findByMessageId(messageId, type);
    }
}