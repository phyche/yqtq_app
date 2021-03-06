package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.ConfigUtil;
import com.sixmac.utils.DateUtils;
import com.sixmac.utils.JsonUtil;
import com.sixmac.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
@Controller
@RequestMapping(value = "/api/playIndex")
public class PlayIndexApi extends CommonController {

    @Autowired
    private UserReserveService userReserveService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private HostRaceService hostRaceService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamRaceService teamRaceService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private TeamService teamService;

    /**
     * 完成
     *
     * @api {post} /api/playIndex/orderballList 我的赛事列表
     * @apiName playIndex.orderballList
     * @apiGroup playIndex
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Long} list.id 约球id
     * @apiSuccess {String} list.content 约球内容
     * @apiSuccess {Integer} list.matchType 赛制
     * @apiSuccess {Integer} list.joinCount 已报人数
     * @apiSuccess {Integer} list.lackCount 剩余人数
     * @apiSuccess {Object} list.user 创建人
     * @apiSuccess {String} list.user.nickname 创建人昵称
     * @apiSuccess {String} list.user.avater 创建人头像
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {String} list.stadium.name 球场名字
     * @apiSuccess {Long} list.startTime 开始时间
     * @apiSuccess {Long} list.createDate 创建时间
     */
    @RequestMapping(value = "orderballList")
    public void orderballList(HttpServletResponse response, Long userId) throws ParseException {
        long start = System.currentTimeMillis();
        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<UserReserve> userReserves = userReserveService.findByUserId(userId);
        Reserve reserve = null;
        for (UserReserve userReserve : userReserves) {
            reserve = userReserve.getReserve();
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(DateUtils.longToDate(reserve.getStartTime(),"yyyy-MM-dd HH:mm:ss")) + "," + reserve.getStadium().getName() + "约球了");
            if (reserve.getType() == 0) {
                reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
            }
            if (reserve.getStadium() == null) {
                reserve.setStadium(new Stadium());
            }
            reserveList.add(reserve);
            //reserveList.add(userReserve.getReserve());

        }

