package com.sixmac.service;

import com.sixmac.entity.Stadium;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017 下午 5:31.
 */
public interface StadiumService extends ICommonService<Stadium> {

    //根据区域、类型筛选场地
    public Page<Stadium> page(Long areaId, Integer type, Integer pageNum, Integer pageSize);

}