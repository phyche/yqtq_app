package com.sixmac.controller.api;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.APIFactory;
import com.sixmac.utils.JsonUtil;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Controller
@RequestMapping(value = "api/interact")
public class InteractApi extends CommonController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostImageService postImageService;

    @Autowired
    private MessageAddService messageAddService;

    /**
     * 完成
     *
     * @api {post} /api/interact/listPost 足球圈列表
     * @apiName interact.listPost
     * @apiGroup interact
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 足球圈列表
     * @apiSuccess {String} list.content 足球圈内容
     * @apiSuccess {Object} list.user 发足球圈用户
     * @apiSuccess {Integer} list.user.id 用户id
     * @apiSuccess {String} list.user.nickname 用户昵称
     * @apiSuccess {String} list.user.avater 用户头像
     *
     * @apiSuccess {Object} list.postImages 足球圈图片列表
     * @apiSuccess {String} list.postImages.avater 足球圈图片
     *
     * @apiSuccess {Object} list.postCommentList 足球圈评论列表
     * @apiSuccess {Object} list.postCommentList.fUser 评论人
     * @apiSuccess {Integer} list.postCommentList.fUser.id 评论人id
     * @apiSuccess {String} list.postCommentList.fUser.nickname 评论人昵称
     * @apiSuccess {String} list.postCommentList.content 评论内容
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/listPost")
    public void listPost(HttpServletResponse response,
                         Integer pageNum,
                         Integer pageSize){

        Page<Post> page = postService.page(pageNum, pageSize);
        List<Post> postList = page.getContent();
        for (Post post : postList) {
            post.setPostImages(postImageService.findByPostId(post.getId()));
            post.setPostCommentList(postCommentService.findByPostId(post.getId()));
            for (int i = 0; i<2; i++) {
                post.setPostCommentList(post.getPostCommentList());
            }
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "status", "gender", "birthday");
        WebUtil.printApi(response, result);

    }

    /**
     * 图片保存有问题
     *
     * @api {post} /api/interact/publish 发布圈子
     * @apiName interact.publish
     * @apiGroup interact
     * @apiParam {Integer} userId 用户id <必传 />
     * @apiParam {String} content 内容
     * @apiParam {String} image 图片
     *
     *
     */
    @RequestMapping(value = "/publish")
    public void publish(HttpServletResponse response,
                        Integer userId,
                        String content,
                        String image) {

        User user = userService.getById(userId);
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setStatus(0);
        postService.create(post);

        PostImage postImage = new PostImage();
        postImage.setPost(post);
        postImage.setAvater(image);
        postImage.setStatus(0);
        postImageService.create(postImage);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/postInfo 足球圈详情
     * @apiName interact.postInfo
     * @apiGroup interact
     * @apiParam {Integer} postId 足球圈id <必传 />
     *
     * @apiSuccess {Object} post 帖子
     * @apiSuccess {Integer} post.content 帖子内容
     * @apiSuccess {Long} post.createDate 帖子创建时间
     *
     * @apiSuccess {Object} post.user 用户列表
     * @apiSuccess {Integer} post.user.id 用户id
     * @apiSuccess {String} post.user.nickname 用户昵称
     *
     * @apiSuccess {Object} postImages 帖子图片列表
     * @apiSuccess {String} postImages.avater 帖子图片
     *
     * @apiSuccess {Object} postComments 帖子评论列表
     * @apiSuccess {Integer} postComments.content 帖子内容
     * @apiSuccess {Long} postComments.createDate 帖子创建时间
     * @apiSuccess {Object} postComments.fUser 帖子评论人
     * @apiSuccess {Integer} postComments.fUser.id 帖子评论人id
     * @apiSuccess {String} postComments.fUser.avater 帖子评论人头像
     * @apiSuccess {String} postComments.fUser.nickname 帖子评论人昵称
     */
    @RequestMapping(value = "/postInfo")
    public void postInfo(HttpServletResponse response, Integer postId) {

        Map<String,Object> map = new HashMap<String,Object>();

        Post post = postService.getById(postId);
        List<PostComment> postComments = postCommentService.findByPostId(postId);
        List<PostImage> postImages = postImageService.findByPostId(postId);

        map.put("post",post);
        map.put("postComments",postComments);
        map.put("postImages",postImages);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * (完成)
     *
     * @api {post} /api/interact/comment 评论回复
     * @apiName interact.comment
     * @apiGroup interact
     * @apiParam {Integer} postId 足球圈id <必传 />
     * @apiParam {Integer} userId 评论人id <必传 />
     * @apiParam {Integer} touserId 被评论人id <必传 />
     * @apiParam {String} content 内容
     */
    @RequestMapping(value = "/comment")
    public void comment(HttpServletResponse response,
                        Integer postId,
                        Integer userId,
                        Integer touserId,
                        String content) {

        PostComment postComment = new PostComment();
        postComment.setPost(postService.getById(postId));
        postComment.setfUser(userService.getById(userId));
        postComment.settUser(userService.getById(touserId));
        postComment.setContent(content);
        postCommentService.create(postComment);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/addressBook 通讯录
     * @apiName interact.addressBook
     * @apiGroup interact
     * @apiParam {Integer} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  messageAddList 通讯录列表
     * @apiSuccess {Integer} messageAddList.id 通讯录id
     * @apiSuccess {Object} messageAddList.toUser 通讯录好友
     * @apiSuccess {Integer} messageAddList.toUser.id 好友id
     * @apiSuccess {String} messageAddList.toUser.nickname 好友昵称
     * @apiSuccess {String} messageAddList.toUser.avater 好友头像
     *
     */
    @RequestMapping(value = "/addressBook")
    public void addressBook(HttpServletResponse response, Integer userId) {

        List<MessageAdd> messageAddList = messageAddService.findUserId(userId);

        Result obj = new Result(true).data(messageAddList);
        String result = JsonUtil.obj2ApiJson(obj,"user","content","status");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/addFriend 添加好友
     * @apiName interact.addFriend
     * @apiGroup interact
     * @apiParam {Integer} userId 用户id <必传/>
     * @apiParam {String} mobile 好友手机号（账号） <必传/>
     *
     */
    @RequestMapping(value = "/addFriend")
    public void addFriend(HttpServletResponse response, Integer userId, String mobile) {

        User user = userService.findByMobile(mobile);

        MessageAdd messageAdd = new MessageAdd();
        messageAdd.setStatus(0);
        messageAdd.setUser(userService.getById(userId));
        messageAdd.setToUser(user);

        messageAddService.create(messageAdd);
        WebUtil.printApi(response, new Result(true).data(0));

    }

    /**
     * 完成
     *
     * @api {post} /api/interact/listInformation 资讯列表
     * @apiName interact.listInformation
     * @apiGroup interact
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 资讯列表
     * @apiSuccess {Integer} list.id 资讯id
     * @apiSuccess {String} list.title 资讯标题
     * @apiSuccess {String} list.avater 资讯封面
     * @apiSuccess {String} list.introduction 资讯简介
     * @apiSuccess {String} list.description 资讯介绍
     * @apiSuccess {Long} list.createDate 资讯创建时间
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/listInformation")
    public void listInformation(HttpServletResponse response,
                                Integer pageNum,
                                Integer pageSize) {

        Page<Information> page = informationService.page(pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/messageInfo 资讯详情
     * @apiName interact.messageInfo
     * @apiGroup interact
     * @apiParam {Integer} messageId 资讯id <必传 />
     *
     * @apiSuccess {Object}  information 资讯列表
     * @apiSuccess {Integer} information.id 资讯id
     * @apiSuccess {String} information.title 资讯标题
     * @apiSuccess {String} information.avater 资讯封面
     * @apiSuccess {String} information.introduction 资讯简介
     * @apiSuccess {String} information.description 资讯介绍
     * @apiSuccess {Long} information.createDate 资讯创建时间
     */
    @RequestMapping(value = "/messageInfo")
    public void messageInfo(HttpServletResponse response, Integer messageId) {

        Information information = informationService.getById(messageId);

        Result obj = new Result(true).data(information);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/listActivity 活动列表
     * @apiName interact.listActivity
     * @apiGroup interact
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 活动列表
     * @apiSuccess {Integer} list.id 活动id
     * @apiSuccess {String} list.title 活动标题
     * @apiSuccess {String} list.avater 活动封面
     * @apiSuccess {String} list.introduction 活动简介
     * @apiSuccess {String} list.description 活动介绍
     * @apiSuccess {Long} list.createDate 活动创建时间
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/listActivity")
    public void listActivity(HttpServletResponse response,
                             Integer pageNum,
                             Integer pageSize) {

        Page<Activity> page = activityService.page(pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/activityInfo 活动详情
     * @apiName interact.activityInfo
     * @apiGroup interact
     * @apiParam {Integer} activityId 活动id <必传 />
     *
     * @apiSuccess {Object}  activity 活动列表
     * @apiSuccess {Integer} activity.id 活动id
     * @apiSuccess {String} activity.title 活动标题
     * @apiSuccess {String} activity.avater 活动封面
     * @apiSuccess {String} activity.introduction 活动简介
     * @apiSuccess {String} activity.description 活动介绍
     * @apiSuccess {Long} activity.createDate 活动创建时间
     */
    @RequestMapping(value = "/activityInfo")
    public void activityInfo(HttpServletResponse response, Integer activityId) {

        Activity activity = activityService.getById(activityId);

        Result obj = new Result(true).data(createMap("activity",activity));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

}