        Result obj = new Result(true).data(createMap("list",reserveList));
        String result = JsonUtil.obj2ApiJson(obj,"userReservelist","insurance");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/playIndex/notice 通知
     * @apiName playIndex.notice
     * @apiGroup playIndex
     *
     * @apiSuccess {Object}  reserve 约球列表
     * @apiSuccess {Long} reserve.id 约球id
     * @apiSuccess {String} reserve.content 约球内容
     *
     */
    @RequestMapping(value = "/notice")
    public void notice(HttpServletResponse response) throws ParseException {
        long start = System.currentTimeMillis();
        List<Reserve> list = reserveService.findNew();
        Reserve reserve = list.get(list.size()-1);
        reserve.setContent(DateUtils.chinaDayOfWeekAndAM(DateUtils.longToDate(reserve.getStartTime(),"yyyy-MM-dd HH:mm:ss")) + "," + reserve.getStadium().getName() + "约球了");

        Result obj = new Result(true).data(createMap("reserve",reserve));
        String result = JsonUtil.obj2ApiJson(obj);
        long end = System.currentTimeMillis();
        System.out.println("api/playIndex/notice:耗时" + (end - start) / 1000.0 + "秒");
        WebUtil.printApi(response, result);
    }
    /**
     * 完成
     *
     * @api {post} /api/playIndex/banner 系统banner图列表
     * @apiName playIndex.banner
     * @apiGroup playIndex
     *
     * @apiSuccess {Object}  list 系统banner图列表
     * @apiSuccess {Long} list.id banner图id
     * @apiSuccess {String} list.avater banner图
     * @apiSuccess {Integer} list.type banner图类型
     * @apiSuccess {Long} list.toId banner图跳转目的Id
     *
     */
    @RequestMapping(value = "/banner")
    public void banner(HttpServletResponse response) {

        List<Banner> list = bannerService.findAll();
        for (Banner banner : list) {
            if (StringUtils.isNotBlank(banner.getAvater())) {
                banner.setAvater(ConfigUtil.getString("upload.url") + banner.getAvater());
            }
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/playIndex/jump 系统banner图跳转
     * @apiName playIndex.jump
     * @apiGroup playIndex
     * @apiParam {Long} bannerId banner图id <必传 />
     *
     * @apiSuccess {Object}  banner.activity 活动
     * @apiSuccess {Long} banner.activity.id 活动id
     * @apiSuccess {String} banner.activity.title 活动标题
     * @apiSuccess {String} banner.activity.avater 活动封面
     * @apiSuccess {String} banner.activity.introduction 活动简介
     * @apiSuccess {String} banner.activity.description 活动介绍
     * @apiSuccess {Long} banner.activity.createDate 活动创建时间
     *
     * @apiSuccess {Object}  banner.hostRace 草根杯
     * @apiSuccess {Long} banner.hostRace.id 草根杯id
     * @apiSuccess {Integer} banner.hostRace.type 草根杯赛制
     * @apiSuccess {String} banner.hostRace.name 草根杯名字
     * @apiSuccess {String} banner.hostRace.avater 草根杯封面
     * @apiSuccess {String} banner.hostRace.description 草根杯介绍
     * @apiSuccess {Object} banner.hostRace.stadium 球场
     * @apiSuccess {String} banner.hostRace.stadium.name 球场名称
     *
     * @apiSuccess {Object}  banner.information 资讯列表
     * @apiSuccess {Long} banner.information.id 资讯id
     * @apiSuccess {String} banner.information.title 资讯标题
     * @apiSuccess {String} banner.information.avater 资讯封面
     * @apiSuccess {String} banner.information.introduction 资讯简介
     * @apiSuccess {String} banner.information.description 资讯介绍
     * @apiSuccess {Long} banner.information.createDate 资讯创建时间
     *
     */
    @RequestMapping(value = "/jump")
    public void jump(HttpServletResponse response, Long bannerId) {

        if (null == bannerId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Banner banner = bannerService.getById(bannerId);
        if (StringUtils.isNotBlank(banner.getAvater())) {
            banner.setAvater(ConfigUtil.getString("upload.url") + banner.getAvater());
        }

        //活动
        if (banner.getType() == 0) {
            Activity activity = activityService.getById(banner.getToId());
            if (StringUtils.isNotBlank(activity.getAvater())) {
                activity.setAvater(ConfigUtil.getString("upload.url") + activity.getAvater());
            }
            map.put("activity",activity);
        }

        //平台赛事
        if (banner.getType() == 1) {
            HostRace hostRace = hostRaceService.getById(banner.getToId());
            if (StringUtils.isNotBlank(hostRace.getAvater())) {
                hostRace.setAvater(ConfigUtil.getString("upload.url") + hostRace.getAvater());
            }
            map.put("hostRace",hostRace);
        }

        //资讯
        if (banner.getType() == 2) {
            Information information = informationService.getById(banner.getToId());
            if (StringUtils.isNotBlank(information.getAvater())) {
                information.setAvater(ConfigUtil.getString("upload.url") + information.getAvater());
            }
            map.put("information",information);
        }

        Result obj = new Result(true).data(createMap("banner",map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/playIndex/teamRace 球队约战
     * @apiName playIndex.teamRace
     * @apiGroup playIndex
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  list 球队约战列表
     * @apiSuccess {Long} list.id 球队id
     * @apiSuccess {String} list.name 球队名字
     * @apiSuccess {String} list.avater 球队队徽
     * @apiSuccess {Integer} list.declareNum 球队宣战数
     * @apiSuccess {Integer} list.battleNum 球队应战数
     * @apiSuccess {Object} list.list 球队球员列表
     * @apiSuccess {Long} list.list.id 球队球员id
     * @apiSuccess {String} list.list.avater 球队球员头像
     *
     */
    @RequestMapping(value = "/teamRace")
    public void teamRace(HttpServletResponse response, Long userId) {
        long start = System.currentTimeMillis();
        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<Team> teams = new ArrayList<Team>();

        List<TeamMember> teamMemberList = teamMemberService.findByUserId(userId);
        if (teamMemberList == null) {
            WebUtil.printApi(response, new Result(true));
            return;
        }
        List<TeamRace> teamRaceList = teamRaceService.findByTeamId(teamMemberList);
        for (TeamRace teamRace : teamRaceList) {
            teams.add(teamRace.getVisitingTeam());
        }



        // =======================================================

//        for (TeamMember teamMember : teamMemberList) {
//            //teamMember.getUser().setAvater(teamMember.getUser().getAvater());
//            teams.add(teamService.getById(teamMember.getTeamId()));
//        }
//
//        String teamIds = "";
//        StringBuffer buffer = new StringBuffer("");
//        for (Team team : teams) {
//            //根据球队列表查询球队赛事
//            // select * from t_race r  where r.host_id in(1,2,3) or r.visitingid in (1,2,3)
//            buffer.append(team.getId()).append(",");
//        }
//        teamIds = buffer.toString().substring(0, buffer.length() - 1);
//
//        List<TeamRace> raceList = teamRaceService.findByHomeTeamId(teamIds);
//        List<Team> list = new ArrayList<Team>();
//        for (TeamRace teamRace : raceList) {
//
//            if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
//                teamRace.getHomeTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
//            }
//            if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
//                teamRace.getVisitingTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
//            }
//
//            list.add(teamRace.getVisitingTeam());
//        }
//
//        List<TeamRace> listrace = teamRaceService.findByVisitingId(teamIds);
//        for (TeamRace teamRace : listrace) {
//
//            if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
//                teamRace.getHomeTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
//            }
//            if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
//                teamRace.getVisitingTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
//            }
//
//            list.add(teamRace.getHomeTeam());
//        }

        Result obj = new Result(true).data(createMap("list",teams));
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser");
        long end = System.currentTimeMillis();
        System.out.println("api/playIndex/teamRace:耗时" + (end - start) / 1000.0 + "秒");
        WebUtil.printApi(response, result);
    }
}
