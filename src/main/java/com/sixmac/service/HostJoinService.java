package com.sixmac.service;

import com.sixmac.entity.HostJoin;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/25 0025 上午 11:15.
 */
public interface HostJoinService extends ICommonService<HostJoin> {

    public List<HostJoin> findByHostRaceId(Long raceId);

}