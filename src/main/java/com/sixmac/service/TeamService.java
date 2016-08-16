package com.sixmac.service;

import com.sixmac.entity.Team;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018 上午 9:55.
 */
public interface TeamService extends ICommonService<Team> {

    public Page<Team> page(String name, Long cityId, Integer pageNum, Integer pageSize);

    public Team findListByLeaderId(Long leaderId);

}