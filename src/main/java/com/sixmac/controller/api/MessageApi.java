package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.MessageVo;
import com.sixmac.utils.*;
import com.sixmac.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Controller
@RequestMapping(value = "api/message")
public class MessageApi extends CommonController {

    @Autowired
    private MessageAddService messageAddService;

    @Autowired
    private MessageWatchingService messageWatchingService;

    @Autowired
    private MessageOrderBallService messageOrderBallService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private UserReserveService userReserveService;

    @Autowired
    private SystemMessageService systemMessageService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MessageJoinService messageJoinService;

    @Autowired
    private TeamRaceService teamRaceService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private MessageTeamService messageTeamService;

    @Autowired
    private UserService userService;

    /**
     * 完成
     *
     * @api {post} /api/message/orderBall 好友约球消息
     * @apiName message.orderBall
     * @apiGroup message
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  list.list1 好友约球消息列表
     * @apiSuccess {Long} list.list1.id 消息id
     * @apiSuccess {String} list.list1.content 消息内容
     * @apiSuccess {Object} list.list1.user 好友
     * @apiSuccess {Long} list.list1.user.id 好友d
     * @apiSuccess {String} list.list1.user.nickname 好友昵称
     * @apiSuccess {Object} list.list1.reserve 约球
     * @apiSuccess {Long} list.list1.reserve.id 约球d
     * @apiSuccess {Object} list.list1.reserve.stadium 约球球场
     * @apiSuccess {String} list.list1.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} list.list1.reserve.startTime 开始时间
     * @apiSuccess {Long} list.list1.createDate 记录生成时间
     *
     * @apiSuccess {Object}  list.list2 加入约球消息列表
     * @apiSuccess {Long} list.list2.id 加入约球id
     * @apiSuccess {String} list.list2.content 约球内容
     * @apiSuccess {Object} list.list2.user 加入约球人
     * @apiSuccess {Long} list.list2.user.id 加入约球人id
     * @apiSuccess {String} list.list2.user.nickname 加入约球人昵称
     * @apiSuccess {Object} list.list2.reserve 加入约球预约
     * @apiSuccess {Long} list.list2.reserve.id 加入约球预约id
     * @apiSuccess {Object} list.list2.reserve.stadium 约球球场
     * @apiSuccess {String} list.list2.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} list.list2.reserve.startTime 开始时间
     * @apiSuccess {Long} list.list2.createDate 记录生成时间
     *
     * @apiSuccess {Object}  list.list3 约球列表
     * @apiSuccess {Long} list.list3.id 约球id
     * @apiSuccess {String} list.list3.content 约球内容
     * @apiSuccess {Integer} list.list3.status 约球状态（1:组队成功2:组队失败）
     * @apiSuccess {Object} list.list3.reserve 约球预约
     * @apiSuccess {Long} list.list3.reserve.id 约球预约id
     * @apiSuccess {Object} list.list3.reserve.stadium 约球球场
     * @apiSuccess {String} list.list3.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} list.list3.reserve.startTime 开始时间
     * @apiSuccess {Long} list.list3.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/orderBall")
    public void orderBall(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        List<MessageOrderBall> list1 = messageOrderBallService.findByToUserId(userId);
        for (MessageOrderBall messageOrderBall : list1) {

            messageOrderBall.setContent("您的好友" + "user" + "约您去踢球啦！");

        }

        List<Reserve> reserveList = reserveService.findByUserId(userId);
        List<UserReserve> list2 = new ArrayList<UserReserve>();
        for (Reserve reserve : reserveList) {
            for (UserReserve userReserve : userReserveService.findByReserverId(reserve.getId())){

                userReserve.setContent("user" + "加入了您的约球");
                list2.add(userReserve);
            }
        }

        List<UserReserve> list3 = userReserveService.findByUserId(userId);
        for (UserReserve userReserve : list3) {

            Reserve reserve = reserveService.getById(userReserve.getReserveId());
            if (reserve.getStatus() == 1 || reserve.getStatus() == 2) {

                userReserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");
                userReserve.setStatus(reserve.getStatus());
                userReserveService.update(userReserve);

            }else {
                WebUtil.printApi(response, new Result(true).data("不用显示"));
            }
        }

        map.put("list1",list1);
        map.put("list2",list2);
        map.put("list3",list3);

        Result obj = new Result(true).data(createMap("list",map));
        String result = JsonUtil.obj2ApiJson(obj,"set","site","insurance","toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/doMessage 处理消息
     * @apiName message.doMessage
     * @apiGroup message
     * @apiParam {Long} messageOrderBallId 约球消息id <必传 />
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     * @apiParam {Integer} type 类型（1：处理约球消息，2：处理添加好友消息，3：处理加入球队消息，4：处理球队约战消息） <必传 />
     *
     */
    @RequestMapping(value = "/doMessage")
    public void doMessage(HttpServletResponse response, Long id, Integer status, Integer type) {

        if (null == id || status == null || type == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        if (type == 1) {
            MessageOrderBall messageOrderBall = messageOrderBallService.getById(id);
            messageOrderBall.setStatus(status);
            messageOrderBallService.update(messageOrderBall);

            if (status == 1) {
                UserReserve userReserve = new UserReserve();
                userReserve.setUser(messageOrderBall.getToUser());

                userReserve.setReserveId(messageOrderBall.getReserve().getId());
                //userReserve.setReserve(messageOrderBall.getReserve());

                userReserveService.create(userReserve);
            }
        }else if (type == 2) {
            MessageAdd messageAdd = messageAddService.getById(id);
            messageAdd.setStatus(status);
            messageAddService.update(messageAdd);
        }else if (type == 3) {
            MessageJoin messageJoin = messageJoinService.getById(id);
            messageJoin.setStatus(status);
            messageJoinService.update(messageJoin);

            if (status == 1) {
                TeamMember teamMember = new TeamMember();
                teamMember.setUserId(messageJoin.getUser().getId());
                teamMember.setTeamId(messageJoin.getTeam().getId());
                teamMemberService.create(teamMember);
            }
        }else if (type == 4) {
            TeamRace teamRace = teamRaceService.getById(id);
            teamRace.setStatus(status);
            if (status == 1) {
                teamRace.getVisitingTeam().setBattleNum(teamRace.getVisitingTeam().getBattleNum() + 1);
                teamRace.getHomeTeam().setDeclareNum(teamRace.getHomeTeam().getDeclareNum() + 1);
            }
            teamRaceService.update(teamRace);
        }


        WebUtil.printApi(response,new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/watching 约看消息
     * @apiName message.watching
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  list 约看列表
     * @apiSuccess {Long} list.id 约看id
     * @apiSuccess {Integer} list.type 类型（0：现场看球，1：直播看球）
     * @apiSuccess {Object} list.user 好友
     * @apiSuccess {Long} list.user.id 好友id
     * @apiSuccess {String} list.user.nickname 好友昵称
     * @apiSuccess {Object} list.bigRace 现场看球
     * @apiSuccess {Long} list.bigRace.id 现场看球id
     * @apiSuccess {String} list.bigRace.stadium.name 现场看球球场名字
     * @apiSuccess {Long} list.bigRace.startTime 现场看球开始时间
     *
     * @apiSuccess {Object} list.watchingRace 直播看球
     * @apiSuccess {Long} list.watchingRace.id 直播看球id
     * @apiSuccess {String} list.watchingRace.name 直播看球球馆名字
     * @apiSuccess {String} list.watchingRace.avater 直播看球球馆封面
     * @apiSuccess {Long} list.watchingRace.startTime 直播看球开始时间
     *
     * @apiSuccess {Long} list.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/watching")
    public void watching(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageWatching> list = messageWatchingService.findByToUserId(userId);
        for (MessageWatching messageWatching : list) {

            messageWatching.setContent("user" + "约您看球");
            messageWatching.getWatchingRace().setAvater(messageWatching.getWatchingRace().getAvater());
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/post 帖子消息
     * @apiName message.post
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  list 帖子消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {String} list.title 消息标题
     * @apiSuccess {Object} list.fUser 好友
     * @apiSuccess {Long} list.fUser.id 好友id
     * @apiSuccess {String} list.fUser.nickname 好友昵称
     * @apiSuccess {Object} list.post 帖子
     * @apiSuccess {Long} list.post.id 帖子id
     * @apiSuccess {String} list.post.content 帖子内容
     * @apiSuccess {Long} list.createDate 评论时间
     *
     *
     */
    @RequestMapping(value = "/post")
    public void post(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<PostComment> list = postCommentService.findByToUserId(userId);
        for (PostComment postComment : list) {
            postComment.setTitle("user" + "评论了您的" + "post");
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"toUser","content");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/system 系统消息
     * @apiName message.system
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {Integer} list.type 消息类型（1：系统消息，2：添加好友消息，3：好友请求消息）
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Long} list.createDate 消息时间
     * @apiSuccess {Object} list.toUser 好友
     * @apiSuccess {Long} list.toUser.id 好友id
     * @apiSuccess {String} list.nickname 好友昵称
     * @apiSuccess {Object} list.user 好友
     * @apiSuccess {Long} list.user.id 好友id
     *
     */
    @RequestMapping(value = "/system")
    public void system(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        List<SystemMessage> list1 = systemMessageService.findByToUserId(userId);
        List<MessageAdd> list2 = messageAddService.findByUserId(userId);
        List<MessageAdd> list3 = messageAddService.findByToUserId(userId);
        List<MessageVo> list = new ArrayList<MessageVo>();
        MessageVo messageVo = null;
        for (SystemMessage systemMessage : list1) {
            messageVo = new MessageVo();
            messageVo.setId(systemMessage.getId());
            messageVo.setContent(systemMessage.getTitle());
            messageVo.setCreateDate(systemMessage.getCreateDate());
            messageVo.setType(1);
            list.add(messageVo);
        }
        for (MessageAdd messageAdd : list2) {
            if (messageAdd.getUser().getId() == userId && messageAdd.getStatus() != 0) {

                messageVo = new MessageVo();
                messageVo.setId(messageAdd.getId());
                messageVo.setToUserId(userId);
                messageVo.setNickname(messageAdd.getToUser().getNickname());
                messageVo.setCreateDate(messageAdd.getCreateDate());
                messageVo.setType(2);
                if (messageAdd.getStatus() == 1) {
                    messageVo.setContent("user" + "同意了您的好友请求");

                } else if (messageAdd.getStatus() == 2) {
                    messageVo.setContent("user" + "拒绝了您的好友请求");
                }
                list.add(messageVo);

            }
        }
        for (MessageAdd messageAdd : list3) {
            if (messageAdd.getToUser().getId() == userId  && messageAdd.getStatus() == 0) {
                messageVo = new MessageVo();
                messageVo.setId(messageAdd.getId());
                messageVo.setUserId(userId);
                messageVo.setNickname(messageAdd.getUser().getNickname());
                messageVo.setCreateDate(messageAdd.getCreateDate());
                messageVo.setType(3);
                messageVo.setContent("user" + "添加您为好友");
                list.add(messageVo);
            }
        }

        Collections.sort(list,new Compare());

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/systemInfo 系统消息详情
     * @apiName message.systemInfo
     * @apiGroup message
     * @apiParam {Long} systemId 系统消息id <必传/>
     *
     * @apiSuccess {Object}  message 系统消息列表
     * @apiSuccess {Long} message.id 消息id
     * @apiSuccess {String} message.title 消息标题
     * @apiSuccess {String} message.content 消息内容
     * @apiSuccess {Long} message.createDate 消息时间
     *
     *
     */
    @RequestMapping(value = "/systemInfo")
    public void systemInfo(HttpServletResponse response, Long systemId) {

        if (null == systemId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        SystemMessage message = systemMessageService.getById(systemId);

        Result obj = new Result(true).data(createMap("message",message));
        String result = JsonUtil.obj2ApiJson(obj,"toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/joinTeam 加入球队消息
     * @apiName message.joinTeam
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Object}  list.beJoinTeamList 被邀请列表
     * @apiSuccess {Long} list.beJoinTeamList.id 被邀请消息id
     * @apiSuccess {String} list.beJoinTeamList.content 被邀请消息内容
     * @apiSuccess {Object} list.beJoinTeamList.user 邀请加入球队的用户
     * @apiSuccess {Long} list.beJoinTeamList.user.id 用户id
     * @apiSuccess {String} list.beJoinTeamList.user.nickname 用户昵称
     * @apiSuccess {Object} list.beJoinTeamList.team 邀请加入的球队
     * @apiSuccess {Long} list.beJoinTeamList.team.id 球队id
     * @apiSuccess {String} list.beJoinTeamList.team.name 球队名称
     * @apiSuccess {Long} list.beJoinTeamList.createDate 消息时间
     *
     * @apiSuccess {Object}  list.joinList 邀请列表
     * @apiSuccess {Long} list.joinList.id 邀请消息id
     * @apiSuccess {String} list.joinList.content 邀请消息内容
     * @apiSuccess {Object} list.joinList.toUser 邀请加入球队的用户
     * @apiSuccess {Long} list.joinList.toUser.id 用户id
     * @apiSuccess {String} list.joinList.toUser.nickname 用户昵称
     * @apiSuccess {Object} list.joinList.team 邀请加入的球队
     * @apiSuccess {Long} list.joinList.team.id 球队id
     * @apiSuccess {String} list.joinList.team.name 球队名称
     * @apiSuccess {Long} list.joinList.createDate 消息时间
     *
     * @apiSuccess {Object}  list.joinTeamList 申请列表
     * @apiSuccess {Long} list.joinTeamList.id 消息id
     * @apiSuccess {String} list.joinTeamList.content 消息内容
     * @apiSuccess {Object} list.joinTeamList.user 申请加入球队的用户
     * @apiSuccess {Long} list.joinTeamList.user.id 用户id
     * @apiSuccess {String} list.joinTeamList.user.nickname 用户昵称
     * @apiSuccess {Long} list.joinTeamList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/joinTeam")
    public void joinTeam(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        List<MessageTeam> list = messageTeamService.findByToUserId(userId);
        for (MessageTeam messageTeam : list) {
            messageTeam.setContent("user" + "邀请您加入" + "team");
        }
        map.put("beJoinTeamList", list);

        Team team = teamService.findListByLeaderId(userId);
        if (team == null) {
            WebUtil.printApi(response, new Result(true).data("不是队长，不能查看"));
        }else {

            List<MessageTeam> list1 = messageTeamService.findByTeam(team);
            for (MessageTeam messageTeam : list1) {
                if (messageTeam.getStatus() == 1) {

                    messageTeam.setContent("user" + "同意加入您的球队");
                } else if (messageTeam.getStatus() == 2) {

                    messageTeam.setContent("user" + "拒绝加入您的球队");
                }
            }
            map.put("joinList", list1);

            List<MessageJoin> list2 = messageJoinService.findByTeam(team);
            for (MessageJoin messageJoin : list2) {
                messageJoin.setContent("user" + "申请加入您的球队");
            }
            map.put("joinTeamList", list2);
        }

        Result obj = new Result(true).data(createMap("list",map));
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser","toUser");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/message/teamOrder 球队约战消息
     * @apiName message.teamOrder
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Object} list.visitingTeam 约战主队
     * @apiSuccess {Long} list.visitingTeam.id 主队id
     * @apiSuccess {String} list.visitingTeam.name 主队名称
     * @apiSuccess {String} list.visitingTeam.avater 主队队徽
     * @apiSuccess {Object} list.homeTeam 约战客队
     * @apiSuccess {Long} list.homeTeam.id 客队id
     * @apiSuccess {String} list.homeTeam.name 客队名称
     * @apiSuccess {String} list.homeTeam.avater 客队队徽
     * @apiSuccess {Long} list.startTime 约战时间
     * @apiSuccess {Long} list.createDate 消息时间
     *
     */
    @RequestMapping(value = "/teamOrder")
    public void teamOrder(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<Team> teamList = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(userId);
        User user = null;
        for (TeamMember teamMember : teamMemberList) {

            user = userService.getById(teamMember.getUserId());
            if (StringUtils.isNotBlank(user.getAvater())) {
                user.setAvater(ConfigUtil.getString("upload.url") + user.getAvater());
            }

            teamList.add(teamService.getById(teamMember.getTeamId()));
        }

        List<TeamRace> list = new ArrayList<TeamRace>();
        for (Team team : teamList) {
            List<TeamRace> teamRaceList1 = teamRaceService.findHomeId(team.getId());
            for (TeamRace teamRace : teamRaceList1) {
                if (teamRace.getStatus() == 1) {
                    teamRace.setContent("您的队伍和" + "visitingTeam" + "约战成功");

                    if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
                        teamRace.getHomeTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                    }
                    if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
                        teamRace.getVisitingTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                    }

                    list.add(teamRace);
                }
            }
            List<TeamRace> teamRaceList2 = teamRaceService.findVisitingId(team.getId());
            for (TeamRace teamRace : teamRaceList2) {
                if (teamRace.getStatus() == 1) {
                    teamRace.setContent("您的队伍和" + "homeTeam" + "约战成功");

                    if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
                        teamRace.getHomeTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                    }
                    if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
                        teamRace.getVisitingTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                    }

                    list.add(teamRace);
                }
            }
        }

        Team team = teamService.findListByLeaderId(userId);
        List<TeamRace> teamRaceList1 = teamRaceService.findHomeId(team.getId());
        for (TeamRace teamRace : teamRaceList1) {
            if (teamRace.getStatus() == 2) {
                teamRace.setContent("visitingTeam" + "拒绝了和您约战");

                if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
                    teamRace.getHomeTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                }
                if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
                    teamRace.getVisitingTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                }

                list.add(teamRace);
            }
        }
        List<TeamRace> teamRaceList2 = teamRaceService.findVisitingId(team.getId());
        for (TeamRace teamRace : teamRaceList2) {
            if (teamRace.getStatus() == 0) {
                teamRace.setContent("visitingTeam" + "约您对战");

                if (StringUtils.isNotBlank(teamRace.getHomeTeam().getAvater())) {
                    teamRace.getHomeTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                }
                if (StringUtils.isNotBlank(teamRace.getVisitingTeam().getAvater())) {
                    teamRace.getVisitingTeam().setAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                }

                list.add(teamRace);
            }
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser");
        WebUtil.printApi(response, result);
    }
}
