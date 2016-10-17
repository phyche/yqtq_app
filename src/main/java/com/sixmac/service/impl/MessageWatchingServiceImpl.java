package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.*;
import com.sixmac.entity.BigRace;
import com.sixmac.entity.MessageRecord;
import com.sixmac.entity.MessageWatching;
import com.sixmac.service.BigRaceService;
import com.sixmac.service.MessageRecordService;
import com.sixmac.service.MessageWatchingService;
import com.sixmac.service.WatchingRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:01.
 */
@Service
public class MessageWatchingServiceImpl implements MessageWatchingService {

    @Autowired
    private MessageWatchingDao messageWatchingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WatchingRaceDao watchingRaceDao;

    @Autowired
    private BigRaceDao bigRaceDao;

    @Autowired
    private MessageRecordDao messageRecordDao;

    @Override
    public List<MessageWatching> findAll() {
        return messageWatchingDao.findAll();
    }

    @Override
    public Page<MessageWatching> find(int pageNum, int pageSize) {
        return messageWatchingDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<MessageWatching> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public MessageWatching getById(Long id) {
        return messageWatchingDao.findOne(id);
    }

    @Override
    public MessageWatching deleteById(Long id) {
        MessageWatching messageWatching = getById(id);
        messageWatchingDao.delete(messageWatching);
        return messageWatching;
    }

    @Override
    public MessageWatching create(MessageWatching messageWatching) {
        return messageWatchingDao.save(messageWatching);
    }

    @Override
    public MessageWatching update(MessageWatching messageWatching) {
        return messageWatchingDao.save(messageWatching);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<MessageWatching> findByToUserId(Long userId) {
        return messageWatchingDao.findByToUserId(userId);
    }

    @Override
    public void inviteBall(HttpServletResponse response, Integer type, Long id, Long userId, Long toUserId) {
        MessageWatching messageWatching = new MessageWatching();
        messageWatching.setUser(userDao.findOne(userId));
        messageWatching.setType(type);
        messageWatching.setToUser(userDao.findOne(toUserId));
        // 类型：0：现场看球，1：直播看球
        if (type == 0) {
            messageWatching.setBigRace(bigRaceDao.findOne(id));
        } else if (type == 1) {
            messageWatching.setWatchingRace(watchingRaceDao.findOne(id));
        }
        messageWatchingDao.save(messageWatching);

        // 新增约看消息
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setUserId(toUserId);
        messageRecord.setStatus(0);
        messageRecord.setMessageId(messageWatching.getId());
        // 类型（3：约看）
        messageRecord.setType(3);
        messageRecordDao.save(messageRecord);
    }
}