package com.sixmac.service;

import com.sixmac.entity.MessageAdd;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 2:59.
 */
public interface MessageAddService extends ICommonService<MessageAdd> {

    public List<MessageAdd> findByUserId(Integer userId);

    public List<MessageAdd> findByToUserId(Integer userId);
}