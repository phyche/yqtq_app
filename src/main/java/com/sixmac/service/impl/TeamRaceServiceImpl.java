package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.TeamRaceDao;
import com.sixmac.entity.TeamMember;
import com.sixmac.entity.TeamRace;
import com.sixmac.entity.vo.WatchBallVo;
import com.sixmac.service.TeamRaceService;

import com.sixmac.service.TeamService;
import com.sixmac.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/20 0020 下午 3:17.
 */
@Service
public class TeamRaceServiceImpl implements TeamRaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRaceServiceImpl.class);

    @Autowired
    private TeamRaceDao teamRaceDao;

    @Autowired
    private TeamService teamService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TeamRace> findAll() {
        return teamRaceDao.findAll();
    }

    @Override
    public Page<TeamRace> find(int pageNum, int pageSize) {
        return teamRaceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<TeamRace> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public TeamRace getById(Long id) {
        return teamRaceDao.findOne(id);
    }

    @Override
    public TeamRace deleteById(Long id) {
        TeamRace teamRace = getById(id);
        teamRaceDao.delete(teamRace);
        return teamRace;
    }

    @Override
    public TeamRace create(TeamRace teamRace) {
        return teamRaceDao.save(teamRace);
    }

    @Override
    public TeamRace update(TeamRace teamRace) {
        return teamRaceDao.save(teamRace);
    }


    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<TeamRace> findByHomeTeamId(String homeId) {
        Long startDate = System.currentTimeMillis();
        String sql = "select a from TeamRace a where a.homeTeam.id in (" + homeId + ") and a.status = 1";
        Query query = em.createQuery(sql);
        List<TeamRace> list = (List<TeamRace>) query.getResultList();
        Long endDate = System.currentTimeMillis();
//        return teamRaceDao.findByHomeTeamId(homeId);
        LOGGER.info("findByHomeTeamId == 查询时间:" + (endDate - startDate) / 1000.0 + "秒");
        return list;
    }

    @Override
    public List<TeamRace> findByVisitingId(String visitingId) {
        Long startDate = System.currentTimeMillis();
        String sql = "select a from TeamRace a where a.visitingTeam.id in (" + visitingId + ") and a.status = 1";
        Query query = em.createQuery(sql);
        List<TeamRace> list = (List<TeamRace>) query.getResultList();

        Long endDate = System.currentTimeMillis();
        LOGGER.info("findByVisitingId == 查询时间:" + (endDate - startDate) / 1000.0 + "秒");
        return list;
    }

    @Override
    public List<TeamRace> findHomeId(Long homeId) {
        return teamRaceDao.findHomeId(homeId);
    }

    @Override
    public List<TeamRace> findVisitingId(Long visitingId) {
        return teamRaceDao.findVisitingId(visitingId);
    }

    @Override
    public List<TeamRace> findByTeamId(List<TeamMember> list) {

        StringBuffer buffer = new StringBuffer("");
        for (TeamMember teamMember : list) {
            buffer.append(teamMember.getTeamId()).append(",");
        }
        String params = buffer.toString().substring(0, buffer.length() - 1);
        String sql = "select a from TeamRace a where (a.visitingTeam.id in (" + params + ") or a.homeTeam.id in (" + params + ")) and a.status = 1 order by a.id desc";
        Query query = em.createQuery(sql, TeamRace.class);
        query.setMaxResults(3);

        List<TeamRace> _list = query.getResultList();
        return _list;
    }

    @Override
    public Map<String, Object> schedule(HttpServletResponse response, Long teamId, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();

        //球队为主队的球赛
        List<TeamRace> teamRaces = teamRaceDao.findHomeId(teamId);

        WatchBallVo watchBallVo1 = null;
        List<WatchBallVo> watchBallVos = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : teamRaces) {
            watchBallVo1 = new WatchBallVo();
            if (teamRace.getHomeTeam().getLeaderUser().getId() == userId) {
                watchBallVo1.setMobile(teamRace.getVisitingTeam().getLeaderUser().getMobile());
            }
            watchBallVo1.setId(teamRace.getId());
            watchBallVo1.setStadiumName(teamRace.getAddress());
            watchBallVo1.setStartTime(teamRace.getStartTime());
            watchBallVo1.setCreateDate(teamRace.getCreateDate());
            watchBallVo1.setHomeTeamName(teamRace.getHomeTeam().getName());
            if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
                watchBallVo1.setHomeTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
            }
            watchBallVo1.setvTeamName(teamRace.getVisitingTeam().getName());
            if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
                watchBallVo1.setvTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
            }
            watchBallVo1.setStatus(teamRace.getStatus());
            watchBallVos.add(watchBallVo1);
        }

        //球队为客队的球赛
        List<TeamRace> teamRaces1 = teamRaceDao.findVisitingId(teamId);
        WatchBallVo watchBallVo2 = null;
        List<WatchBallVo> watchBallVoList = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : teamRaces1) {

            watchBallVo2 = new WatchBallVo();
            if (teamRace.getVisitingTeam().getLeaderUser().getId() == userId) {
                watchBallVo2.setMobile(teamRace.getHomeTeam().getLeaderUser().getMobile());
            }
            watchBallVo2.setId(teamRace.getId());
            watchBallVo2.setStadiumName(teamRace.getAddress());
            watchBallVo2.setStartTime(teamRace.getStartTime());
            watchBallVo2.setHomeTeamName(teamRace.getHomeTeam().getName());
            watchBallVo2.setCreateDate(teamRace.getCreateDate());
            if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
                watchBallVo2.setHomeTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
            }
            watchBallVo2.setvTeamName(teamRace.getVisitingTeam().getName());
            if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
                watchBallVo2.setvTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
            }
            watchBallVo2.setStatus(teamRace.getStatus());
            watchBallVoList.add(watchBallVo2);
        }

        map.put("watchBallVos", watchBallVos);
        map.put("watchBallVoList", watchBallVoList);

        return map;
    }

}