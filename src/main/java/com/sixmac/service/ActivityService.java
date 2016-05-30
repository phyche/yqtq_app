package com.sixmac.service;

import com.sixmac.entity.Activity;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/19 0019 上午 10:08.
 */
public interface ActivityService extends ICommonService<Activity> {

    public Page<Activity> page(Integer pageNum, Integer pageSize);
}