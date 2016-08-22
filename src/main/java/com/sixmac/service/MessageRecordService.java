package com.sixmac.service;

import com.sixmac.entity.MessageRecord;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022 15:09.
 */
public interface MessageRecordService extends ICommonService<MessageRecord> {

    public List<MessageRecord> findByUserId(Long userId);

    public MessageRecord findByMessageId(Long messageId, Integer type);
}