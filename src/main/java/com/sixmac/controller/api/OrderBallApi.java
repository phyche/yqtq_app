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
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "/api/orderBall")
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

    @Autowired
    private OrderballMessageService orderballMessageService;

    @Autowired
    private MessageOrderBallService messageOrderBallService;

    @Autowired
    private SysExperienceService sysExperienceService;

    @Autowired
    private SysCredibilityService sysCredibilityService;

    @Autowired
    private VipLevelService vipLevelService;


    /**
     * 完成
     *
     * @api {post} /api/orderBall/list 约球列表
     * @apiName orderBall.list
     * @apiGroup orderBall
     * @apiParam {Integer} timelimit 时间期限
     * @apiParam {Integer} type 球赛类型 N人制 N代表数量
     * @apiParam {Long} areaId 区域ID
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Integer} list.id 约球id
     * @apiSuccess {String} list.content 约球内容
     * @apiSuccess {Integer} list.matchType 赛制
     * @apiSuccess {Integer} list.joinCount 已报人数
     * @apiSuccess {Integer} list.lackCount 剩余人数
     * @apiSuccess {Object} list.user 创建人
     * @apiSuccess {String} list.user.nickname 创建人昵称
     * @apiSuccess {String} list.user.avater 创建人头像
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {String} list.stadium.name 球场名字
     * @apiSuccess {Integer} list.stadium.type 球场类型 (0:私人球场 1:公共球场)
     * @apiSuccess {Long} list.startTime 开始时间
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     Integer timelimit,
                     Integer type,
                     Long areaId,
                     Integer pageNum,
                     Integer pageSize) {

        initPageable(pageNum, pageSize);
        //球场、时间筛选有问题
        Page<Reserve> page = reserveService.page(timelimit, type, areaId, pageNum, pageSize);
        List<Reserve> list = page.getContent();
        for (Reserve reserve : list) {
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");

            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);

        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj, "set", "insurance", "site", "list");
        WebUtil.printApi(response, result);
    }


    /**
     * 完成
     *
     * @api {post} /api/orderBall/orderInfo 约球的详情
     * @apiName orderBall.orderInfo
     * @apiGroup orderBall
     * @apiParam {Long} reserveId 约球ID <必传 />
     *
     * @apiSuccess {Object}  reserve.reserve 约球列表
     * @apiSuccess {Long} reserve.reserve.id 约球id
     * @apiSuccess {String} reserve.reserve.content 约球内容
     * @apiSuccess {Integer} reserve.reserve.matchType 赛制
     * @apiSuccess {Integer} reserve.reserve.joinCount 已报人数
     * @apiSuccess {Integer} reserve.reserve.lackCount 剩余人数
     * @apiSuccess {Object} reserve.reserve.user 创建人
     * @apiSuccess {String} reserve.reserve.user.nickname 创建人昵称
     * @apiSuccess {String} reserve.reserve.user.avater 创建人头像
     * @apiSuccess {Object} reserve.reserve.stadium 球场
     * @apiSuccess {Long} reserve.reserve.stadium.id 球场id
     * @apiSuccess {String} reserve.reserve.stadium.name 球场名字
     * @apiSuccess {Double} reserve.reserve.avePrice AA制金额
     * @apiSuccess {Double} reserve.reserve.sumPrice 支付总金额
     * @apiSuccess {Object} reserve.userList 已报名球友列表
     * @apiSuccess {Long} reserve.userList.id 报名球友id
     * @apiSuccess {String} reserve.userList.nickname 报名球友昵称
     * @apiSuccess {String} reserve.userList.avater 报名球友头像
     * @apiSuccess {Long} reserve.reserve.startTime 开始时间
     */
    @RequestMapping(value = "/orderInfo")
    public void orderInfo(HttpServletResponse response, Long reserveId) {

        if (null == reserveId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (null == reserveId) {
                WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
                return;
            }

            Reserve reserve = reserveService.getById(reserveId);
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");

            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }

            if (null == reserve) {
                WebUtil.printApi(response, new Result(false).msg(ErrorCode.ERROR_CODE_0003));
                return;
            }

            //已报名该预定的用户
            List<User> userList = new ArrayList<User>();
            List<UserReserve> userReserves = userReserveService.findByReserverId(reserveId);
            for (UserReserve userReserve : userReserves) {

                if (StringUtils.isNotBlank(userReserve.getUser().getAvater())) {
                    userReserve.getUser().setAvater(ConfigUtil.getString("upload.url") + userReserve.getUser().getAvater());
                }
                userList.add(userReserve.getUser());
            }

            reserve.setAvePrice(reserve.getPrice() / reserve.getMatchType());
            reserve.setSumPrice(reserve.getAvePrice() + reserve.getInsurance().getPrice());
            reserveService.update(reserve);

            map.put("userList", userList);
            map.put("reserve", reserve);

            Result obj = new Result(true).data(createMap("reserve",map));
            String result = JsonUtil.obj2ApiJson(obj, "set", "site", "list");
            WebUtil.printApi(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/information 约球须知
     * @apiName orderBall.information
     * @apiGroup orderBall
     * @apiSuccess {String} content 约球须知内容
     */
    @RequestMapping(value = "/information")
    public void information(HttpServletResponse response) {
        OrderballMessage orderballMessage = orderballMessageService.getById(1L);

        String description = orderballMessage.getContent();
        String others = "<html><head><style type='text/css'>body{overflow-x:hidden;margin:0;padding:0;background:#fff;color:#000;font-size:18px;font-family:Arial,'microsoft yahei',Verdana}body,div,fieldset,form,h1,h2,h3,h4,h5,h6,html,p,span{-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:normal}applet,dd,div,dl,dt,h1,h2,h3,h4,h5,h6,html,iframe,img,object,p,span{margin:0;padding:0;border:0}img{margin:0;padding:0;border:0;vertical-align:top}li,ul{margin:0;padding:0;list-style:none outside none}input[type=text],select{margin:0;padding:0;border:0;background:0;text-indent:3px;font-size:14px;font-family:Arial,'microsoft yahei',Verdana;-webkit-appearance:none;-moz-appearance:none}.wrapper{box-sizing:border-box;padding:10px;width:100%}p{color:#666;line-height:1.6em}.wrapper img{width:auto!important;height:auto!important;max-width:100%}p,span,p span{font-size:18px!important}</head></style>";
        description = description.format("<body><div class='wrapper'>%s</div></body></html>", description);
        description = others + description;

        Result obj = new Result(true).data(createMap("content", description));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     * * @api {post} /api/orderBall/info 球友个人资料
     *
     * @apiName orderBall.info
     * @apiGroup orderBall
     * @apiParam {Long} playerId 球友ID <必传 />
     *
     * @apiSuccess {Object}  player.user 球友
     * @apiSuccess {String} player.user.avater 球友头像
     * @apiSuccess {String} player.user.nickname 球友昵称
     * @apiSuccess {Integer} player.user.vipNum 球友会员等级
     * @apiSuccess {Integer} player.user.credibility 球友信誉评分
     * @apiSuccess {Integer} player.user.age 球友年龄
     * @apiSuccess {Integer} player.user.gender 球友性别 0:男 1：女
     * @apiSuccess {Double} player.user.height 球友身高
     * @apiSuccess {Double} player.user.weight 球友体重
     * @apiSuccess {Integer} player.user.position 球友位置 0：前 1：中 2：后 3：守
     * @apiSuccess {Object} player.team 球友的球队
     * @apiSuccess {String} player.team.avater 球队队徽
     * @apiSuccess {String} player.team.name 球队名称
     * @apiSuccess {Integer} player.team.declareNum 球队宣战数
     * @apiSuccess {Integer} player.team.battleNum 球队应战数
     * @apiSuccess {Integer} player.team.count 球队总人数
     * @apiSuccess {Object} player.team.list 球队球员列表
     * @apiSuccess {Long} player.team.list .id 球队球员id
     * @apiSuccess {String} player.team.list.avater 球队球员头像
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Long playerId) {

        if (null == playerId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.getById(playerId);
        if (StringUtils.isNotBlank(user.getAvater())) {
            user.setAvater(ConfigUtil.getString("upload.url") + user.getAvater());
        }

        //查询用户所属球队
        List<Team> team = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(user.getId());
        for (TeamMember teamMember : teamMemberList) {
            if (StringUtils.isNotBlank(teamMember.getUser().getAvater())) {
                teamMember.getUser().setAvater(ConfigUtil.getString("upload.url") + teamMember.getUser().getAvater());
            }
            team.add(teamMember.getTeam());
        }


        map.put("user", user);
        map.put("team", team);

        Result obj = new Result(true).data(createMap("player",map));
        String result = JsonUtil.obj2ApiJson(obj, "address", "city", "slogan", "leaderUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/orderList 球友的约球列表
     * @apiName orderBall.orderList
     * @apiGroup orderBall
     * @apiParam {Long} playerId 球友ID <必传 />
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Long} list.id 约球id
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
     */
    @RequestMapping(value = "/orderList")
    public void orderList(HttpServletResponse response, Long playerId) {

        if (null == playerId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<UserReserve> userReserves = userReserveService.findByUserId(playerId);
        for (UserReserve userReserve : userReserves) {

            userReserve.getUser().setAvater(userReserve.getUser().getAvater());
            reserveList.add(reserveService.getById(userReserve.getReserveId()));
        }
        for (Reserve reserve : reserveList) {
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");
            /*reserve.setJoinCount(reserve.getUserReservelist() != null ? reserve.getUserReservelist().size() : 0);
            reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
*/
            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }
        }

        map.put("reserveList", reserveList);

        Result obj = new Result(true).data(createMap("list",map));
        String result = JsonUtil.obj2ApiJson(obj, "set", "insurance", "site", "list");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/order 约球邀请
     * @apiName orderBall.order
     * @apiGroup orderBall
     * @apiParam {Long} reserveId 约球id <必传/>
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} toUserId 好友id <必传/>
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response,
                      Long reserveId,
                      Long userId,
                      Long toUserId) {

        if (null == userId || reserveId == null || toUserId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageOrderBall messageOrderBall = new MessageOrderBall();

        messageOrderBall.setStatus(0);
        messageOrderBall.setUser(userService.getById(userId));
        messageOrderBall.setToUser(userService.getById(toUserId));
        messageOrderBall.setReserve(reserveService.getById(reserveId));

        messageOrderBallService.create(messageOrderBall);
        WebUtil.printApi(response, new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/raceList 球友的赛事列表
     * @apiName orderBall.raceList
     * @apiGroup orderBall
     * @apiParam {Long} playerId 球友ID <必传 />
     * @apiSuccess {Object}  schedule.watchBallVos 球员所在球队为主队赛事列表
     * @apiSuccess {String} schedule.watchBallVos.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVos.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVos.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVos.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVos.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVos.stadiumName 球场名称
     * @apiSuccess {Long} schedule.watchBallVos.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVos.createDate 发起时间
     *
     * @apiSuccess {Object}  schedule.watchBallVoList 球员所在球队为客队赛事列表
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVoList.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVoList.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVoList.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVoList.stadiumName 球场名称
     * @apiSuccess {Long} schedule.watchBallVoList.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVoList.createDate 发起时间
     */
    @RequestMapping(value = "/raceList")
    public void raceList(HttpServletResponse response, Long playerId) {

        if (null == playerId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        List<Team> teams = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(playerId);
        for (TeamMember teamMember : teamMemberList) {
            if (StringUtils.isNotBlank(teamMember.getUser().getAvater())) {
                teamMember.getUser().setAvater(ConfigUtil.getString("upload.url") + teamMember.getUser().getAvater());
            }
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
            watchBallVo2.setId(teamRace.getId());
            watchBallVo2.setStadiumName(teamRace.getStadium().getName());
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

        Result obj = new Result(true).data(createMap("schedule",map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/raceInfo 球友的赛事详情
     * @apiName orderBall.raceInfo
     * @apiGroup orderBall
     * @apiParam {Long} userId 球友ID <必传 />
     * @apiParam {Long} raceId 赛事ID <必传 />
     * @apiSuccess {Object}  race.watchBallVo 球员赛事
     * @apiSuccess {String} race.watchBallVo.homeTeamName 主队队名
     * @apiSuccess {String} race.watchBallVo.homeTeamAvater 主队队徽
     * @apiSuccess {String} race.watchBallVo.vTeamName 客队队名
     * @apiSuccess {String} race.watchBallVo.vTeamAvater 客队队徽
     * @apiSuccess {Integer} race.watchBallVo.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} race.watchBallVo.stadiumName 球场名称
     * @apiSuccess {Long} race.watchBallVo.startTime 开始时间
     * @apiSuccess {String} race.mobile 手机号
     */
    @RequestMapping(value = "/raceInfo")
    public void raceInfo(HttpServletResponse response, Long userId, Long raceId, String mobile) {

        if (null == userId || raceId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

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
        if (StringUtils.isNotBlank(homeTeam.getAvater())) {
            watchBallVo.setHomeTeamAvater(ConfigUtil.getString("upload.url") + homeTeam.getAvater());
        }
        watchBallVo.setvTeamName(visitingTeam.getName());
        if (StringUtils.isNotBlank(visitingTeam.getAvater())) {
            watchBallVo.setvTeamAvater(ConfigUtil.getString("upload.url") + visitingTeam.getAvater());
        }
        watchBallVo.setStatus(teamRace.getStatus());
        watchBallVo.setStartTime(teamRace.getStartTime());
        watchBallVo.setStadiumName(teamRace.getStadium().getName());

        map.put("watchBallVo", watchBallVo);
        map.put("mobile", mobile);

        Result obj = new Result(true).data(createMap("race",map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }


    /**
     * 球友的帖子
     *
     * 与UserApi里我的帖子相同
     */

    /**
     * 完成
     *
     * @api {post} /api/orderBall/pay 球友的约球支付
     * @apiName orderBall.pay
     * @apiGroup orderBall
     * @apiParam {Long} reserveId 约球ID <必传 />
     * @apiParam {Long} userId 用户ID <必传 />
     *
     * @apiSuccess {Object} order 订单
     * @apiSuccess {String} order.userName 用户昵称
     * @apiSuccess {String} order.stadiumName 球场名称
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response, Long reserveId, Long userId, Double money) {

        if (null == userId || reserveId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Reserve reserve = reserveService.getById(reserveId);
        Order order = null;
        if (reserve.getJoinCount() < reserve.getMatchType()*2) {

            reserve.setJoinCount(reserve.getJoinCount() + 1);
            reserveService.update(reserve);
            if (reserve.getJoinCount() == reserve.getMatchType()*2) {

                List<UserReserve> userReserveList = userReserveService.findByReserverId(reserveId);
                for (UserReserve userReserve : userReserveList) {

                    SysExperience sysExperience = sysExperienceService.findByAction(1);
                    userReserve.getUser().setExperience(userReserve.getUser().getExperience() + sysExperience.getExperience());
                    userService.changeIntegral(userReserve.getUser());
                    SysCredibility sysCredibility = sysCredibilityService.findByAction(1);
                    userReserve.getUser().setCredibility(userReserve.getUser().getCredibility() + sysCredibility.getCredibility());

                    userService.update(userReserve.getUser());
                }
            }

            Insurance insurance = new Insurance();

            //球场已经全额付款的
            if (reserve.getPayment() == 0) {
                WebUtil.printApi(response, new Result(true).data("加入成功"));
            }
            //球场AA付款
            if (reserve.getPayment() == 1) {
                insurance.setMoney(reserve.getInsurance().getPrice());
                /*if (userService.getById(userId).getVipNum() != 0) {
                    VipLevel vipLevel = vipLevelService.findBylevel(userService.getById(userId).getVipNum());
                    money = (reserve.getPrice() / reserve.getMatchType() + reserve.getInsurance().getPrice()) * vipLevel.getPreferente();
                }else {
                    money = reserve.getPrice() / reserve.getMatchType() + reserve.getInsurance().getPrice();
                }*/

                money = reserve.getPrice() / reserve.getMatchType();
                insurance.setUser(userService.getById(userId));
                insurance.setReserve(reserve);
                insuranceService.create(insurance);

                String sn = CommonUtils.generateSn(); // 订单号

                order = new Order();
                order.setUser(userService.getById(userId));
                order.setStadiumname(reserve.getStadium().getName());
                order.setPrice(money);
                //order.setAction(1);
                order.setSn(sn);
                orderService.create(order);
            }

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(userService.getById(userId));

            userReserve.setReserveId(reserveId);
            //userReserve.setReserve(reserve);
            userReserve.setStatus(0);
            userReserveService.create(userReserve);
        }

        Result obj = new Result(true).data(createMap("order", order));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
