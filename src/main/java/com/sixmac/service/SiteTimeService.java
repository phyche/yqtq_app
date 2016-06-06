package com.sixmac.service;

import com.sixmac.entity.SiteTime;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006 上午 10:34.
 */
public interface SiteTimeService extends ICommonService<SiteTime> {

    public List<SiteTime> findBySiteId(Integer siteId);

    public SiteTime findBySiteAndTime(Integer siteId, Long time);
}