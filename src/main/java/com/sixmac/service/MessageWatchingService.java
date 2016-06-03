package com.sixmac.service;

import com.sixmac.entity.MessageWatching;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:01.
 */
public interface MessageWatchingService extends ICommonService<MessageWatching> {

    public List<MessageWatching> findByToUserId(Integer userId);
}