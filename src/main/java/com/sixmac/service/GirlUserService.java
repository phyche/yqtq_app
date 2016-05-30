package com.sixmac.service;

import com.sixmac.entity.GirlUser;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 2:30.
 */
public interface GirlUserService extends ICommonService<GirlUser> {

    public List<GirlUser> findByUserId(Integer userId);

}