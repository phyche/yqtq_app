package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.utils.DateUtils;
import com.sixmac.utils.JsonUtil;
import com.sixmac.service.*;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 完成
     *
     * @api {post} /api/message/orderBall 好友约球消息
     * @apiName message.orderBall
     * @apiGroup message
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  messageOrderBallList 好友约球消息列表
     * @apiSuccess {Long} messageOrderBallList.id 消息id
     * @apiSuccess {String} messageOrderBallList.content 消息内容
     * @apiSuccess {Object} messageOrderBallList.user 好友
     * @apiSuccess {Long} messageOrderBallList.user.id 好友d
     * @apiSuccess {String} messageOrderBallList.user.nickname 好友昵称
     * @apiSuccess {Object} messageOrderBallList.reserve 约球
     * @apiSuccess {Long} messageOrderBallList.reserve.id 约球d
     * @apiSuccess {Object} messageOrderBallList.reserve.stadium 约球球场
     * @apiSuccess {String} messageOrderBallList.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} messageOrderBallList.reserve.startTime 开始时间
     *
     * @apiSuccess {Long} messageOrderBallList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/orderBall")
    public void orderBall(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageOrderBall> messageOrderBallList = messageOrderBallService.findByToUserId(userId);
        for (MessageOrderBall messageOrderBall : messageOrderBallList) {

            messageOrderBall.setContent("您的好友" + "user" + "约您去踢球啦！");

        }

        Result obj = new Result(true).data(messageOrderBallList);
        String result = JsonUtil.obj2ApiJson(obj,"set","site","list","insurance","toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/addOrder 加入的约球消息
     * @apiName message.addOrder
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  userReserveList 加入约球消息列表
     * @apiSuccess {Long} userReserveList.id 加入约球id
     * @apiSuccess {String} userReserveList.content 约球内容
     * @apiSuccess {Object} userReserveList.user 加入约球人
     * @apiSuccess {Long} userReserveList.user.id 加入约球人id
     * @apiSuccess {String} userReserveList.user.nickname 加入约球人昵称
     * @apiSuccess {Object} userReserveList.reserve 加入约球预约
     * @apiSuccess {Long} userReserveList.reserve.id 加入约球预约id
     * @apiSuccess {Object} userReserveList.reserve.stadium 约球球场
     * @apiSuccess {String} userReserveList.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} userReserveList.reserve.startTime 开始时间
     *
     * @apiSuccess {Long} userReserveList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/addOrder")
    public void addOrder(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<Reserve> reserveList = reserveService.findByUserId(userId);
        List<UserReserve> userReserveList = new ArrayList<UserReserve>();
        for (Reserve reserve : reserveList) {
            for (UserReserve userReserve : userReserveService.findByReserverId(reserve.getId())){

                userReserve.setContent("user" + "加入了您的约球");
                userReserveList.add(userReserve);
            }
        }

        Result obj = new Result(true).data(userReserveList);
        String result = JsonUtil.obj2ApiJson(obj,"site","list","insurance");
        WebUtil.printApi(response, result);
    }

    /**
     * 待定 内容状态存不进去
     *
     * @api {post} /api/message/myOrder 我参与的约球消息
     * @apiName message.myOrder
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  userReserveList 约球列表
     * @apiSuccess {Long} userReserveList.id 约球id
     * @apiSuccess {String} userReserveList.content 约球内容
     * @apiSuccess {Integer} userReserveList.status 约球状态（1:组队成功2:组队失败）
     * @apiSuccess {Object} userReserveList.reserve 约球预约
     * @apiSuccess {Long} userReserveList.reserve.id 约球预约id
     * @apiSuccess {Object} userReserveList.reserve.stadium 约球球场
     * @apiSuccess {String} userReserveList.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} userReserveList.reserve.startTime 开始时间
     *
     * @apiSuccess {Long} userReserveList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/myOrder")
    public void myOrder(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<UserReserve> userReserveList = userReserveService.findByUserId(userId);

        for (UserReserve userReserve : userReserveList) {
            if (userReserve.getReserve().getStatus() == 1 || userReserve.getReserve().getStatus() == 2) {

                userReserve.setContent(DateUtils.chinaDayOfWeekAndAM(new Date()) + "," + userReserve.getReserve().getStadium().getName() + "约球了");
                userReserve.setStatus(userReserve.getReserve().getStatus());
                userReserveService.update(userReserve);

                Result obj = new Result(true).data(userReserveList);
                String result = JsonUtil.obj2ApiJson(obj,"site","list","insurance");
                WebUtil.printApi(response, result);
            }else {
                WebUtil.printApi(response, new Result(true).data("不用显示"));
            }

        }
    }

    /**
     * 完成
     *
     * @api {post} /api/message/doOrder 处理好友约球消息
     * @apiName message.doOrder
     * @apiGroup message
     * @apiParam {Long} messageOrderBallId 约球消息id <必传 />
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doOrder")
    public void doOrder(HttpServletResponse response, Long messageOrderBallId, Integer status) {

        if (null == messageOrderBallId || status == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageOrderBall messageOrderBall = messageOrderBallService.getById(messageOrderBallId);
        messageOrderBall.setStatus(status);
        messageOrderBallService.update(messageOrderBall);

        if (status == 1) {
            UserReserve userReserve = new UserReserve();
            userReserve.setUser(messageOrderBall.getToUser());
            userReserve.setReserve(messageOrderBall.getReserve());

            userReserveService.create(userReserve);
        }

        WebUtil.printApi(response,new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/watching 约看消息
     * @apiName message.watching
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  messageWatchingList 约看列表
     * @apiSuccess {Long} messageWatchingList.id 约看id
     * @apiSuccess {Object} messageWatchingList.user 好友
     * @apiSuccess {Long} messageWatchingList.user.id 好友id
     * @apiSuccess {String} messageWatchingList.user.nickname 好友昵称
     * @apiSuccess {Object} messageWatchingList.bigRace 现场看球
     * @apiSuccess {Long} messageWatchingList.bigRace.id 现场看球id
     * @apiSuccess {String} messageWatchingList.bigRace.stadium.name 现场看球球场名字
     * @apiSuccess {Long} messageWatchingList.bigRace.startTime 现场看球开始时间
     *
     * @apiSuccess {Object} messageWatchingList.watchingRace 直播看球
     * @apiSuccess {Long} messageWatchingList.watchingRace.id 直播看球id
     * @apiSuccess {String} messageWatchingList.watchingRace.name 直播看球球馆名字
     * @apiSuccess {String} messageWatchingList.watchingRace.avater 直播看球球馆封面
     * @apiSuccess {Long} messageWatchingList.watchingRace.startTime 直播看球开始时间
     *
     * @apiSuccess {Long} messageWatchingList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/watching")
    public void watching(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageWatching> messageWatchingList = messageWatchingService.findByToUserId(userId);
        for (MessageWatching messageWatching : messageWatchingList) {

            messageWatching.setContent("user" + "约您看球");
        }

        Result obj = new Result(true).data(messageWatchingList);
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
     * @apiSuccess {Object}  postCommentList 帖子消息列表
     * @apiSuccess {Long} postCommentList.id 消息id
     * @apiSuccess {String} postCommentList.title 消息标题
     * @apiSuccess {Object} postCommentList.fUser 好友
     * @apiSuccess {Long} postCommentList.fUser.id 好友id
     * @apiSuccess {String} postCommentList.fUser.nickname 好友昵称
     * @apiSuccess {Object} postCommentList.post 帖子
     * @apiSuccess {Long} postCommentList.post.id 帖子id
     * @apiSuccess {String} postCommentList.post.content 帖子内容
     * @apiSuccess {Long} postCommentList.createDate 评论时间
     *
     *
     */
    @RequestMapping(value = "/post")
    public void post(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<PostComment> postCommentList = postCommentService.findByToUserId(userId);
        for (PostComment postComment : postCommentList) {
            postComment.setTitle("user" + "评论了您的" + "post");
        }

        Result obj = new Result(true).data(postCommentList);
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
     * @apiSuccess {Object}  messageList 系统消息列表
     * @apiSuccess {Long} messageList.id 消息id
     * @apiSuccess {String} messageList.title 消息标题
     * @apiSuccess {Long} messageList.createDate 消息时间
     *
     *
     */
    @RequestMapping(value = "/system")
    public void system(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<SystemMessage> messageList = systemMessageService.findByToUserId(userId);

        Result obj = new Result(true).data(messageList);
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

        Result obj = new Result(true).data(message);
        String result = JsonUtil.obj2ApiJson(obj,"toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/toAdd 主动添加好友消息
     * @apiName message.toAdd
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageAddList 消息列表
     * @apiSuccess {Long} messageAddList.id 消息id
     * @apiSuccess {String} messageAddList.content 消息内容
     * @apiSuccess {Object} messageAddList.toUser 好友
     * @apiSuccess {Long} messageAddList.toUser.id 好友id
     * @apiSuccess {String} messageAddList.toUser.nickname 好友昵称
     * @apiSuccess {Long} messageAddList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/toAdd")
    public void toAdd(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageAdd> messageAddList = messageAddService.findByUserId(userId);

        for (MessageAdd messageAdd : messageAddList) {

            if (messageAdd.getStatus() == 1) {
                messageAdd.setContent("user" + "同意了您的好友请求");
            }
            if (messageAdd.getStatus() == 2) {
                messageAdd.setContent("user" + "拒绝了您的好友请求");
            }
        }

        Result obj = new Result(true).data(messageAddList);
        String result = JsonUtil.obj2ApiJson(obj,"user");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/beAdd 被动添加好友消息
     * @apiName message.beAdd
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageAddList 消息列表
     * @apiSuccess {Long} messageAddList.id 消息id
     * @apiSuccess {String} messageAddList.content 消息内容
     * @apiSuccess {Object} messageAddList.user 好友
     * @apiSuccess {Long} messageAddList.user.id 好友id
     * @apiSuccess {String} messageAddList.user.nickname 好友昵称
     * @apiSuccess {Long} messageAddList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/beAdd")
    public void beAdd(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageAdd> messageAddList = messageAddService.findByToUserId(userId);

        for (MessageAdd messageAdd : messageAddList) {

            messageAdd.setContent("user" + "添加您为好友");

        }

        Result obj = new Result(true).data(messageAddList);
        String result = JsonUtil.obj2ApiJson(obj,"toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/doAdd 处理好友添加消息
     * @apiName message.doAdd
     * @apiGroup message
     * @apiParam {Long} messageAddId 添加消息id <必传 />
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doAdd")
    public void doAdd(HttpServletResponse response, Long messageAddId, Integer status) {

        if (null == messageAddId || status == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageAdd messageAdd = messageAddService.getById(messageAddId);
        messageAdd.setStatus(status);
        messageAddService.update(messageAdd);

        WebUtil.printApi(response,new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/joinTeam 加入球队消息
     * @apiName message.joinTeam
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageJoinList 消息列表
     * @apiSuccess {Long} messageJoinList.id 消息id
     * @apiSuccess {String} messageJoinList.content 消息内容
     * @apiSuccess {Object} messageJoinList.user 申请加入球队的用户
     * @apiSuccess {Long} messageJoinList.user.id 用户id
     * @apiSuccess {String} messageJoinList.user.nickname 用户昵称
     * @apiSuccess {Long} messageJoinList.createDate 消息时间
     *
     */
    @RequestMapping(value = "joinTeam")
    public void joinTeam(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Team team = teamService.findListByLeaderId(userId);

        if (team == null) {
            WebUtil.printApi(response, new Result(true).data("不是队长，不能查看申请加入球队消息"));
        }else {

            List<MessageJoin> messageJoinList = messageJoinService.findByTeam(team);
            for (MessageJoin messageJoin : messageJoinList) {
                messageJoin.setContent("user" + "申请加入您的球队");
            }

            Result obj = new Result(true).data(messageJoinList);
            String result = JsonUtil.obj2ApiJson(obj,"team");
            WebUtil.printApi(response, result);
        }
    }

    /**
     * 完成
     *
     * @api {post} /api/message/doJoinTeam 处理加入球队消息
     * @apiName message.doJoinTeam
     * @apiGroup message
     * @apiParam {Long} messageJoinId 消息id <必传/>
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doJoinTeam")
    public void doJoinTeam(HttpServletResponse response, Long messageJoinId, Integer status) {

        if (null == messageJoinId || status == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageJoin messageJoin = messageJoinService.getById(messageJoinId);
        messageJoin.setStatus(status);
        messageJoinService.update(messageJoin);

        if (status == 1) {
            TeamMember teamMember = new TeamMember();
            teamMember.setUser(messageJoin.getUser());
            teamMember.setTeam(messageJoin.getTeam());
            teamMemberService.create(teamMember);
        }

        WebUtil.printApi(response, new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/beJoinTeam 被邀请加入球队消息
     * @apiName message.beJoinTeam
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageTeamList 消息列表
     * @apiSuccess {Long} messageTeamList.id 消息id
     * @apiSuccess {String} messageTeamList.content 消息内容
     * @apiSuccess {Object} messageTeamList.user 邀请加入球队的用户
     * @apiSuccess {Long} messageTeamList.user.id 用户id
     * @apiSuccess {String} messageTeamList.user.nickname 用户昵称
     * @apiSuccess {Object} messageTeamList.team 邀请加入的球队
     * @apiSuccess {Long} messageTeamList.team.id 球队id
     * @apiSuccess {String} messageTeamList.team.name 球队名称
     * @apiSuccess {Long} messageTeamList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/beJoinTeam")
    public void beJoinTeam(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageTeam> messageTeamList = messageTeamService.findByToUserId(userId);


        for (MessageTeam messageTeam : messageTeamList) {
            messageTeam.setContent("user" + "邀请您加入" + "team");
        }

        Result obj = new Result(true).data(messageTeamList);
        String result = JsonUtil.obj2ApiJson(obj,"list","leaderUser","toUser");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/message/doBeJoinTeam 处理被邀请加入球队消息
     * @apiName message.doBeJoinTeam
     * @apiGroup message
     * @apiParam {Long} messageTeamId 消息id <必传/>
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doBeJoinTeam")
    public void doBeJoinTeam(HttpServletResponse response, Long messageTeamId, Integer status) {

        if (null == messageTeamId || status == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageTeam messageTeam = messageTeamService.getById(messageTeamId);
        messageTeam.setStatus(status);
        messageTeamService.update(messageTeam);

        if (status == 1) {
            TeamMember teamMember = new TeamMember();
            teamMember.setUser(messageTeam.getToUser());
            teamMember.setTeam(messageTeam.getTeam());
            teamMemberService.create(teamMember);
        }

        WebUtil.printApi(response, new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/Join 邀请加入球队消息
     * @apiName message.Join
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageTeamList 消息列表
     * @apiSuccess {Long} messageTeamList.id 消息id
     * @apiSuccess {String} messageTeamList.content 消息内容
     * @apiSuccess {Object} messageTeamList.toUser 邀请加入球队的用户
     * @apiSuccess {Long} messageTeamList.toUser.id 用户id
     * @apiSuccess {String} messageTeamList.toUser.nickname 用户昵称
     * @apiSuccess {Object} messageTeamList.team 邀请加入的球队
     * @apiSuccess {Long} messageTeamList.team.id 球队id
     * @apiSuccess {String} messageTeamList.team.name 球队名称
     * @apiSuccess {Long} messageTeamList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/Join")
    public void Join(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Team team = teamService.findListByLeaderId(userId);

        if (team == null) {

            WebUtil.printApi(response, new Result(true).data("不是队长，不能查看被邀请加入球队消息"));
        }else {

            List<MessageTeam> messageTeamList = messageTeamService.findByTeam(team);

            for (MessageTeam messageTeam : messageTeamList) {
                if (messageTeam.getStatus() == 1) {

                    messageTeam.setContent("user" + "同意加入您的球队");
                } else if (messageTeam.getStatus() == 2) {

                    messageTeam.setContent("user" + "拒绝加入您的球队");
                }

            }

            Result obj = new Result(true).data(messageTeamList);
            String result = JsonUtil.obj2ApiJson(obj,"list","leaderUser","user");
            WebUtil.printApi(response, result);
        }
    }

    /**
     * 完成
     *
     * @api {post} /api/message/teamOrder 球队约战消息
     * @apiName message.teamOrder
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageJoinList 消息列表
     * @apiSuccess {Long} messageJoinList.id 消息id
     * @apiSuccess {String} messageJoinList.content 消息内容
     * @apiSuccess {Object} messageJoinList.visitingTeam 约战主队
     * @apiSuccess {Long} messageJoinList.visitingTeam.id 主队id
     * @apiSuccess {String} messageJoinList.visitingTeam.name 主队名称
     * @apiSuccess {String} messageJoinList.visitingTeam.avater 主队队徽
     * @apiSuccess {Object} messageJoinList.homeTeam 约战客队
     * @apiSuccess {Long} messageJoinList.homeTeam.id 客队id
     * @apiSuccess {String} messageJoinList.homeTeam.name 客队名称
     * @apiSuccess {String} messageJoinList.homeTeam.avater 客队队徽
     * @apiSuccess {Long} messageJoinList.startTime 约战时间
     * @apiSuccess {Long} messageJoinList.createDate 消息时间
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
        for (TeamMember teamMember : teamMemberList) {
            teamList.add(teamMember.getTeam());
        }

        List<TeamRace> teamRaceList = new ArrayList<TeamRace>();
        for (Team team : teamList) {
            List<TeamRace> teamRaceList1 = teamRaceService.findHomeId(team.getId());
            for (TeamRace teamRace : teamRaceList1) {
                if (teamRace.getStatus() == 1) {
                    teamRace.setContent("您的队伍和" + "visitingTeam" + "约战成功");
                    teamRaceList.add(teamRace);
                }
            }
            List<TeamRace> teamRaceList2 = teamRaceService.findVisitingId(team.getId());
            for (TeamRace teamRace : teamRaceList2) {
                if (teamRace.getStatus() == 1) {
                    teamRace.setContent("您的队伍和" + "homeTeam" + "约战成功");
                    teamRaceList.add(teamRace);
                }
            }
        }

        Team team = teamService.findListByLeaderId(userId);
        List<TeamRace> teamRaceList1 = teamRaceService.findHomeId(team.getId());
        for (TeamRace teamRace : teamRaceList1) {
            if (teamRace.getStatus() == 2) {
                teamRace.setContent("visitingTeam" + "拒绝了和您约战");
                teamRaceList.add(teamRace);
            }
        }
        List<TeamRace> teamRaceList2 = teamRaceService.findVisitingId(team.getId());
        for (TeamRace teamRace : teamRaceList2) {
            if (teamRace.getStatus() == 0) {
                teamRace.setContent("visitingTeam" + "约您对战");
                teamRaceList.add(teamRace);
            }
        }

        Result obj = new Result(true).data(teamRaceList);
        String result = JsonUtil.obj2ApiJson(obj,"leaderUser","list");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/doTeamOrder 处理球队约战消息
     * @apiName message.doTeamOrder
     * @apiGroup message
     * @apiParam {Long} messageTeamId 消息id <必传/>
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doTeamOrder")
    public void doTeamOrder(HttpServletResponse response, Long teamRaceId, Integer status) {

        if (null == teamRaceId || status == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        TeamRace teamRace = teamRaceService.getById(teamRaceId);
        teamRace.setStatus(status);
        teamRaceService.update(teamRace);

        WebUtil.printApi(response, new Result(true).data(0));
    }
}
