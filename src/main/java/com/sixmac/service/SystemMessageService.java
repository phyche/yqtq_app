package com.sixmac.service;

import com.sixmac.entity.SystemMessage;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 下午 5:59.
 */
public interface SystemMessageService extends ICommonService<SystemMessage> {

    public List<SystemMessage> findByToUserId(Long userId);
}