package com.sixmac.service;

import com.sixmac.entity.MessageAdd;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 2:59.
 */
public interface MessageAddService extends ICommonService<MessageAdd> {

    //根据用户id查询用户添加成功或者失败的好友消息
    public List<MessageAdd> findByUserId(Long userId);

    //根据用户id查询等待用户处理的好友添加消息
    public List<MessageAdd> findByToUserId(Long userId);

    //根据用户id查询用户添加成功的好友消息
    public List<MessageAdd> findUserId(Long userId);
}