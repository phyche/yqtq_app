package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.Constant;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.OrderBallVo;
import com.sixmac.entity.vo.WatchBallVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2016/5/17 0017.
 * 约散客
 */
@Controller
@RequestMapping(value = "/api/orderball")
public class OrderBallApi extends CommonController {

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


    /**
     * 完成
     *
     * @api {post} /api/orderball/list 约球列表
     * @apiName orderball.list
     * @apiGroup orderball
     * @apiParam {Integer} timelimit 时间期限
     * @apiParam {Integer} type 球赛类型 N人制 N代表数量
     * @apiParam {Integer} areaId 区域ID
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Integer} list.id 约球id
     * @apiSuccess {Integer} list.content 约球内容
     * @apiSuccess {Integer} list.matchType 赛制
     * @apiSuccess {Integer} list.joinCount 已报人数
     * @apiSuccess {Integer} list.lackCount 剩余人数
     * @apiSuccess {Object} list.user 创建人
     * @apiSuccess {String} list.user.nickname 创建人昵称
     * @apiSuccess {String} list.user.avater 创建人头像
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {String} list.stadium.name 球场名字
     * @apiSuccess {Long} list.startTime 开始时间
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     Integer timelimit,
                     Integer type,
                     Integer areaId,
                     Integer pageNum,
                     Integer pageSize) {

        initPageable(pageNum,pageSize);
        //球场、时间筛选有问题
        Page<Reserve> page = reserveService.page(timelimit, type, areaId, pageNum, pageSize);
        List<Reserve> list = page.getContent();
        for (Reserve reserve : list) {
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");
            reserve.setJoinCount(reserve.getList() != null ? reserve.getList().size() : 0);
            reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"set","insurance","site","list");
        WebUtil.printApi(response, result);
    }


    /**
     * 约球的详情(完成)
     * @api {post} /api/orderball/orderInfo 约球的详情
     * @apiName orderball.orderInfo
     * @apiGroup orderball
     * @apiParam {Integer} reserveId 约球ID <必传 />
     *
     * @apiSuccess {Object}  reserve 约球列表
     * @apiSuccess {Integer} reserve.id 约球id
     * @apiSuccess {Integer} reserve.content 约球内容
     * @apiSuccess {Integer} reserve.matchType 赛制
     * @apiSuccess {Integer} reserve.joinCount 已报人数
     * @apiSuccess {Integer} reserve.lackCount 剩余人数
     * @apiSuccess {Object} reserve.user 创建人
     * @apiSuccess {String} reserve.user.nickname 创建人昵称
     * @apiSuccess {String} reserve.user.avater 创建人头像
     * @apiSuccess {Object} reserve.stadium 球场
     * @apiSuccess {Integer} reserve.stadium.id 球场id
     * @apiSuccess {String} reserve.stadium.name 球场名字
     * @apiSuccess {Double} reserve.avePrice AA制金额
     * @apiSuccess {Double} reserve.sumPrice 支付总金额
     * @apiSuccess {Object} userList 已报名球友列表
     * @apiSuccess {Integer} userList.id 报名球友id
     * @apiSuccess {String} userList.nickname 报名球友昵称
     * @apiSuccess {String} userList.avater 报名球友头像
     * @apiSuccess {Long} reserve.startTime 开始时间
     *
     */
    @RequestMapping(value = "/orderInfo")
    public void orderInfo(HttpServletResponse response, Integer reserveId) {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (null == reserveId) {
                WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
                return;
            }

            Reserve reserve = reserveService.getById(reserveId);

            if (null == reserve) {
                WebUtil.printApi(response, new Result(false).msg(ErrorCode.ERROR_CODE_0003));
                return;
            }

            User user = reserveService.getById(reserveId).getUser();

            //已报名该预定的用户
            List<User> userList = new ArrayList<User>();
            List<UserReserve> userReserves = userReserveService.findByReserverId(reserveId);
            for (UserReserve userReserve : userReserves) {
                userList.add(userReserve.getUser());
            }

            //已报名该预定的用户数量
            reserve.setJoinCount(userList.size());
            //缺少的人数
            reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
            reserve.setAvePrice(reserve.getPrice() / reserve.getMatchType());
            reserve.setSumPrice(reserve.getAvePrice() + reserve.getInsurance().getPrice());
            reserveService.update(reserve);

            /*Insurance insurance = new Insurance();
            insurance.setReserve(reserve);
            insurance.setUser(userService.getById(userId));
            insurance.setMoney(reserve.getInsurance().getPrice());
            insuranceService.create(insurance);*/

            map.put("userList", userList);
            map.put("reserve", reserve);

            Result obj = new Result(true).data(map);
            String result = JsonUtil.obj2ApiJson(obj,"set","site","list");
            WebUtil.printApi(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成
     * * @api {post} /api/orderball/info 球友个人资料
     * @apiName orderball.info
     * @apiGroup orderball
     * @apiParam {Integer} playerId 球友ID <必传 />
     *
     * @apiSuccess {Object}  user 球友列表
     * @apiSuccess {String} user.avater 球友头像
     * @apiSuccess {String} user.nickname 球友昵称
     * @apiSuccess {Integer} user.vipNum 球友会员等级
     * @apiSuccess {Integer} user.credibility 球友信誉评分
     * @apiSuccess {Integer} user.age 球友年龄
     * @apiSuccess {Integer} user.gender 球友性别 0:男 1：女
     * @apiSuccess {Double} user.height 球友身高
     * @apiSuccess {Double} user.weight 球友体重
     * @apiSuccess {Integer} user.position 球友位置 0：前 1：中 2：后 3：守
     * @apiSuccess {Object} team 球友的球队
     * @apiSuccess {String} team.avater 球队队徽
     * @apiSuccess {String} team.name 球队名称
     * @apiSuccess {Integer} team.declareNum 球队宣战数
     * @apiSuccess {Integer} team.battleNum 球队应战数
     * @apiSuccess {Integer} team.count 球队总人数
     * @apiSuccess {Object} team.list 球队球员列表
     * @apiSuccess {Integer} team.list .id 球队球员id
     * @apiSuccess {String} team.list.avater 球队球员头像
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Integer playerId) {
        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.getById(playerId);

        //查询用户所属球队
        List<Team> team = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(user.getId());
        for (TeamMember teamMember : teamMemberList) {
            team.add(teamMember.getTeam());
        }


        map.put("user", user);
        map.put("team", team);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj, "address", "cityId", "slogan", "leaderUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     *  @api {post} /api/orderball/orderList 球友的约球列表
     * @apiName orderball.orderList
     * @apiGroup orderball
     * @apiParam {Integer} playerId 球友ID <必传 />
     *
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Integer} list.id 约球id
     * @apiSuccess {Integer} list.content 约球内容
     * @apiSuccess {Integer} list.matchType 赛制
     * @apiSuccess {Integer} list.joinCount 已报人数
     * @apiSuccess {Integer} list.lackCount 剩余人数
     * @apiSuccess {Object} list.user 创建人
     * @apiSuccess {String} list.user.nickname 创建人昵称
     * @apiSuccess {String} list.user.avater 创建人头像
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {String} list.stadium.name 球场名字
     * @apiSuccess {Long} list.startTime 开始时间
     *
     */
    @RequestMapping(value = "/orderList")
    public void orderList(HttpServletResponse response, Integer playerId) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<UserReserve> userReserves = userReserveService.findByUserId(playerId);
        for (UserReserve userReserve : userReserves) {

            reserveList.add(userReserve.getReserve());
        }
        for (Reserve reserve : reserveList) {
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");
            reserve.setJoinCount(reserve.getList() != null ? reserve.getList().size() : 0);
            reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
        }

        map.put("reserveList", reserveList);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"set","insurance","site","list");
        WebUtil.printApi(response, result);
    }


    /**
     * 完成
     *
     *  @api {post} /api/orderball/raceList 球友的赛事列表
     * @apiName orderball.raceList
     * @apiGroup orderball
     * @apiParam {Integer} playerId 球友ID <必传 />
     *
     * @apiSuccess {Object}  watchBallVos 球员所在球队为主队赛事列表
     * @apiSuccess {String} watchBallVos.homeTeamName 主队队名
     * @apiSuccess {String} watchBallVos.homeTeamAvater 主队队徽
     * @apiSuccess {String} watchBallVos.vTeamName 客队队名
     * @apiSuccess {String} watchBallVos.vTeamAvater 客队队徽
     * @apiSuccess {Integer} watchBallVos.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} watchBallVos.stadiumName 球场名称
     * @apiSuccess {Long} watchBallVos.startTime 开始时间
     *
     * @apiSuccess {Object}  watchBallVoList 球员所在球队为客队赛事列表
     * @apiSuccess {String} watchBallVoList.homeTeamName 主队队名
     * @apiSuccess {String} watchBallVoList.homeTeamAvater 主队队徽
     * @apiSuccess {String} watchBallVoList.vTeamName 客队队名
     * @apiSuccess {String} watchBallVoList.vTeamAvater 客队队徽
     * @apiSuccess {Integer} watchBallVoList.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} watchBallVoList.stadiumName 球场名称
     * @apiSuccess {Long} watchBallVoList.startTime 开始时间
     */
    @RequestMapping(value = "/raceList")
    public void raceList(HttpServletResponse response, Integer playerId) {

        Map<String, Object> map = new HashMap<String, Object>();

        List<Team> teams = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(playerId);
        for (TeamMember teamMember : teamMemberList) {
            teams.add(teamMember.getTeam());
        }

        String teamIds = "";
        StringBuffer buffer = null;
        for (Team team : teams) {
            //根据球队列表查询球队赛事
            // select * from t_race r  where r.host_id in(1,2,3) or r.visitingid in (1,2,3)
            buffer = new StringBuffer("");
            buffer.append(team.getId()).append(",");
        }
        teamIds = buffer.toString().substring(0, buffer.length() - 1);

        List<TeamRace> list = teamRaceService.findByHomeTeamId(teamIds);
        List<WatchBallVo> watchBallVos = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : list) {
            WatchBallVo watchBallVo1 = new WatchBallVo();
            watchBallVo1.setId(teamRace.getId());
            watchBallVo1.setStadiumName(teamRace.getStadium().getName());
            watchBallVo1.setStartTime(teamRace.getStartTime());
            watchBallVo1.setHomeTeamName(teamRace.getHomeTeam().getName());
            watchBallVo1.setHomeTeamAvater(teamRace.getHomeTeam().getAvater());
            watchBallVo1.setvTeamName(teamRace.getVisitingTeam().getName());
            watchBallVo1.setvTeamAvater(teamRace.getVisitingTeam().getAvater());
            watchBallVo1.setStatus(teamRace.getStatus());
            watchBallVos.add(watchBallVo1);
        }

        List<TeamRace> listrace = teamRaceService.findByVisitingId(teamIds);
        List<WatchBallVo> watchBallVoList = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : listrace) {
            WatchBallVo watchBallVo2 = new WatchBallVo();
            watchBallVo2.setId(teamRace.getId());
            watchBallVo2.setStadiumName(teamRace.getStadium().getName());
            watchBallVo2.setStartTime(teamRace.getStartTime());
            watchBallVo2.setHomeTeamName(teamRace.getHomeTeam().getName());
            watchBallVo2.setHomeTeamAvater(teamRace.getHomeTeam().getAvater());
            watchBallVo2.setvTeamName(teamRace.getVisitingTeam().getName());
            watchBallVo2.setvTeamAvater(teamRace.getVisitingTeam().getAvater());
            watchBallVo2.setStatus(teamRace.getStatus());

            if (watchBallVos.getClass().equals(watchBallVo2)) return;
            watchBallVoList.add(watchBallVo2);
        }

        map.put("watchBallVos", watchBallVos);
        map.put("watchBallVoList", watchBallVoList);

        try {
            System.out.println(JsonUtil.obj2Json(map));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/orderball/raceInfo 球友的赛事详情
     * @apiName orderball.raceInfo
     * @apiGroup orderball
     * @apiParam {Integer} userId 球友ID <必传 />
     * @apiParam {Integer} raceId 赛事ID <必传 />
     *
     * @apiSuccess {Object}  watchBallVo 球员赛事
     * @apiSuccess {String} watchBallVo.homeTeamName 主队队名
     * @apiSuccess {String} watchBallVo.homeTeamAvater 主队队徽
     * @apiSuccess {String} watchBallVo.vTeamName 客队队名
     * @apiSuccess {String} watchBallVo.vTeamAvater 客队队徽
     * @apiSuccess {Integer} watchBallVo.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} watchBallVo.stadiumName 球场名称
     * @apiSuccess {Long} watchBallVo.startTime 开始时间
     * @apiSuccess {Long} mobile 手机号
     */
    @RequestMapping(value = "/raceInfo")
    public void raceInfo(HttpServletResponse response, Integer userId, Integer raceId, String mobile) {

        Map<String, Object> map = new HashMap<String, Object>();

        TeamRace teamRace = teamRaceService.getById(raceId);

        if (teamRace.getHomeTeam().getLeaderUser().getId() == userId) {

            mobile = teamRace.getVisitingTeam().getLeaderUser().getMobile();
        }

        if (teamRace.getVisitingTeam().getLeaderUser().getId() == userId) {

            mobile = teamRace.getHomeTeam().getLeaderUser().getMobile();
        }

        //主队
        Team homeTeam = teamRace.getHomeTeam();
        //客队
        Team visitingTeam = teamRace.getVisitingTeam();

        WatchBallVo watchBallVo = new WatchBallVo();
        watchBallVo.setId(teamRace.getId());
        watchBallVo.setHomeTeamName(homeTeam.getName());
        watchBallVo.setHomeTeamAvater(homeTeam.getAvater());
        watchBallVo.setvTeamName(visitingTeam.getName());
        watchBallVo.setvTeamAvater(visitingTeam.getAvater());
        watchBallVo.setStatus(teamRace.getStatus());
        watchBallVo.setStartTime(teamRace.getStartTime());
        watchBallVo.setStadiumName(teamRace.getStadium().getName());

        map.put("watchBallVo", watchBallVo);
        map.put("mobile", mobile);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }


    /**
     * 球友的帖子
     *
     * 与USERAPI里我的帖子相同
     */

    /**
     * 完成
     *
     * @api {post} /api/orderball/pay 球友的约球支付
     * @apiName orderball.pay
     * @apiGroup orderball
     * @apiParam {Integer} reserveId 约球ID <必传 />
     * @apiParam {Integer} userId 赛事ID <必传 />
     *
     * @apiSuccess {Object} order 订单
     * @apiSuccess {String} order.userName 用户昵称
     * @apiSuccess {String} order.stadiumName 球场名称
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response, Integer reserveId, Integer userId, Double money) {

        Reserve reserve = reserveService.getById(reserveId);

        Insurance insurance = new Insurance();

        //球场已经全额付款的
        if (reserve.getPayment() == 0) {
            insurance.setMoney(0.0);
            money = 0.0;
        }
        //球场AA付款
        if (reserve.getPayment() == 1) {
            insurance.setMoney(reserve.getInsurance().getPrice());
            money = reserve.getPrice() / reserve.getMatchType() + reserve.getInsurance().getPrice();
        }

        insurance.setUser(userService.getById(userId));
        insurance.setReserve(reserve);
        insuranceService.create(insurance);

        String sn = CommonUtils.generateSn(); // 订单号
        Order order = new Order();
        order.setUsername(userService.getById(userId).getNickname());
        order.setStadiumname(reserve.getStadium().getName());
        order.setPrice(money);
        order.setSn(sn);
        orderService.create(order);

        UserReserve userReserve = new UserReserve();
        userReserve.setUser(userService.getById(userId));
        userReserve.setReserve(reserve);
        userReserve.setStatus(0);
        userReserveService.create(userReserve);;

        Result obj = new Result(true).data(order);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
