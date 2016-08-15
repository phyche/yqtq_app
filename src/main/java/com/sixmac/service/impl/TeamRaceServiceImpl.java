package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.TeamRaceDao;
import com.sixmac.entity.TeamRace;
import com.sixmac.service.TeamRaceService;

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

        String sql = "select a from TeamRace a where a.homeTeam.id in (" + homeId + ") and a.status = 1";
        Query query = em.createQuery(sql);
        List<TeamRace> list = (List<TeamRace>) query.getResultList();
//        return teamRaceDao.findByHomeTeamId(homeId);
        return list;
    }

    @Override
    public List<TeamRace> findByVisitingId(String visitingId) {
        EntityManager em = factory.createEntityManager();

        String sql = "select a from TeamRace a where a.visitingTeam.id in (" + visitingId + ") and a.status = 1";
        Query query = em.createQuery(sql);
        List<TeamRace> list = (List<TeamRace>) query.getResultList();

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

}