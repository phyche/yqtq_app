package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.TeamRaceDao;
import com.sixmac.entity.TeamMember;
import com.sixmac.entity.TeamRace;
import com.sixmac.service.TeamRaceService;

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
import javax.persistence.Query;
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
    private EntityManagerFactory factory;

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
        EntityManager em = factory.createEntityManager();
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
        EntityManager em = factory.createEntityManager();
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

        EntityManager em = factory.createEntityManager();
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

}