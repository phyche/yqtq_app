package com.sixmac.controller.api;

import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.HostJoinService;
import com.sixmac.service.HostRaceService;
import com.sixmac.service.ReserveService;
import com.sixmac.service.UserReserveService;
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
    private ReserveService reserveService;

    @Autowired
    private UserReserveService userReserveService;

    /**
     * 草根杯列表（完成）
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
     * 草根杯详情（完成）
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Integer raceId) {

        HostRace hostRace = hostRaceService.getById(raceId);

        Result obj = new Result(true).data(hostRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 草根杯参赛队伍(完成)
     */
    @RequestMapping(value = "/teamList")
    public void teamList(HttpServletResponse response, Integer raceId) {

        List<Team> teams = new ArrayList<Team>();

        List<HostJoin> hostJoinList = hostJoinService.findByHostRaceId(raceId);
        for (HostJoin hostJoin : hostJoinList) {
            teams.add(hostJoin.getTeam());
        }

        Result obj = new Result(true).data(teams);
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser","list","slogan","avater","declareNum","address");
        WebUtil.printApi(response, result);
    }

    /**
     * 草根杯赛事资讯
     */

    /**
     * 踢球首页约球列表
     */
    @RequestMapping(value = "orderballList")
    public void orderballList(HttpServletResponse response,Integer userId) {

        Map<String, Object> map = new HashMap<String, Object>();

        //球友参与的约球
        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<UserReserve> userReserves = userReserveService.findByUserId(userId);
        for (UserReserve userReserve : userReserves) {
            reserveList.add(userReserve.getReserve());
        }

        //查询约球的前三条信息
        List<Reserve> reserves = new ArrayList<Reserve>();
        reserves.add(reserveList.get(reserveList.size()-3));
        reserves.add(reserveList.get(reserveList.size()-2));
        reserves.add(reserveList.get(reserveList.size()-1));

        map.put("reserves", reserves);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
