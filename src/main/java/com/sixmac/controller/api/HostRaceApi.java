package com.sixmac.controller.api;

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
 * Created by Administrator on 2016/5/25 0025.
 */
@Controller
@RequestMapping(value = "api/hostRace")
public class HostRaceApi {

    @Autowired
    private HostRaceService hostRaceService;

    @Autowired
    private HostJoinService hostJoinService;

    @Autowired
    private EventInformationService eventInformationService;

    /**
     * （完成）
     *
     * @api {post} /api/hostRace/list 草根杯列表
     * @apiName hostRace.list
     * @apiGroup hostRace
     *
     * @apiSuccess {Object}  hostRace 草根杯列表
     * @apiSuccess {Integer} hostRace.id 草根杯id
     * @apiSuccess {Integer} hostRace.id 草根杯赛制
     * @apiSuccess {String} hostRace.name 草根杯名字
     * @apiSuccess {String} hostRace.avater 草根杯封面
     * @apiSuccess {Long} hostRace.createDate 草根杯创建时间
     * @apiSuccess {Object} hostRace.stadium 球场
     * @apiSuccess {String} hostRace.stadium.name 球场名称
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response) {

        List<HostRace> hostRaceList = hostRaceService.findAll();
        HostRace hostRace = hostRaceList.get(hostRaceList.size()-1);

        Result obj = new Result(true).data(hostRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/hostRace/info 草根杯详情
     * @apiName hostRace.info
     * @apiGroup hostRace
     * @apiParam {Integer} raceId 草根杯id <必传 />
     *
     * @apiSuccess {Object}  hostRace 草根杯列表
     * @apiSuccess {Integer} hostRace.id 草根杯id
     * @apiSuccess {Integer} hostRace.type 草根杯赛制
     * @apiSuccess {String} hostRace.name 草根杯名字
     * @apiSuccess {String} hostRace.avater 草根杯封面
     * @apiSuccess {String} hostRace.description 草根杯介绍
     * @apiSuccess {Object} hostRace.stadium 球场
     * @apiSuccess {String} hostRace.stadium.name 球场名称
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Integer raceId) {

        HostRace hostRace = hostRaceService.getById(raceId);

        Result obj = new Result(true).data(hostRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/hostRace/teamList 草根杯参赛队伍
     * @apiName hostRace.teamList
     * @apiGroup hostRace
     * @apiParam {Integer} raceId 草根杯id <必传 />
     *
     * @apiSuccess {Object}  hostRace 草根杯列表
     * @apiSuccess {Object} hostRace.team 草根杯参赛队伍列表
     * @apiSuccess {Integer} hostRace.team.id 球队id
     * @apiSuccess {String} hostRace.team.name 球队名称
     * @apiSuccess {String} hostRace.team.avater 球队队徽
     * @apiSuccess {Integer} hostRace.team.count 球队总人数
     * @apiSuccess {Integer} hostRace.team.cityId 球队所在地
     * @apiSuccess {Integer} hostRace.team.num 球队场次
     */
    @RequestMapping(value = "/teamList")
    public void teamList(HttpServletResponse response, Integer raceId) {

        List<Team> teams = new ArrayList<Team>();

        List<HostJoin> hostJoinList = hostJoinService.findByHostRaceId(raceId);
        for (HostJoin hostJoin : hostJoinList) {
            hostJoin.getTeam().setNum(hostJoin.getTeam().getBattleNum() + hostJoin.getTeam().getDeclareNum());
            hostJoin.getTeam().setCount(hostJoin.getTeam().getList().size());
            teams.add(hostJoin.getTeam());
        }

        Result obj = new Result(true).data(teams);
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser","list","slogan","avater","declareNum","address");
        WebUtil.printApi(response, result);
    }

    /**
     *
     *
     * @api {post} /api/hostRace/eventInformation 草根杯赛事资讯
     * @apiName hostRace.eventInformation
     * @apiGroup hostRace
     * @apiParam {Integer} raceId 草根杯id <必传 />
     *
     * @apiSuccess {Object}  hostRace 草根杯列表
     * @apiSuccess {String} hostRace.content 草根杯赛事资讯
     */
    @RequestMapping(value = "/eventInformation")
    public void eventInformation(HttpServletResponse response, Integer raceId) {

        EventInformation eventInformation = eventInformationService.findByRaceId(raceId);

        Result obj = new Result(true).data(eventInformation);
        String result = JsonUtil.obj2ApiJson(obj,"hostRace");
        WebUtil.printApi(response, result);
    }
}
