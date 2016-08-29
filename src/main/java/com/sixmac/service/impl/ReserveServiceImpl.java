package com.sixmac.service.impl;

import com.sixmac.controller.api.PayCallBackApi;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.Constant;
import com.sixmac.core.bean.Result;
import com.sixmac.dao.ReserveDao;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.WatchBallVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
 * Created by Administrator on 2016/5/19 0019 上午 11:54.
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private UserReserveService userReserveService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private TeamRaceService teamRaceService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageOrderBallService messageOrderBallService;

    @Autowired
    private SysExperienceService sysExperienceService;

    @Autowired
    private SysCredibilityService sysCredibilityService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MessageRecordService messageRecordService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Reserve> findAll() {
        return reserveDao.findAll();
    }

    @Override
    public Page<Reserve> find(int pageNum, int pageSize) {
        return reserveDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Reserve> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Reserve getById(Long id) {
        return reserveDao.findOne(id);
    }

    @Override
    public Reserve deleteById(Long id) {
        Reserve booking = getById(id);
        reserveDao.delete(booking);
        return booking;
    }

    @Override
    public Reserve create(Reserve booking) {
        return reserveDao.save(booking);
    }

    @Override
    public Reserve update(Reserve booking) {
        return reserveDao.save(booking);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<Reserve> findByUserId(Long userId) {
        return reserveDao.findByUserId(userId);
    }

    @Override
    public Page<Reserve> page(final Integer timelimit, final Integer type, final Long areaId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Reserve> page = reserveDao.findAll(new Specification<Reserve>() {
            @Override
            public Predicate toPredicate(Root<Reserve> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if(timelimit != null) {
                    long beforeDate = DateUtils.dateAddDay(timelimit * -1).getTime();
                    predicateList.add(cb.between(root.get("startTime").as(Long.class),beforeDate,System.currentTimeMillis()));
                }

                if (type != null) {
                    Predicate pre = cb.equal(root.get("matchType").as(Integer.class), type);
                    predicateList.add(pre);
                }

                if (areaId != null) {
                    Predicate pre = cb.equal(root.get("stadium").get("areaId").as(Integer.class), areaId);
                    predicateList.add(pre);
                }

                Predicate pre = cb.equal(root.get("payStatus").as(Integer.class), 1);
                predicateList.add(pre);

                Predicate pre2 = cb.equal(root.get("reserveType").as(Integer.class), 0);
                predicateList.add(pre2);

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
    public List<Reserve> findNew() {

        String params = "0,1";
        Query query = em.createQuery("SELECT r from Reserve r where r.status in (" + params +") and r.reserveType = 0 order by r.id desc",Reserve.class);
        query.setMaxResults(1);
        List<Reserve> reserveList = query.getResultList();
        return reserveList;
    }

    @Override
    public void order(HttpServletResponse response, Long reserveId, Long userId, Long toUserId) {
        MessageOrderBall messageOrderBall = new MessageOrderBall();

        messageOrderBall.setStatus(0);
        messageOrderBall.setUser(userService.getById(userId));
        messageOrderBall.setToUser(userService.getById(toUserId));
        messageOrderBall.setReserve(reserveService.getById(reserveId));
        messageOrderBallService.create(messageOrderBall);

        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setUserId(toUserId);
        messageRecord.setStatus(0);
        messageRecord.setMessageId(messageOrderBall.getId());
        messageRecord.setType(0);
        messageRecordService.create(messageRecord);
    }

    @Override
    public Map<String, Object> raceList(HttpServletResponse response, Long playerId) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Team> teams = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(playerId);
        User user = null;
        for (TeamMember teamMember : teamMemberList) {
            user = userService.getById(teamMember.getUserId());
            if (StringUtils.isNotBlank(user.getAvater())) {
                user.setAvater(ConfigUtil.getString("upload.url") + user.getAvater());
            }
            teams.add(teamService.getById(teamMember.getTeamId()));
        }

        String teamIds = "";
        StringBuffer buffer = new StringBuffer("");
        for (Team team : teams) {
            //根据球队列表查询球队赛事
            // select * from t_race r  where r.host_id in(1,2,3) or r.visitingid in (1,2,3)
            buffer.append(team.getId() + ",");
        }
        teamIds = buffer.toString().substring(0, buffer.length() - 1);

        List<TeamRace> list = teamRaceService.findByHomeTeamId(teamIds);
        List<WatchBallVo> watchBallVos = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : list) {
            WatchBallVo watchBallVo1 = new WatchBallVo();
            if (teamRace.getHomeTeam().getLeaderUser().getId() == playerId) {
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

        List<TeamRace> listrace = teamRaceService.findByVisitingId(teamIds);
        List<WatchBallVo> watchBallVoList = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : listrace) {
            WatchBallVo watchBallVo2 = new WatchBallVo();
            if (teamRace.getVisitingTeam().getLeaderUser().getId() == playerId) {
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

            if (watchBallVos.getClass().equals(watchBallVo2)) return map;
            watchBallVoList.add(watchBallVo2);
        }

        map.put("watchBallVos", watchBallVos);
        map.put("watchBallVoList", watchBallVoList);

        try {
            System.out.println(JsonUtil.obj2Json(map));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Order pay(HttpServletResponse response, Long reserveId, Long userId, Long messageId, Double money) {
        Reserve reserve = reserveService.getById(reserveId);
        Order order = null;
        if (reserve.getType() == 0) {
            if (reserve.getJoinCount() < reserve.getMatchType() * 2) {

                Insurance insurance = new Insurance();

                //球场已经全额付款的
                if (reserve.getPayment() == 0) {
                    UserReserve userReserve = new UserReserve();
                    userReserve.setUser(userService.getById(userId));
//                    userReserve.setReserveId(reserveId);
                    userReserve.setReserve(reserve);
                    userReserve.setStatus(0);
                    userReserveService.create(userReserve);

                    MessageRecord messageRecord = new MessageRecord();
                    messageRecord.setUserId(reserve.getUser().getId());
                    messageRecord.setStatus(0);
                    messageRecord.setMessageId(userReserve.getId());
                    messageRecord.setType(1);
                    messageRecordService.create(messageRecord);

                    if (messageId != null) {
                        MessageOrderBall messageOrderBall = messageOrderBallService.getById(messageId);
                        if (messageRecord != null) {
                            messageRecord.setStatus(1);
                            messageRecordService.update(messageRecord);
                        }
                    }

                    WebUtil.printApi(response, new Result(true));
                }
                //球场AA付款
                if (reserve.getPayment() == 1) {
                    insurance.setMoney(reserve.getInsurance().getPrice());

                    money = reserve.getPrice() / reserve.getMatchType();
                    String sn = CommonUtils.generateSn(); // 订单号

                    order = new Order();
                    order.setUser(userService.getById(userId));
                    order.setReserve(reserve);
                /*order.setStadium(reserve.getStadium());
                order.setSite(reserve.getSite());*/
                    order.setPrice(money);
                    order.setAction(1);
                    order.setSn(sn);
                    order.setMessageId(messageId);
                    orderService.create(order);

                    // 当前没有支付接口，因此状态直接为已支付
                    PayCallBackApi.changeOrderStatus(orderService, order.getSn(), null, response);
                }

                insurance.setUserId(userId);
                insurance.setReserveId(reserve.getId());
                insuranceService.create(insurance);
            }
        }else if (reserve.getType() == 1){
            UserReserve userReserve = new UserReserve();
            userReserve.setUser(userService.getById(userId));
//            userReserve.setReserveId(reserveId);
            userReserve.setReserve(reserve);
            userReserve.setStatus(0);
            userReserveService.create(userReserve);

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(reserve.getUser().getId());
            messageRecord.setStatus(0);
            messageRecord.setMessageId(userReserve.getId());
            messageRecord.setType(1);
            messageRecordService.create(messageRecord);

            if (messageId != null) {
                MessageOrderBall messageOrderBall = messageOrderBallService.getById(messageId);
                if (messageRecord != null) {
                    messageRecord.setStatus(1);
                    messageRecordService.update(messageRecord);
                }
            }

            WebUtil.printApi(response, new Result(true));
        }

        return order;
    }

}