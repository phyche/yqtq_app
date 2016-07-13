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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private TeamMemberService teamMemberService;


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
     * @apiSuccess {String} list.address 球队地址

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
            if (StringUtils.isNotBlank(team.getAvater())) {
                team.setAvater(ConfigUtil.getString("upload.url") + team.getAvater());
            }
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj,"list","leaderUser","slogan","aveage","aveweight","aveheight","sum");
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
     * @apiSuccess {Object}  teamInfo.team 球队
     * @apiSuccess {Long} teamInfo.team.id 球队id
     * @apiSuccess {String} teamInfo.team.name 球队名称
     * @apiSuccess {String} teamInfo.team.avater 球队队徽
     * @apiSuccess {String} teamInfo.team.slogan 球队口号
     * @apiSuccess {String} teamInfo.team.address 球队地址
     * @apiSuccess {Double} teamInfo.team.aveAge 球队平均年龄
     * @apiSuccess {Double} teamInfo.team.aveHeight 球队平均身高
     * @apiSuccess {Double} teamInfo.team.aveWeight 球队平均体重
     *
     * @apiSuccess {Object}  teamInfo.team.leaderUser 队长
     * @apiSuccess {Long} teamInfo.team.leaderUser.id 队长id
     * @apiSuccess {String} teamInfo.team.leaderUser.avater 队长头像
     *
     * @apiSuccess {Object}  teamInfo.userList 队员
     * @apiSuccess {Long} teamInfo.userList.id 队员id
     * @apiSuccess {String} teamInfo.userList.avater 队员头像
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
        if (StringUtils.isNotBlank(team.getAvater())) {
            team.setAvater(ConfigUtil.getString("upload.url") + team.getAvater());
        }
        team.getLeaderUser().setAvater(team.getLeaderUser().getAvater());

        List<User> userList = new ArrayList<User>();
        if (teamMemberService.findByTeamId(teamId).size() != 0) {
            for (sum = 0; sum < team.getList().size(); sum++) {
                sumAge += team.getList().get(sum).getAge();
                sumHeight += team.getList().get(sum).getHeight();
                sumWeight += team.getList().get(sum).getWeight();
            }

            sumAge = sumAge + teamService.getById(teamId).getLeaderUser().getAge();
            sumHeight = sumHeight + teamService.getById(teamId).getLeaderUser().getHeight();
            sumWeight = sumWeight + teamService.getById(teamId).getLeaderUser().getWeight();

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
            sumAge = sumAge + teamService.getById(teamId).getLeaderUser().getAge();
            sumHeight = sumHeight + teamService.getById(teamId).getLeaderUser().getHeight();
            sumWeight = sumWeight + teamService.getById(teamId).getLeaderUser().getWeight();

            team.setAveAge(sumAge);
            team.setAveHeight(sumHeight);
            team.setAveWeight(sumWeight);
        }


        map.put("team", team);
        map.put("userList",userList);

        Result obj = new Result(true).data(createMap("teamInfo",map));
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
     * @apiParam {Stream} avater 队徽
     * @apiParam {String} address 球队地址
     *
     * @apiSuccess {Long}  teamId 球队id
     */
    @RequestMapping(value = "/add")
    public void add(HttpServletRequest request ,
                    HttpServletResponse response,
                    Long userId,
                    String name,
                    String address,
                    String slogan) throws IOException {

        MultipartFile multipartFile = null;
        MultipartRequest multipartRequest = null;

        if (null == userId || name == null || name == " " || address == null || address == " " || slogan == null || slogan == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        if(request instanceof MultipartRequest) {
            multipartRequest = (MultipartRequest) request;

        }

        Team team = new Team();

        /*MultipartFile multipartFile = multipartRequest.getFile("mainImage");
        if (null != multipartFile) {
            String url = QiNiuUploadImgUtil.upload(multipartFile);
            //magazine.setCover(url);
            team.setAvater(url);
        }*/

        if(multipartRequest != null) {
            multipartFile = multipartRequest.getFile("avater");
            if (null != multipartFile) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(multipartFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                team.setAvater(fileBo.getPath());
            }
        }
        team.setName(name);
        team.setAddress(address);
        team.setSlogan(slogan);
        team.setLeaderUser(userService.getById(userId));
        teamService.create(team);

        WebUtil.printApi(response, new Result(true).data(createMap("teamId",team.getId())));
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
     * @apiSuccess {Object}  list 队员
     * @apiSuccess {Long} list.user.id 队员id
     * @apiSuccess {String} list.user.name 队员昵称
     * @apiSuccess {String} list.user.avater 队员头像
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
        List<User> list = team.getList();
        for (User user : list) {
            if (StringUtils.isNotBlank(user.getAvater())) {
                user.setAvater(ConfigUtil.getString("upload.url") + user.getAvater());
            }
        }

        Result obj = new Result(true).data(createMap("list",list));
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
     * @apiSuccess {Object}  schedule 赛事列表
     * @apiSuccess {Object}  schedule.watchBallVos 球员所在球队为主队赛事列表
     * @apiSuccess {Long} schedule.watchBallVos.id 赛事id
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
     * @apiSuccess {Long} schedule.watchBallVoList.id 赛事id
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVoList.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVoList.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVoList.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVoList.stadiumName 球场名称
     * @apiSuccess {Long} schedule.watchBallVoList.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVoList.createDate 发起时间
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

        //球队为客队的球赛
        List<TeamRace> teamRaces1 = teamRaceService.findVisitingId(teamId);
        WatchBallVo watchBallVo2 = new WatchBallVo();
        List<WatchBallVo> watchBallVoList = new ArrayList<WatchBallVo>();
        for (TeamRace teamRace : teamRaces1) {

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
            watchBallVoList.add(watchBallVo2);
        }

        map.put("watchBallVos", watchBallVos);
        map.put("watchBallVoList", watchBallVoList);

        Result obj = new Result(true).data(createMap("schedule",map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
