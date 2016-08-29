package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.MessageVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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

    @Autowired
    private PostService postService;

    @Autowired
    private MessageRecordService messageRecordService;

    @Autowired
    private MessageService messageService;

    /**
     * 完成
     *
     * @api {post} /api/message/orderBall 好友约球消息
     * @apiName message.orderBall
     * @apiGroup message
     * @apiParam {Integer} userId 用户id <必传 />
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
     * @apiSuccess {Integer} list.reserveType 约球类型（0：私人，1：公共）
     * @apiSuccess {Integer} list.matchType 约球赛制（N赛制）
     * @apiSuccess {Integer} list.joinCount 约球参加人数
     * @apiSuccess {Integer} list.lackNum 约球缺的人数
     * @apiSuccess {Long} list.reserveId 约球id
     */
    @RequestMapping(value = "/orderBall")
    public void orderBall(HttpServletResponse response, Long userId) throws ParseException {

        if (null == userId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageVo> messageVoList = messageService.orderBall(response, userId);

        Result obj = new Result(true).data(createMap("list", messageVoList));
        String result = JsonUtil.obj2ApiJson(obj, "set", "site", "insurance", "toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/doMessage 处理消息
     * @apiName message.doMessage
     * @apiGroup message
     * @apiParam {Long} id 消息id <必传 />
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     * @apiParam {Integer} type 类型（1：处理约球消息，2：处理添加好友消息，3：处理加入球队消息，4：处理球队约战消息） <必传 />
     */
    @RequestMapping(value = "/doMessage")
    public void doMessage(HttpServletResponse response, Long id, Integer status, Integer type) {

        if (null == id || status == null || type == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        messageService.doMessage(response, id, status, type);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/watching 约看消息
     * @apiName message.watching
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     * @apiSuccess {Object}  list 约看列表
     * @apiSuccess {Long} list.id 约看id
     * @apiSuccess {Integer} list.type 类型（0：现场看球，1：直播看球）
     * @apiSuccess {Object} list.user 好友
     * @apiSuccess {Long} list.user.id 好友id
     * @apiSuccess {String} list.user.nickname 好友昵称
     * @apiSuccess {Object} list.bigRace 现场看球
     * @apiSuccess {Long} list.bigRace.id 现场看球id
     * @apiSuccess {String} list.bigRace.stadium.name 现场看球球场名字
     * @apiSuccess {Long} list.bigRace.startDate 现场看球开始时间
     * @apiSuccess {Object} list.watchingRace 直播看球
     * @apiSuccess {Long} list.watchingRace.id 直播看球id
     * @apiSuccess {String} list.watchingRace.name 直播看球球馆名字
     * @apiSuccess {String} list.watchingRace.avater 直播看球球馆封面
     * @apiSuccess {Long} list.watchingRace.startTime 直播看球开始时间
     * @apiSuccess {Long} list.createDate 记录生成时间
     */
    @RequestMapping(value = "/watching")
    public void watching(HttpServletResponse response, Long userId) {

        if (null == userId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageWatching> list = messageService.watching(response, userId);

        Result obj = new Result(true).data(createMap("list", list));
        String result = JsonUtil.obj2ApiJson(obj, "toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/post 帖子消息
     * @apiName message.post
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传 />
     * @apiSuccess {Object}  list 帖子消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {String} list.title 消息标题
     * @apiSuccess {String} list.content 消息评论内容
     * @apiSuccess {Long} list.createDate 消息评论时间
     * @apiSuccess {Object} list.fUser 好友
     * @apiSuccess {Long} list.fUser.id 好友id
     * @apiSuccess {String} list.fUser.nickname 好友昵称
     * @apiSuccess {Object} list.post 帖子
     * @apiSuccess {Long} list.post.id 帖子id
     * @apiSuccess {String} list.post.content 帖子内容
     */
    @RequestMapping(value = "/post")
    public void post(HttpServletResponse response, Long userId) {

        if (null == userId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<PostComment> list = messageService.post(response, userId);

        Result obj = new Result(true).data(createMap("list", list));
        String result = JsonUtil.obj2ApiJson(obj, "tUser", "user", "postImages", "postCommentList", "postId");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/system 系统消息
     * @apiName message.system
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {Integer} list.type 消息类型（1：系统消息，2：添加好友消息，3：好友请求消息）
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Long} list.createDate 消息时间
     * @apiSuccess {Long} list.toUserId 好友id (添加好友)
     * @apiSuccess {String} list.nickname 好友昵称
     * @apiSuccess {Long} list.userId 好友id (好友请求)
     */
    @RequestMapping(value = "/system")
    public void system(HttpServletResponse response, Long userId) {

        if (null == userId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageVo> list = messageService.system(response, userId);

        Result obj = new Result(true).data(createMap("list", list));
        String result = JsonUtil.obj2ApiJson(obj, "toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/systemInfo 系统消息详情
     * @apiName message.systemInfo
     * @apiGroup message
     * @apiParam {Long} systemId 系统消息id <必传/>
     * @apiSuccess {Object}  message 系统消息列表
     * @apiSuccess {Long} message.id 消息id
     * @apiSuccess {String} message.title 消息标题
     * @apiSuccess {String} message.content 消息内容
     * @apiSuccess {Long} message.createDate 消息时间
     */
    @RequestMapping(value = "/systemInfo")
    public void systemInfo(HttpServletResponse response, Long systemId) {

        if (null == systemId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        SystemMessage message = systemMessageService.getById(systemId);

        Result obj = new Result(true).data(createMap("message", message));
        String result = JsonUtil.obj2ApiJson(obj, "toUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/message/team 球队消息
     * @apiName message.team
     * @apiGroup message
     * @apiParam {Long} userId 用户id <必传/>
     * @apiSuccess {Object}  list 消息列表
     * @apiSuccess {Long} list.id 消息id
     * @apiSuccess {Integer} list.type 消息类型（7：被邀请加入球队消息，8：邀请加入球队，9：申请加入球队, 10：约战成功,11：约战失败,12：申请约战, 13：申请加入球队回复）
     * @apiSuccess {String} list.content 消息内容
     * @apiSuccess {Long} list.createDate 消息时间
     * @apiSuccess {Long} list.toUserId 好友id (邀请加入球队)
     * @apiSuccess {String} list.nickname 好友昵称
     * @apiSuccess {Long} list.userId 好友id (被邀请加入球队、申请加入球队)
     * @apiSuccess {Long} list.userId 球队id
     * @apiSuccess {String} list.nickname 球队名称
     * @apiSuccess {String} list.teamAvater 球队队徽
     */
    @RequestMapping(value = "/team")
    public void team(HttpServletResponse response, Long userId) {

        if (null == userId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageVo> messageVoList = messageService.team(response, userId);

        Result obj = new Result(true).data(createMap("list", messageVoList));
        String result = JsonUtil.obj2ApiJson(obj, "leaderUser", "toUser");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/message/messageStatus 消息状态
     * @apiName message.messageStatus
     * @apiGroup message
     * @apiParam {Long} userId 消息id <必传 />
     * @apiSuccess {Integer} status 消息状态(0：有新消息，1：没有)
     */
    @RequestMapping(value = "/messageStatus")
    private void messageStatus(HttpServletResponse response, Long userId) {
        if (userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Integer status = 0;
        List<MessageRecord> messageRecordList = messageRecordService.findByUserId(userId);
        if (messageRecordList == null || messageRecordList.size() == 0) {
            status = 1;
        }
        Result obj = new Result(true).data(createMap("status", status));
        String result = JsonUtil.obj2ApiJson(obj, "leaderUser");
        WebUtil.printApi(response, result);
    }
}
