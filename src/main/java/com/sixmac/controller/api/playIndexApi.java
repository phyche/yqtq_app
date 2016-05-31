package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.JsonUtil;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 完成
     *
     * @api {post} /api/playIndex/orderballList 我的赛事列表
     * @apiName playIndex.orderballList
     * @apiGroup playIndex
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  reserves 约球列表
     * @apiSuccess {Integer} reserves.id 约球id
     * @apiSuccess {Integer} reserves.content 约球内容
     * @apiSuccess {Integer} reserves.matchType 赛制
     * @apiSuccess {Integer} reserves.joinCount 已报人数
     * @apiSuccess {Integer} reserves.lackCount 剩余人数
     * @apiSuccess {Object} reserves.user 创建人
     * @apiSuccess {String} reserves.user.nickname 创建人昵称
     * @apiSuccess {String} reserves.user.avater 创建人头像
     * @apiSuccess {Object} reserves.stadium 球场
     * @apiSuccess {String} reserves.stadium.name 球场名字
     * @apiSuccess {Long} reserves.startTime 开始时间
     */
    @RequestMapping(value = "orderballList")
    public void orderballList(HttpServletResponse response, Integer userId) {

        Map<String, Object> map = new HashMap<String, Object>();

        //球友参与的约球
        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<UserReserve> userReserves = userReserveService.findByUserId(userId);
        for (UserReserve userReserve : userReserves) {
            reserveList.add(userReserve.getReserve());
        }

        //查询约球的前三条信息
        List<Reserve> reserves = new ArrayList<Reserve>();
        if (reserveList.size() >= 3) {
            reserves.add(reserveList.get(reserveList.size()-3));
        }
        if (reserveList.size() >= 2) {
            reserves.add(reserveList.get(reserveList.size()-2));
        }
        if (reserveList.size() >= 1) {
            reserves.add(reserveList.get(reserveList.size()-1));
        }

        map.put("reserves", reserves);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"set","insurance","site","list");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/playIndex/banner 系统banner图列表
     * @apiName playIndex.banner
     * @apiGroup playIndex
     *
     * @apiSuccess {Object}  bannerList 系统banner图列表
     * @apiSuccess {Integer} bannerList.id banner图id
     * @apiSuccess {Integer} bannerList.avater banner图
     * @apiSuccess {Integer} bannerList.type banner图类型
     * @apiSuccess {Integer} bannerList.toId banner图跳转目的Id
     *
     */
    @RequestMapping(value = "/banner")
    public void banner(HttpServletResponse response) {

        List<Banner> bannerList = bannerService.findAll();

        Result obj = new Result(true).data(bannerList);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/playIndex/jump 系统banner图跳转
     * @apiName playIndex.jump
     * @apiGroup playIndex
     * @apiParam {Integer} bannerId banner图id <必传 />
     *
     * @apiSuccess {Object}  activity 活动
     * @apiSuccess {Integer} list.id 活动id
     * @apiSuccess {String} activity.title 活动标题
     * @apiSuccess {String} activity.avater 活动封面
     * @apiSuccess {String} activity.introduction 活动简介
     * @apiSuccess {String} activity.description 活动介绍
     * @apiSuccess {Long} activity.createDate 活动创建时间
     *
     * @apiSuccess {Object}  hostRace 草根杯
     * @apiSuccess {Integer} hostRace.id 草根杯id
     * @apiSuccess {Integer} hostRace.type 草根杯赛制
     * @apiSuccess {String} hostRace.name 草根杯名字
     * @apiSuccess {String} hostRace.avater 草根杯封面
     * @apiSuccess {String} hostRace.description 草根杯介绍
     * @apiSuccess {Object} hostRace.stadium 球场
     * @apiSuccess {String} hostRace.stadium.name 球场名称
     *
     * @apiSuccess {Object}  information 资讯列表
     * @apiSuccess {Integer} information.id 资讯id
     * @apiSuccess {String} information.title 资讯标题
     * @apiSuccess {String} information.avater 资讯封面
     * @apiSuccess {String} information.introduction 资讯简介
     * @apiSuccess {String} information.description 资讯介绍
     * @apiSuccess {Long} information.createDate 资讯创建时间
     *
     */
    @RequestMapping(value = "/jump")
    public void jump(HttpServletResponse response, Integer bannerId) {

        Map<String, Object> map = new HashMap<String, Object>();

        Banner banner = bannerService.getById(bannerId);
        //活动
        if (banner.getType() == 0) {
            Activity activity = activityService.getById(banner.getToId());
            map.put("activity",activity);
        }

        //平台赛事
        if (banner.getType() == 1) {
            HostRace hostRace = hostRaceService.getById(banner.getToId());
            map.put("hostRace",hostRace);
        }

        //资讯
        if (banner.getType() == 2) {
            Information information = informationService.getById(banner.getToId());
            map.put("information",information);
        }

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/playIndex/teamRace 球队约战
     * @apiName playIndex.teamRace
     * @apiGroup playIndex
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  teamList 球队约战列表
     * @apiSuccess {Integer} teamList.id 球队id
     * @apiSuccess {String} teamList.name 球队名字
     * @apiSuccess {String} teamList.avater 球队队徽
     * @apiSuccess {Integer} teamList.declareNum 球队宣战数
     * @apiSuccess {Integer} teamList.battleNum 球队应战数
     * @apiSuccess {Object} teamList.list 球队球员列表
     * @apiSuccess {Integer} teamList.list.id 球队球员id
     * @apiSuccess {String} teamList.list.avater 球队球员头像
     *
     */
    @RequestMapping(value = "/teamRace")
    public void teamRace(HttpServletResponse response, Integer userId) {

        List<Team> teams = new ArrayList<Team>();

        List<TeamMember> teamMemberList = teamMemberService.findByUserId(userId);
        if (teamMemberList == null) {
            WebUtil.printApi(response, new Result(true).data("没有球队约战"));
        }

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
        List<Team> teamList = new ArrayList<Team>();
        for (TeamRace teamRace : list) {
            teamList.add(teamRace.getVisitingTeam());
        }

        List<TeamRace> listrace = teamRaceService.findByVisitingId(teamIds);
        for (TeamRace teamRace : listrace) {
            teamList.add(teamRace.getHomeTeam());
        }

        Result obj = new Result(true).data(teamList);
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser");
        WebUtil.printApi(response, result);
    }
}
