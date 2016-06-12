package com.sixmac.service;

import com.sixmac.entity.SysCredibility;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/6/12 0012 上午 11:48.
 */
public interface SysCredibilityService extends ICommonService<SysCredibility> {

    public SysCredibility findByAction(Integer action);
}