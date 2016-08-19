package com.sixmac.service;

import com.sixmac.entity.HostRace;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 11:21.
 */
public interface HostRaceService extends ICommonService<HostRace> {

    public List<HostRace> findNew();
}