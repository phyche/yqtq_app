package com.sixmac.controller.api;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.UserVo;
import com.sixmac.entity.vo.WatchBallVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/5/18 0018.
 * 约球队
 */
@Controller
@RequestMapping(value = "api/team")
public class TeamApi extends CommonController {

    @Autowired
    private TeamService teamService;

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


    /**
     * 完成
     *
     *  @api {post} /api/team/list 球队列表
     * @apiName team.list
     * @apiGroup team
     * @apiParam {String} name 球队名字
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 球队列表
     * @apiSuccess {Long} list.id 球队id
     * @apiSuccess {String} list.name 球队名称
     * @apiSuccess {String} list.avater 球队队徽
     * @apiSuccess {Integer} list.count 球队人数
     * @apiSuccess {String} list.provinceName 球队省份名字
     * @apiSuccess {String} list.cityName 球队城市名字
     * @apiSuccess {Integer} list.num 球队场次
     *
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     String name,
                     Integer pageNum,
                     Integer pageSize) {


        Page<Team> page = teamService.page(name, pageNum, pageSize);
        List<Team> list = page.getContent();
        for (Team team : list) {
            team.setNum(team.getBattleNum() + team.getDeclareNum());
            team.setProvinceName(provinceService.getByProvinceId(team.getProvinceId()).getProvince());
            team.setCityName(cityService.getByCityId(team.getCityId()).getCity());
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"list","leaderUser","slogan","aveage","aveweight","aveheight","sum");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/team/info 球队详情
     * @apiName team.info
     * @apiGroup team
     * @apiParam {Long} teamId 球队Id <必传 />
     *
     * @apiSuccess {Object}  team 球队
     * @apiSuccess {Long} team.id 球队id
     * @apiSuccess {String} team.name 球队名称
     * @apiSuccess {String} team.avater 球队队徽
     * @apiSuccess {String} team.slogan 球队口号
     * @apiSuccess {String} team.address 球队地址
     * @apiSuccess {Double} team.aveAge 球队平均年龄
     * @apiSuccess {Double} team.aveHeight 球队平均身高
     * @apiSuccess {Double} team.aveWeight 球队平均体重
     *
     * @apiSuccess {Object}  team.leaderUser 队长
     * @apiSuccess {Long} team.leaderUser.id 队长id
     * @apiSuccess {String} team.leaderUser.avater 队长头像
     *
     * @apiSuccess {Object}  userList 队员
     * @apiSuccess {Long} userList.id 队员id
     * @apiSuccess {String} userList.avater 队员头像
     *
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Long teamId) {

        if (null == teamId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Integer sum = 0;
        double sumAge = 0;
        double sumWeight = 0.0;
        double sumHeight = 0.0;

        Team team = teamService.getById(teamId);

        for (sum = 0; sum < team.getList().size(); sum++) {
            sumAge += team.getList().get(sum).getAge();
            sumHeight += team.getList().get(sum).getHeight();
            sumWeight += team.getList().get(sum).getWeight();
        }

        team.setAveAge(sumAge / team.getList().size());
        team.setAveHeight(sumHeight / team.getList().size());
        team.setAveWeight(sumWeight / team.getList().size());

        //球员列表
        List<User> userList = team.getList();
        team.setCount(userList.size());

        map.put("team", team);
        map.put("userList",userList);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"list","declareNum","battleNum","sum","nickname");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/team/add 创建球队
     * @apiName team.add
     * @apiGroup team
     * @apiParam {Long} userId 用户Id <必传 />
     * @apiParam {String} name 球队名称 <必传 />
     * @apiParam {String} slogan 球队口号 <必传 />
     * @apiParam {String} address 球队地址
     *
     *
     */
    @RequestMapping(value = "/add")
    public void add(HttpServletResponse response,
                    Long userId,
                    String name,
                    String address,
                    String slogan,
                    MultipartRequest multipartRequest) throws IOException {

        if (null == userId || name == null || name == " " || address == null || address == " " || slogan == null || slogan == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Team team = new Team();

        /*MultipartFile multipartFile = multipartRequest.getFile("mainImage");
        if (null != multipartFile) {
            String url = QiNiuUploadImgUtil.upload(multipartFile);
            //magazine.setCover(url);
            team.setAvater(url);
        }*/

        MultipartFile multipartFile = multipartRequest.getFile("imageFile");
        if (null != multipartFile) {
            FileBo fileBo = FileUtil.save(multipartFile);
            team.setAvater(fileBo.getPath());
        }

        team.setName(name);
        team.setAddress(address);
        team.setSlogan(slogan);
        team.setLeaderUser(userService.getById(userId));
        teamService.create(team);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/team/addFriend 邀请朋友加入球队
     * @apiName team.addFriend
     * @apiGroup team
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} toUserId 好友id <必传/>
     *
     */
    @RequestMapping(value = "/addFriend")
    public void addFriend(HttpServletResponse response, Long userId, Long toUserId) {

        if (null == userId || toUserId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Team team = teamService.findListByLeaderId(userId);

        MessageTeam messageTeam = new MessageTeam();
        messageTeam.setUser(userService.getById(userId));
        messageTeam.setToUser(userService.getById(toUserId));
        messageTeam.setTeam(team);
        messageTeam.setStatus(0);
        messageTeamService.create(messageTeam);

        WebUtil.printApi(response, new Result(true).data("添加成功"));
    }

    /**
     * 完成
     *
     * @api {post} /api/team/apply 申请加入球队
     * @apiName team.apply
     * @apiGroup team
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} teamId 球队id <必传/>
     *
     */
    @RequestMapping(value = "/apply")
    public void apply(HttpServletResponse response, Long userId, Long teamId) {

        if (null == teamId || userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageJoin messageJoin = new MessageJoin();
        messageJoin.setStatus(0);
        messageJoin.setUser(userService.getById(userId));
        messageJoin.setTeam(teamService.getById(teamId));
        messageJoinService.create(messageJoin);

        WebUtil.printApi(response, new Result(true).data("申请成功，等待球队确认"));
    }

    /**
     * 完成
     * 如果用户没有球队会有弹窗提示创建球队
     *
     * @api {post} /api/team/order 约球队
     * @apiName team.order
     * @apiGroup team
     * @apiParam {Long} userId 用户Id <必传 />
     * @apiParam {Long} teamId 球队Id <必传 />
     * @apiParam {Long} time 约球时间
     * @apiParam {Long} cityId 约球地址
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response,
                      Long userId,
                      Long teamId,
                      Long time,
                      Long cityId) {

        if (null == teamId || userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        if (teamService.findListByLeaderId(userId) != null) {

            //被约球球队(客队)
            Team team1 = teamService.getById(teamId);
            //约球球队（主队）
            Team team2 = teamService.findListByLeaderId(userId);

            TeamRace teamRace = new TeamRace();
            teamRace.setHomeTeam(team2);
            teamRace.setVisitingTeam(team1);
            teamRace.setStartTime(time);
            teamRace.setStartTime(time);
            teamRace.setCityId(cityId);
            team2.setDeclareNum(team2.getDeclareNum() + 1);
            teamRaceService.create(teamRace);

            WebUtil.printApi(response, new Result(true).data(0));
        } else {
            //会有弹窗提示创建球队
            WebUtil.printApi(response, new Result(true).data(1));
        }
    }

    /**
     * 完成
     *
     * @api {post} /api/team/playerList 球员列表
     * @apiName team.playerList
     * @apiGroup team
     * @apiParam {Integer} teamId 球队Id <必传 />
     *
     * @apiSuccess {Object}  userList 队员
     * @apiSuccess {Long} userList.id 队员id
     * @apiSuccess {String} userList.name 队员昵称
     * @apiSuccess {String} userList.avater 队员头像
     *
     */
    @RequestMapping(value = "/playerList")
    public void playerList(HttpServletResponse response, Long teamId) {

        if (null == teamId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        //根据球队id会自动查询用户列表
        Team team = teamService.getById(teamId);
        List<User> userList = team.getList();

        Result obj = new Result(true).data(userList);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/team/schedule 日程
     * @apiName team.schedule
     * @apiGroup team
     * @apiParam {Long} teamId 球队Id <必传 />
     *
     * @apiSuccess {Object}  watchBallVos 球员所在球队为主队赛事列表
     * @apiSuccess {Long} watchBallVos.id 赛事id
     * @apiSuccess {String} watchBallVos.homeTeamName 主队队名
     * @apiSuccess {String} watchBallVos.homeTeamAvater 主队队徽
     * @apiSuccess {String} watchBallVos.vTeamName 客队队名
     * @apiSuccess {String} watchBallVos.vTeamAvater 客队队徽
     * @apiSuccess {Integer} watchBallVos.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} watchBallVos.stadiumName 球场名称
     * @apiSuccess {Long} watchBallVos.startTime 开始时间
     *
     * @apiSuccess {Object}  watchBallVoList 球员所在球队为客队赛事列表
     * @apiSuccess {Long} watchBallVoList.id 赛事id
     * @apiSuccess {String} watchBallVoList.homeTeamName 主队队名
     * @apiSuccess {String} watchBallVoList.homeTeamAvater 主队队徽
     * @apiSuccess {String} watchBallVoList.vTeamName 客队队名
     * @apiSuccess {String} watchBallVoList.vTeamAvater 客队队徽
     * @apiSuccess {Integer} watchBallVoList.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} watchBallVoList.stadiumName 球场名称
     * @apiSuccess {Long} watchBallVoList.startTime 开始时间
     */
    @RequestMapping(value = "/schedule")
    private void schedule(HttpServletResponse response, Long teamId) {

        if (null == teamId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        //球队为主队的球赛
        List<TeamRace> teamRaces = teamRaceService.findHomeId(teamId);

        WatchBallVo watchBallVo1 = new WatchBallVo();
        List<WatchBallVo> watchBallVos = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : teamRaces) {

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

        //球队为客队的球赛
        List<TeamRace> teamRaces1 = teamRaceService.findVisitingId(teamId);
        WatchBallVo watchBallVo2 = new WatchBallVo();
        List<WatchBallVo> watchBallVoList = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : teamRaces1) {

            watchBallVo2.setId(teamRace.getId());
            watchBallVo2.setStadiumName(teamRace.getStadium().getName());
            watchBallVo2.setStartTime(teamRace.getStartTime());
            watchBallVo2.setHomeTeamName(teamRace.getHomeTeam().getName());
            watchBallVo2.setHomeTeamAvater(teamRace.getHomeTeam().getAvater());
            watchBallVo2.setvTeamName(teamRace.getVisitingTeam().getName());
            watchBallVo2.setvTeamAvater(teamRace.getVisitingTeam().getAvater());
            watchBallVo2.setStatus(teamRace.getStatus());
            watchBallVoList.add(watchBallVo2);
        }

        map.put("watchBallVos", watchBallVos);
        map.put("watchBallVoList", watchBallVoList);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
