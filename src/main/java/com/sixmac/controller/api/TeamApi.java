package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.WatchBallVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private MessageRecordService messageRecordService;


    /**
     * 完成
     *
     *  @api {post} /api/team/list 球队列表
     * @apiName team.list
     * @apiGroup team
     * @apiParam {String} name 球队名字
     * @apiParam {Long} cityId 城市id <必传/>
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
                     Long cityId,
                     Integer pageNum,
                     Integer pageSize) {


        Page<Team> page = teamService.page(name, cityId, pageNum, pageSize);
        List<Team> list = page.getContent();
        for (Team team : list) {
            team.setNum(team.getBattleNum() + team.getDeclareNum());
            if (StringUtils.isNotBlank(team.getAvater())) {
                team.setAvater(ConfigUtil.getString("upload.url") + team.getAvater());
            }
            // 球队人数：球员加队长
            team.setCount(team.getList().size() + 1);
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser","slogan","aveage","aveweight","aveheight","sum");
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
     * @apiSuccess {String} teamInfo.userList.nickname 队员昵称
     *
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Long teamId) {

        if (null == teamId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = teamService.info(response, teamId);

        Result obj = new Result(true).data(createMap("teamInfo",map));
        String result = JsonUtil.obj2ApiJson(obj,"declareNum","battleNum","sum");
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

        teamService.addFriend(response, userId, toUserId);

        WebUtil.printApi(response, new Result(true));
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

        teamService.apply(response, userId, teamId);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/team/order 约球队
     * @apiName team.order
     * @apiGroup team
     * @apiParam {Long} userId 用户Id <必传 />
     *
     * @apiSuccess {Object}  team 球队
     * @apiSuccess {Long} team.id 球队id
     * @apiSuccess {String} team.name 球队名称
     * @apiSuccess {String} team.avater 球队队徽
     * @apiSuccess {String} team.address 球队地址
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response,
                          Long userId) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Team team = null;
        if (teamService.findListByLeaderId(userId) != null) {
            team = teamService.findListByLeaderId(userId);
        }
        WebUtil.printApi(response, new Result(true).data(createMap("team",team)));
    }

    /**
     * 完成
     *
     * @api {post} /api/team/orderInfo 约球队详情
     * @apiName team.orderInfo
     * @apiGroup team
     * @apiParam {Long} team1Id 主队Id <必传 />
     * @apiParam {Long} team2Id 客队Id <必传 />
     * @apiParam {Long} time 约球时间
     */
    @RequestMapping(value = "/orderInfo")
    public void orderInfo(HttpServletResponse response,
                      Long team1Id,
                      Long team2Id,
                      Long time,
                      Long cityId) {

        if (null == team1Id || team2Id == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        teamService.orderInfo(response, team1Id, team2Id, time, cityId);

        WebUtil.printApi(response, new Result(true));

    }

    /**
     * 完成
     *
     * @api {post} /api/team/schedule 日程
     * @apiName team.schedule
     * @apiGroup team
     * @apiParam {Long} teamId 球队Id
     * @apiParam {Long} userId 用户Id <必传 />
     *
     * @apiSuccess {Object}  schedule 赛事列表
     * @apiSuccess {Object}  schedule.watchBallVos 球员所在球队为主队赛事列表
     * @apiSuccess {Long} schedule.watchBallVos.id 赛事id
     * @apiSuccess {String} schedule.watchBallVos.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVos.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVos.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVos.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVos.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVos.stadiumName 地址
     * @apiSuccess {Long} schedule.watchBallVos.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVos.createDate 发起时间
     * @apiSuccess {String} schedule.watchBallVos.mobile 手机号
     *
     * @apiSuccess {Object}  schedule.watchBallVoList 球员所在球队为客队赛事列表
     * @apiSuccess {Long} schedule.watchBallVoList.id 赛事id
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVoList.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVoList.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVoList.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVoList.stadiumName 地址
     * @apiSuccess {Long} schedule.watchBallVoList.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVoList.createDate 发起时间
     * @apiSuccess {String} schedule.watchBallVoList.mobile 手机号
     */
    @RequestMapping(value = "/schedule")
    public void schedule(HttpServletResponse response, Long teamId, Long userId) {

        if (null == teamId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = teamRaceService.schedule(response, teamId, userId);

        Result obj = new Result(true).data(createMap("schedule",map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/team/raceAddress 球赛地址
     * @apiName team.raceAddress
     * @apiGroup team
     * @apiParam {Long} teamRaceId 球赛Id <必传 />
     * @apiParam {String} address 地址 <必传 />
     *
     *
     */
    @RequestMapping(value = "raceAddress")
    public void raceAddress(HttpServletResponse response,
                            Long teamRaceId,
                            String address) {

        TeamRace teamRace = teamRaceService.getById(teamRaceId);
        teamRace.setAddress(address);
        teamRaceService.update(teamRace);

        Result obj = new Result(true);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
