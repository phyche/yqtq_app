package com.sixmac.service;

import com.sixmac.entity.SysInsurance;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 1:53.
 */
public interface SysInsuranceService extends ICommonService<SysInsurance> {

    public List<SysInsurance> findList();
}