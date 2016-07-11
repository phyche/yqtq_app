package com.sixmac.service;

import com.sixmac.entity.BigRace;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 12:05.
 */
public interface BigRaceService extends ICommonService<BigRace> {

    public Page<BigRace> page(Long cityId, Integer status, Integer pageNum, Integer pageSize);

    public BigRace getByStartDate(Long startDate);

    public List<BigRace> page(Long cityId, Integer status);
}