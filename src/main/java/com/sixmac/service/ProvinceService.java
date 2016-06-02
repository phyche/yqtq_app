package com.sixmac.service;

import com.sixmac.entity.Province;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:00.
 */
public interface ProvinceService extends ICommonService<Province> {

    public Province getByProvinceId(Integer provinceId);
}