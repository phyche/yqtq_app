package com.sixmac.controller.api;


import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.EventInformation;
import com.sixmac.entity.HostJoin;
import com.sixmac.entity.HostRace;
import com.sixmac.entity.Team;
import com.sixmac.service.*;
import com.sixmac.utils.ConfigUtil;
import com.sixmac.utils.JsonUtil;
import com.sixmac.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
@Controller
@RequestMapping(value = "api/hostRace")
public class HostRaceApi extends CommonController {

    @Autowired
    private HostRaceService hostRaceService;

    @Autowired
    private HostJoinService hostJoinService;

    @Autowired
    private EventInformationService eventInformationService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private TeamService teamService;

    /**
     * （完成）
     *
     * @api {post} /api/hostRace/list 草根杯列表
     * @apiName hostRace.list
     * @apiGroup hostRace
     *
     * @apiSuccess {Object}  hostRace 草根杯
     * @apiSuccess {Long} hostRace.id 草根杯id
     * @apiSuccess {Integer} hostRace.type 草根杯赛制
     * @apiSuccess {String} hostRace.name 草根杯名字
     * @apiSuccess {String} hostRace.avater 草根杯封面
     * @apiSuccess {Long} hostRace.createDate 草根杯创建时间
     * @apiSuccess {Object} hostRace.stadium 球场
     * @apiSuccess {String} hostRace.stadium.name 球场名称
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response) {

        List<HostRace> hostRaceList = hostRaceService.findNew();
        HostRace hostRace = hostRaceList.get(0);

        if (StringUtils.isNotBlank(hostRace.getAvater())) {
            hostRace.setAvater(ConfigUtil.getString("upload.url") + hostRace.getAvater());
        }

        Result obj = new Result(true).data(createMap("hostRace",hostRace));
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
     * @apiSuccess {Object}  list 草根杯列表
     * @apiSuccess {Object} list.team 草根杯参赛队伍列表
     * @apiSuccess {Long} list.team.id 球队id
     * @apiSuccess {String} list.team.name 球队名称
     * @apiSuccess {String} list.team.avater 球队队徽
     * @apiSuccess {Integer} list.team.count 球队总人数
     * @apiSuccess {String} list.team.address 球队所在地
     * @apiSuccess {Integer} list.team.num 球队场次
     */
    @RequestMapping(value = "/teamList")
    public void teamList(HttpServletResponse response, Long raceId) {

        if (null == raceId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<Team> list = new ArrayList<Team>();

        List<HostJoin> hostJoinList = hostJoinService.findByHostRaceId(raceId);
        for (HostJoin hostJoin : hostJoinList) {
            hostJoin.getTeam().setNum(hostJoin.getTeam().getBattleNum() + hostJoin.getTeam().getDeclareNum());
            hostJoin.getTeam().setCount(hostJoin.getTeam().getList().size() + 1);
            /*hostJoin.getTeam().setCityName(cityService.getByCityId(hostJoin.getTeam().getCityId()).getCity());
            hostJoin.getTeam().setProvinceName(provinceService.getByProvinceId(hostJoin.getTeam().getProvinceId()).getProvince());*/

            if (StringUtils.isNotBlank(hostJoin.getTeam().getAvater())) {
                hostJoin.getTeam().setAvater(ConfigUtil.getString("upload.url") + hostJoin.getTeam().getAvater());
            }

            list.add(hostJoin.getTeam());
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser","list","slogan","declareNum","provinceName","cityName");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/hostRace/eventInformation 草根杯赛事资讯
     * @apiName hostRace.eventInformation
     * @apiGroup hostRace
     * @apiParam {Long} raceId 草根杯id <必传 />
     *
     * @apiSuccess {Object}  eventInformation 草根杯赛事资讯列表
     * @apiSuccess {String} eventInformation.content 草根杯赛事资讯内容
     */
    @RequestMapping(value = "/eventInformation")
    public void eventInformation(HttpServletResponse response, Long raceId) {

//        if(ValidateUtis.isEmpty(user,"userId","username")) {
//        }
        if (null == raceId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        EventInformation eventInformation = eventInformationService.findByRaceId(raceId);

        Result obj = new Result(true).data(createMap("eventInformation",eventInformation));
        String result = JsonUtil.obj2ApiJson(obj,"hostRace");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/hostRace/apply 草根杯报名
     * @apiName hostRace.apply
     * @apiGroup hostRace
     * @apiParam {Long} raceId 草根杯id <必传 />
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  eventInformation 草根杯赛事资讯列表
     * @apiSuccess {String} eventInformation.content 草根杯赛事资讯内容
     */
    @RequestMapping(value = "/apply")
    public void apply(HttpServletResponse response, Long raceId, Long userId) {

        if (null == raceId || userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        HostRace hostRace = hostRaceService.getById(raceId);

        Team team = teamService.findListByLeaderId(userId);

        if (team == null) {
            // 没有球队，提示创建球队
            WebUtil.printApi(response, new Result(true));
        }else {

            HostJoin hostJoin = new HostJoin();
            hostJoin.setTeam(team);
            hostJoin.setHostRaceId(hostRace.getId());
            hostRaceService.create(hostRace);
        }

        WebUtil.printApi(response, new Result(true));
    }
}
