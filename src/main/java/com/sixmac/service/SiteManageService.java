package com.sixmac.service;

import com.sixmac.entity.SiteManage;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/6/6 0006 上午 10:35.
 */
public interface SiteManageService extends ICommonService<SiteManage> {

    public SiteManage findBySiteAndTime(Integer siteId, Long time);
}