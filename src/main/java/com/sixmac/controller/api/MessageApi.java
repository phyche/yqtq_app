package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
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

    /**
     * 完成
     *
     * @api {post} /api/message/orderBall 好友约球消息
     * @apiName message.orderBall
     * @apiGroup message
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  messageOrderBallList 好友约球消息列表
     * @apiSuccess {Integer} messageOrderBallList.id 消息id
     * @apiSuccess {String} messageOrderBallList.content 消息内容
     * @apiSuccess {Object} messageOrderBallList.user 好友
     * @apiSuccess {Integer} messageOrderBallList.user.id 好友d
     * @apiSuccess {String} messageOrderBallList.user.nickname 好友昵称
     * @apiSuccess {Object} messageOrderBallList.reserve 约球
     * @apiSuccess {Integer} messageOrderBallList.reserve.id 约球d
     * @apiSuccess {Object} messageOrderBallList.reserve.stadium 约球球场
     * @apiSuccess {String} messageOrderBallList.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} messageOrderBallList.reserve.startTime 开始时间
     *
     * @apiSuccess {Long} messageOrderBallList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/orderBall")
    public void orderBall(HttpServletResponse response, Integer userId) {

        List<MessageOrderBall> messageOrderBallList = messageOrderBallService.findByToUserId(userId);
        for (MessageOrderBall messageOrderBall : messageOrderBallList) {

            messageOrderBall.setContent("您的好友" + messageOrderBall.getUser().getNickname() + "约您去踢球啦！");

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
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  userReserveList 加入约球消息列表
     * @apiSuccess {Integer} userReserveList.id 加入约球id
     * @apiSuccess {String} userReserveList.content 约球内容
     * @apiSuccess {Object} userReserveList.user 加入约球人
     * @apiSuccess {Integer} userReserveList.user.id 加入约球人id
     * @apiSuccess {String} userReserveList.user.nickname 加入约球人昵称
     * @apiSuccess {Object} userReserveList.reserve 加入约球预约
     * @apiSuccess {Integer} userReserveList.reserve.id 加入约球预约id
     * @apiSuccess {Object} userReserveList.reserve.stadium 约球球场
     * @apiSuccess {String} userReserveList.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} userReserveList.reserve.startTime 开始时间
     *
     * @apiSuccess {Long} userReserveList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/addOrder")
    public void addOrder(HttpServletResponse response, Integer userId) {

        List<Reserve> reserveList = reserveService.findByUserId(userId);
        List<UserReserve> userReserveList = new ArrayList<UserReserve>();
        for (Reserve reserve : reserveList) {
            for (UserReserve userReserve : userReserveService.findByReserverId(reserve.getId())){

                userReserve.setContent(userReserve.getUser().getNickname() + "加入了您的约球");
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
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  userReserveList 约球列表
     * @apiSuccess {Integer} userReserveList.id 约球id
     * @apiSuccess {String} userReserveList.content 约球内容
     * @apiSuccess {Integer} userReserveList.status 约球状态（1:组队成功2:组队失败）
     * @apiSuccess {Object} userReserveList.reserve 约球预约
     * @apiSuccess {Integer} userReserveList.reserve.id 约球预约id
     * @apiSuccess {Object} userReserveList.reserve.stadium 约球球场
     * @apiSuccess {String} userReserveList.reserve.stadium.name 约球球场名字
     * @apiSuccess {Long} userReserveList.reserve.startTime 开始时间
     *
     * @apiSuccess {Long} userReserveList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/myOrder")
    public void myOrder(HttpServletResponse response, Integer userId) {

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
     *
     *
     * @api {post} /api/message/doOrder 处理好友约球消息
     * @apiName message.doOrder
     * @apiGroup message
     * @apiParam {Integer} messageOrderBallId 约球消息id <必传 />
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doOrder")
    public void doOrder(HttpServletResponse response, Integer messageOrderBallId, Integer status) {

        MessageOrderBall messageOrderBall = messageOrderBallService.getById(messageOrderBallId);
        messageOrderBall.setStatus(status);
        messageOrderBallService.update(messageOrderBall);

        WebUtil.printApi(response,new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/message/watching 约看消息
     * @apiName message.watching
     * @apiGroup message
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  messageWatchingList 约看列表
     * @apiSuccess {Integer} messageWatchingList.id 约看id
     * @apiSuccess {Object} messageWatchingList.user 好友
     * @apiSuccess {Integer} messageWatchingList.user.id 好友id
     * @apiSuccess {String} messageWatchingList.user.nickname 好友昵称
     * @apiSuccess {Object} messageWatchingList.bigRace 现场看球
     * @apiSuccess {Integer} messageWatchingList.bigRace.id 现场看球id
     * @apiSuccess {String} messageWatchingList.bigRace.stadium.name 现场看球球场名字
     * @apiSuccess {Long} messageWatchingList.bigRace.startTime 现场看球开始时间
     *
     * @apiSuccess {Object} messageWatchingList.watchingRace 直播看球
     * @apiSuccess {Integer} messageWatchingList.watchingRace.id 直播看球id
     * @apiSuccess {String} messageWatchingList.watchingRace.name 直播看球球馆名字
     * @apiSuccess {String} messageWatchingList.watchingRace.avater 直播看球球馆封面
     * @apiSuccess {Long} messageWatchingList.watchingRace.startTime 直播看球开始时间
     *
     * @apiSuccess {Long} messageWatchingList.createDate 记录生成时间
     *
     */
    @RequestMapping(value = "/watching")
    public void watching(HttpServletResponse response, Integer userId) {

        List<MessageWatching> messageWatchingList = messageWatchingService.findByToUserId(userId);
        for (MessageWatching messageWatching : messageWatchingList) {

            messageWatching.setContent(messageWatching.getUser().getNickname() + "约您看球");
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
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  postCommentList 帖子消息列表
     * @apiSuccess {Integer} postCommentList.id 消息id
     * @apiSuccess {String} postCommentList.title 消息标题
     * @apiSuccess {Object} postCommentList.fUser 好友
     * @apiSuccess {Integer} postCommentList.fUser.id 好友id
     * @apiSuccess {String} postCommentList.fUser.nickname 好友昵称
     * @apiSuccess {Object} postCommentList.post 帖子
     * @apiSuccess {Integer} postCommentList.post.id 帖子id
     * @apiSuccess {String} postCommentList.post.content 帖子内容
     * @apiSuccess {Long} postCommentList.createDate 评论时间
     *
     *
     */
    @RequestMapping(value = "/post")
    public void post(HttpServletResponse response, Integer userId) {

        List<PostComment> postCommentList = postCommentService.findByToUserId(userId);
        for (PostComment postComment : postCommentList) {
            postComment.setTitle(postComment.getfUser().getNickname() + "评论了您的" + postComment.getPost().getContent());
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
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object}  messageList 系统消息列表
     * @apiSuccess {Integer} messageList.id 消息id
     * @apiSuccess {String} messageList.title 消息标题
     * @apiSuccess {Long} messageList.createDate 消息时间
     *
     *
     */
    @RequestMapping(value = "/system")
    public void system(HttpServletResponse response, Integer userId) {

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
     * @apiParam {Integer} systemId 系统消息id
     *
     * @apiSuccess {Object}  message 系统消息列表
     * @apiSuccess {Integer} message.id 消息id
     * @apiSuccess {String} message.title 消息标题
     * @apiSuccess {String} message.content 消息内容
     * @apiSuccess {Long} message.createDate 消息时间
     *
     *
     */
    @RequestMapping(value = "/systemInfo")
    public void systemInfo(HttpServletResponse response, Integer systemId) {

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
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object}  messageAddList 消息列表
     * @apiSuccess {Integer} messageAddList.id 消息id
     * @apiSuccess {String} messageAddList.content 消息内容
     * @apiSuccess {Object} messageAddList.toUser 好友
     * @apiSuccess {Integer} messageAddList.toUser.id 好友id
     * @apiSuccess {String} messageAddList.toUser.nickname 好友昵称
     * @apiSuccess {Long} messageAddList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/toAdd")
    public void toAdd(HttpServletResponse response, Integer userId) {

        List<MessageAdd> messageAddList = messageAddService.findByUserId(userId);

        for (MessageAdd messageAdd : messageAddList) {

            if (messageAdd.getStatus() == 1) {
                messageAdd.setContent(messageAdd.getToUser().getNickname() + "同意了您的好友请求");
            }
            if (messageAdd.getStatus() == 2) {
                messageAdd.setContent(messageAdd.getToUser().getNickname() + "拒绝了您的好友请求");
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
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object}  messageAddList 消息列表
     * @apiSuccess {Integer} messageAddList.id 消息id
     * @apiSuccess {String} messageAddList.content 消息内容
     * @apiSuccess {Object} messageAddList.user 好友
     * @apiSuccess {Integer} messageAddList.user.id 好友id
     * @apiSuccess {String} messageAddList.user.nickname 好友昵称
     * @apiSuccess {Long} messageAddList.createDate 消息时间
     *
     */
    @RequestMapping(value = "/beAdd")
    public void beAdd(HttpServletResponse response, Integer userId) {

        List<MessageAdd> messageAddList = messageAddService.findByToUserId(userId);

        for (MessageAdd messageAdd : messageAddList) {

            messageAdd.setContent(messageAdd.getUser().getNickname() + "添加您为好友");

        }

        Result obj = new Result(true).data(messageAddList);
        String result = JsonUtil.obj2ApiJson(obj,"toUser");
        WebUtil.printApi(response, result);
    }

    /**
     *
     *
     * @api {post} /api/message/doAdd 处理好友添加消息
     * @apiName message.doAdd
     * @apiGroup message
     * @apiParam {Integer} messageAddId 添加消息id <必传 />
     * @apiParam {Integer} status 状态（1：同意，2：拒绝） <必传 />
     *
     */
    @RequestMapping(value = "/doAdd")
    public void doAdd(HttpServletResponse response, Integer messageAddId, Integer status) {

        MessageAdd messageAdd = messageAddService.getById(messageAddId);
        messageAdd.setStatus(status);
        messageAddService.update(messageAdd);

        WebUtil.printApi(response,new Result(true).data(0));
    }
}
