package com.sixmac.service;

import com.sixmac.entity.UserVip;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/6/12 0012 上午 10:07.
 */
public interface UserVipService extends ICommonService<UserVip> {

    public UserVip findByUserId(Long userId);
}