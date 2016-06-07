package com.sixmac.service;

import com.sixmac.entity.EventInformation;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 4:09.
 */
public interface EventInformationService extends ICommonService<EventInformation> {

    public EventInformation findByRaceId(Long raceId);
}