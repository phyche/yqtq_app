package com.sixmac.service;

import com.sixmac.entity.MessageWatching;
import com.sixmac.service.common.ICommonService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:01.
 */
public interface MessageWatchingService extends ICommonService<MessageWatching> {

    //根据用户id查询邀请用户看球消息
    public List<MessageWatching> findByToUserId(Long userId);

    // 看球邀请
    public void inviteBall(HttpServletResponse response,
                           Integer type,
                           Long id,
                           Long userId,
                           Long toUserId);
}