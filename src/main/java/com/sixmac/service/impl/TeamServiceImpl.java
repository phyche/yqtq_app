package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.TeamDao;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/18 0018 上午 9:55.
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TeamRaceService teamRaceService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private MessageJoinService messageJoinService;

    @Autowired
    private MessageTeamService messageTeamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private MessageRecordService messageRecordService;

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public Page<Team> find(int pageNum, int pageSize) {
        return teamDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Team> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Team getById(Long id) {
        return teamDao.findOne(id);
    }

    @Override
    public Team deleteById(Long id) {
        Team team = getById(id);
        teamDao.delete(team);
        return team;
    }

    @Override
    public Team create(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team update(Team team) {
        return teamDao.save(team);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Team> page(final String name, final Long cityId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Team> page = teamDao.findAll(new Specification<Team>() {
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (name != null) {
                    Predicate pre = cb.like(root.get("name").as(String.class), "%" + name + "%");
                    predicateList.add(pre);
                }

                if (cityId != null) {
                    Predicate pre = cb.equal(root.get("cityId").as(Long.class), cityId);
                    predicateList.add(pre);
                }

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    @Override
    public Team findListByLeaderId(Long leaderId) {
        return teamDao.findListByLeaderId(leaderId);
    }

    @Override
    @Transactional
    public Map<String, Object> info(HttpServletResponse response, Long teamId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer sum = 0;
        double sumAge = 0;
        double sumWeight = 0.0;
        double sumHeight = 0.0;

        Team team = teamDao.findOne(teamId);
        if (StringUtils.isNotBlank(team.getAvater())) {
            team.setAvater(ConfigUtil.getString("upload.url") + team.getAvater());
        }
        if (StringUtils.isNotBlank(team.getLeaderUser().getAvater())) {
            team.getLeaderUser().setAvater(ConfigUtil.getString("upload.url") + team.getLeaderUser().getAvater());
        }

        List<User> userList = new ArrayList<User>();
        if (team.getList().size() != 0) {
            for (sum = 0; sum < team.getList().size(); sum++) {
                sumAge += team.getList().get(sum).getAge();
                sumHeight += team.getList().get(sum).getHeight();
                sumWeight += team.getList().get(sum).getWeight();
            }

            sumAge = sumAge + teamDao.findOne(teamId).getLeaderUser().getAge();
            sumHeight = sumHeight + teamDao.findOne(teamId).getLeaderUser().getHeight();
            sumWeight = sumWeight + teamDao.findOne(teamId).getLeaderUser().getWeight();

            team.setAveAge(sumAge / (team.getList().size()+1));
            team.setAveHeight(sumHeight / (team.getList().size()+1));
            team.setAveWeight(sumWeight / (team.getList().size()+1));

            //球员列表
            userList = team.getList();
            for (User user : userList) {
                if (StringUtils.isNotBlank(user.getAvater())) {
                    user.setAvater(ConfigUtil.getString("upload.url") + user.getAvater());
                }
            }
            team.setCount(userList.size());
        }else {
            sumAge = sumAge + teamDao.findOne(teamId).getLeaderUser().getAge();
            sumHeight = sumHeight + teamDao.findOne(teamId).getLeaderUser().getHeight();
            sumWeight = sumWeight + teamDao.findOne(teamId).getLeaderUser().getWeight();

            team.setAveAge(sumAge);
            team.setAveHeight(sumHeight);
            team.setAveWeight(sumWeight);
        }


        map.put("team", team);
        map.put("userList",userList);
        return map;
    }

    @Override
    @Transactional
    public void addFriend(HttpServletResponse response, Long userId, Long toUserId) {
        Team team = teamDao.findListByLeaderId(userId);
        if (team != null) {
            MessageTeam messageTeam = new MessageTeam();
            messageTeam.setUser(userService.getById(userId));
            messageTeam.setToUser(userService.getById(toUserId));
            messageTeam.setTeam(team);
            messageTeam.setStatus(0);
            messageTeamService.create(messageTeam);

            /*// 邀请加入球队
            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(userId);
            messageRecord.setStatus(0);
            messageRecord.setMessageId(messageTeam.getId());
            messageRecord.setType(9);
            messageRecordService.create(messageRecord);*/

            // 好友被邀请加入球队
            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(toUserId);
            messageRecord.setStatus(0);
            messageRecord.setMessageId(messageTeam.getId());
            messageRecord.setType(8);
            messageRecordService.create(messageRecord);
        }

    }

    @Override
    @Transactional
    public void apply(HttpServletResponse response, Long userId, Long teamId) {
        MessageJoin messageJoin = new MessageJoin();
        messageJoin.setStatus(0);
        messageJoin.setUser(userService.getById(userId));
        messageJoin.setTeam(teamDao.findOne(teamId));
        messageJoinService.create(messageJoin);

        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setUserId(userId);
        messageRecord.setStatus(0);
        messageRecord.setMessageId(messageJoin.getId());
        messageRecord.setType(10);
        messageRecordService.create(messageRecord);
    }

    @Override
    @Transactional
    public void orderInfo(HttpServletResponse response, Long team1Id, Long team2Id, Long time, Long cityId) {

        //被约球球队(客队)
        Team team1 = teamDao.findOne(team2Id);
        //约球球队（主队）
        Team team2 = teamDao.findOne(team1Id);

        TeamRace teamRace = new TeamRace();
        teamRace.setHomeTeam(team2);
        teamRace.setVisitingTeam(team1);
        teamRace.setStartTime(time);
        teamRaceService.create(teamRace);

        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setUserId(team1.getLeaderUser().getId());
        messageRecord.setStatus(0);
        messageRecord.setMessageId(teamRace.getId());
        messageRecord.setType(13);
        messageRecordService.create(messageRecord);
    }
}