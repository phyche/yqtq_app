package com.sixmac.service;

import com.sixmac.entity.Reserve;
import com.sixmac.entity.UserReserve;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019 上午 11:54.
 */
public interface ReserveService extends ICommonService<Reserve> {

    public List<Reserve> findByUserId(Long userId);

    public Page<Reserve> page(Integer timelimit, Integer type, Long areaId, Integer pageNum, Integer pageSize);

}