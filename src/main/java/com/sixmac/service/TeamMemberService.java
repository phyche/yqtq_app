package com.sixmac.service;

import com.sixmac.entity.TeamMember;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 上午 9:42.
 */
public interface TeamMemberService extends ICommonService<TeamMember> {

    public List<TeamMember> findByUserId(Long userId);

    //public List<TeamMember> findByTeamId(Long teamId);
}