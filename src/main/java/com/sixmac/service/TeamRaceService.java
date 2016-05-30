package com.sixmac.service;

import com.sixmac.entity.TeamRace;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018 下午 4:45.
 */
public interface TeamRaceService extends ICommonService<TeamRace> {

    //public List<TeamRace> findByTeamId(Integer teamId);

    public List<TeamRace> findByHomeTeamId(String homeId);

    public List<TeamRace> findByVisitingId(String visitingId);

    public List<TeamRace> findHomeId(Integer homeId);

    public List<TeamRace> findVisitingId(Integer visitingId);
}