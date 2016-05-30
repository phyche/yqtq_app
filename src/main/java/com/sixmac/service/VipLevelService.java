package com.sixmac.service;

import com.sixmac.entity.VipLevel;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 4:42.
 */
public interface VipLevelService extends ICommonService<VipLevel> {

    public VipLevel findBylevel(Integer level);
}