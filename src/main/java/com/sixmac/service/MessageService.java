package com.sixmac.service;

import com.sixmac.entity.Message;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:42.
 */
public interface MessageService extends ICommonService<Message> {

    public Message getByType(Integer type);
}