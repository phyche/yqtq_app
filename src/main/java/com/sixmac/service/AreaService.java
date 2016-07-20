package com.sixmac.service;

import com.sixmac.entity.Area;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 6:00.
 */
public interface AreaService extends ICommonService<Area> {

    public Area getByAreaId(Long areaId);

    public List<Area> getByCityId(Long cityId);
}