package com.sixmac.service;

import com.sixmac.entity.Information;
import com.sixmac.entity.Team;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/19 0019 上午 10:08.
 */
public interface InformationService extends ICommonService<Information> {

    public Page<Information> page(Integer pageNum, Integer pageSize);

}