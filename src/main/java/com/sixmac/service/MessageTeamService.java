package com.sixmac.service;

import com.sixmac.entity.MessageTeam;
import com.sixmac.entity.Team;
import com.sixmac.service.common.ICommonService;

import java.util.List;

public interface MessageTeamService extends ICommonService<MessageTeam> {

    public List<MessageTeam> findByToUserId(Integer userId);

    public List<MessageTeam> findByTeam(Team team);
}
