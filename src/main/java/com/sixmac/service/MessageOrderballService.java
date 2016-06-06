package com.sixmac.service;

import com.sixmac.entity.MessageOrderBall;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 3:40.
 */
public interface MessageOrderBallService extends ICommonService<MessageOrderBall> {

    //根据用户id查询等待用户处理的好友约球消息
    public List<MessageOrderBall> findByToUserId(Integer userId);
}