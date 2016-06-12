package com.sixmac.service;

import com.sixmac.entity.SysExperience;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/6/12 0012 上午 11:44.
 */
public interface SysExperienceService extends ICommonService<SysExperience> {

    public SysExperience findByAction(Integer action);
}