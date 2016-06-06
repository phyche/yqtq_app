package com.sixmac.service;

import com.sixmac.entity.MessageWatching;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:01.
 */
public interface MessageWatchingService extends ICommonService<MessageWatching> {

    //根据用户id查询邀请用户看球消息
    public List<MessageWatching> findByToUserId(Integer userId);
}