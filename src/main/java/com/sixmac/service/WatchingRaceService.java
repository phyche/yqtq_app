package com.sixmac.service;

import com.sixmac.entity.WatchingRace;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/23 0023 上午 11:20.
 */
public interface WatchingRaceService extends ICommonService<WatchingRace> {

    public Page<WatchingRace> page(Integer pageNum, Integer pageSize);
}