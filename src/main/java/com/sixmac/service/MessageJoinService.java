package com.sixmac.service;

import com.sixmac.entity.MessageJoin;
import com.sixmac.entity.Team;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003 下午 2:34.
 */
public interface MessageJoinService extends ICommonService<MessageJoin> {

    public List<MessageJoin> findByTeam(Team team);

    public List<MessageJoin> findByUserId(Long userId);
}