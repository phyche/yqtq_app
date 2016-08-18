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
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {Integer} list.type 消息类型（4：好友约球消息，5：好友加入约球消息，6：约球成功或失败消息）
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Long} list.createDate 消息时间
     * @apiSuccess {Long} list.startTime 约球开始时间
     * @apiSuccess {Long} list.userId 好友id (好友请求)
     * @apiSuccess {String} list.nickname 好友昵称
     * @apiSuccess {String} list.stadiumname 球场名称
     * @apiSuccess {Integer} list.status 约球状态（1：成功，2：失败）
     *
     */
    @RequestMapping(value = "/orderBall")
    public void orderBall(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageVo> messageVoList = new ArrayList<MessageVo>();
        MessageVo messageVo = null;
        List<MessageOrderBall> list1 = messageOrderBallService.findByToUserId(userId);
        for (MessageOrderBall messageOrderBall : list1) {

            messageVo = new MessageVo();
            messageVo.setId(messageOrderBall.getId());
            messageVo.setUserId(messageOrderBall.getUser().getId());
            messageVo.setNickname(messageOrderBall.getUser().getNickname());
            messageVo.setContent("您的好友" + "user" + "约您去踢球啦！");
            messageVo.setCreateDate(messageOrderBall.getCreateDate());
            messageVo.setStartTime(messageOrderBall.getReserve().getStartTime());
            messageVo.setStadiumName(messageOrderBall.getReserve().getStadium().getName());
            messageVo.setType(4);
            messageVoList.add(messageVo);

        }

        List<Reserve> reserveList = reserveService.findByUserId(userId);
        List<UserReserve> list2 = new ArrayList<UserReserve>();
        for (Reserve reserve : reserveList) {
            for (UserReserve userReserve : userReserveService.findByReserverId(reserve.getId())){

                messageVo = new MessageVo();
                messageVo.setUserId(userReserve.getUser().getId());
                messageVo.setNickname(userReserve.getUser().getNickname());
                messageVo.setContent("user" + "加入了您的约球");
                messageVo.setCreateDate(userReserve.getCreateDate());
                messageVo.setStartTime(reserve.getStartTime());
                messageVo.setStadiumName(reserve.getStadium().getName());
                messageVo.setType(5);
                messageVoList.add(messageVo);
            }
        }

        List<UserReserve> list3 = userReserveService.findByUserId(userId);
        Reserve reserve = null;
        for (UserReserve userReserve : list3) {

            reserve = reserveService.getById(userReserve.getReserveId());
            if (reserve.getStatus() == 1 || reserve.getStatus() == 2) {
                messageVo = new MessageVo();
                messageVo.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + reserve.getStadium().getName() + "约球了");
                messageVo.setCreateDate(userReserve.getCreateDate());
                messageVo.setStartTime(reserve.getStartTime());
                messageVo.setStadiumName(reserve.getStadium().getName());
                messageVo.setType(6);
                messageVo.setStatus(reserve.getStatus());
                messageVoList.add(messageVo);
            }
        }

        Collections.sort(messageVoList,new Compare());

        Result obj = new Result(true).data(createMap("list",messageVoList));
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
            if (messageWatching.getType() == 0) {
                messageWatching.setWatchingRace(new WatchingRace());
            }else if (messageWatching.getType() == 1) {
                messageWatching.setBigRace(new BigRace());
            }
            if (messageWatching.getWatchingRace()!= null && StringUtils.isNotBlank(messageWatching.getWatchingRace().getAvater())) {
                messageWatching.getWatchingRace().setAvater(messageWatching.getWatchingRace().getAvater());
            }
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
     * @apiSuccess {Integer} list.type 消息类型（1：系统消息，2：添加好友消息，3：好友请求消息）
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Long} list.createDate 消息时间
     * @apiSuccess {Long} list.toUserId 好友id (添加好友)
     * @apiSuccess {String} list.nickname 好友昵称
     * @apiSuccess {Long} list.userId 好友id (好友请求)
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
                messageVo.setCreateDate(messageAdd.getUpdateDate());
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
     * @api {post} /api/message/team 球队消息
     * @apiName message.team
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {Integer} list.type 消息类型（7：被邀请加入球队消息，8：邀请加入球队，9：申请加入球队, 10：约战成功,11：约战失败,12：申请约战）
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Long} list.createDate 消息时间
     * @apiSuccess {Long} list.toUserId 好友id (邀请加入球队)
     * @apiSuccess {String} list.nickname 好友昵称
     * @apiSuccess {Long} list.userId 好友id (被邀请加入球队、申请加入球队)
     * @apiSuccess {Long} list.userId 球队id
     * @apiSuccess {String} list.nickname 球队名称
     * @apiSuccess {String} list.nickname 球队队徽
     *
     */
    @RequestMapping(value = "/team")
    public void team(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageVo> messageVoList = new ArrayList<MessageVo>();
        MessageVo messageVo = null;
        List<MessageTeam> list = messageTeamService.findByToUserId(userId);
        for (MessageTeam messageTeam : list) {

            messageVo = new MessageVo();
            messageVo.setId(messageTeam.getId());
            messageVo.setUserId(messageTeam.getTeam().getLeaderUser().getId());
            messageVo.setNickname(messageTeam.getTeam().getLeaderUser().getNickname());
            messageVo.setTeamId(messageTeam.getTeam().getId());
            messageVo.setTeamName(messageTeam.getTeam().getName());
            messageVo.setType(7);
            messageVo.setCreateDate(messageTeam.getCreateDate());
            messageVo.setContent("user" + "邀请您加入" + "team");
            messageVoList.add(messageVo);
        }

        Team team = teamService.findListByLeaderId(userId);
        if (team == null) {
            WebUtil.printApi(response, new Result(true));
        }else {

            List<MessageTeam> list1 = messageTeamService.findByTeam(team);
            for (MessageTeam messageTeam : list1) {
                messageVo = new MessageVo();
                messageVo.setToUserId(messageTeam.getUser().getId());
                messageVo.setNickname(messageTeam.getUser().getNickname());
                messageVo.setStatus(messageTeam.getStatus());
                if (messageTeam.getStatus() == 1) {
                    messageVo.setContent("user" + "同意加入您的球队");
                } else if (messageTeam.getStatus() == 2) {
                    messageVo.setContent("user" + "拒绝加入您的球队");
                }
                messageVo.setType(8);
                messageVo.setCreateDate(messageTeam.getUpdateDate());
                messageVoList.add(messageVo);
            }


            List<MessageJoin> list2 = messageJoinService.findByTeam(team);
            for (MessageJoin messageJoin : list2) {
                messageVo = new MessageVo();
                messageVo.setUserId(messageJoin.getUser().getId());
                messageVo.setNickname(messageJoin.getUser().getNickname());
                messageVo.setContent("user" + "申请加入您的球队");
                messageVo.setType(9);
                messageVo.setCreateDate(messageJoin.getCreateDate());
                messageVoList.add(messageVo);
            }

        }

        List<Team> teamList = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(userId);
        User user = null;
        for (TeamMember teamMember : teamMemberList) {
            teamList.add(teamService.getById(teamMember.getTeamId()));
        }

        for (Team team1 : teamList) {
            List<TeamRace> teamRaceList1 = teamRaceService.findHomeId(team1.getId());
            for (TeamRace teamRace : teamRaceList1) {
                if (teamRace.getStatus() == 1) {
                    messageVo = new MessageVo();
                    messageVo.setTeamId(teamRace.getVisitingTeam().getId());
                    messageVo.setTeamName(teamRace.getVisitingTeam().getName());
                    messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                    messageVo.setContent("您的队伍和" + "visitingTeam" + "约战成功");
                    messageVo.setType(10);
                    messageVo.setCreateDate(teamRace.getUpdateDate());
                    messageVoList.add(messageVo);
                }
            }
            List<TeamRace> teamRaceList2 = teamRaceService.findVisitingId(team.getId());
            for (TeamRace teamRace : teamRaceList2) {
                if (teamRace.getStatus() == 1) {
                    messageVo = new MessageVo();
                    messageVo.setTeamId(teamRace.getHomeTeam().getId());
                    messageVo.setTeamName(teamRace.getHomeTeam().getName());
                    messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                    messageVo.setContent("您的队伍和" + "homeTeam" + "约战成功");
                    messageVo.setType(10);
                    messageVo.setCreateDate(teamRace.getUpdateDate());
                    messageVoList.add(messageVo);
                }
            }
        }

        List<TeamRace> teamRaceList1 = teamRaceService.findHomeId(team.getId());
        for (TeamRace teamRace : teamRaceList1) {
            if (teamRace.getStatus() == 2) {

                messageVo = new MessageVo();
                messageVo.setTeamId(teamRace.getVisitingTeam().getId());
                messageVo.setTeamName(teamRace.getVisitingTeam().getName());
                messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                messageVo.setContent("visitingTeam" + "拒绝了和您约战");
                messageVo.setType(11);
                messageVo.setCreateDate(teamRace.getUpdateDate());
                messageVoList.add(messageVo);
            }
        }
        List<TeamRace> teamRaceList2 = teamRaceService.findVisitingId(team.getId());
        for (TeamRace teamRace : teamRaceList2) {
            if (teamRace.getStatus() == 0) {

                messageVo = new MessageVo();
                messageVo.setTeamId(teamRace.getHomeTeam().getId());
                messageVo.setTeamName(teamRace.getHomeTeam().getName());
                messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                messageVo.setContent("homeTeam" + "约您对战");
                messageVo.setType(12);
                messageVo.setCreateDate(teamRace.getCreateDate());
                messageVoList.add(messageVo);
            }
        }

        Collections.sort(messageVoList, new Compare());

        Result obj = new Result(true).data(createMap("list",messageVoList));
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
