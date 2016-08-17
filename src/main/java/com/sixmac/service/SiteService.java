package com.sixmac.service;

import com.sixmac.entity.Site;
import com.sixmac.entity.Stadium;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 3:52.
 */
public interface SiteService extends ICommonService<Site> {

    public List<Site> findByStadiumId(Long stadiumId);

    //根据区域、类型筛选场地
    public List<Site> page(Integer type);
}