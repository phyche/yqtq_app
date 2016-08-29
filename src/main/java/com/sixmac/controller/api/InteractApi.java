package com.sixmac.controller.api;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.controller.editor.CustomStringEditor;
import com.sixmac.core.Constant;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.*;

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

    @Autowired
    private MessageRecordService messageRecordService;

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
     *
     * @apiSuccess {Integer} list.shareNum 分享数
     * @apiSuccess {Integer} list.commentNum 评论数
     *
     * @apiSuccess {Object} list.user 发足球圈用户
     * @apiSuccess {Long} list.user.id 用户id
     * @apiSuccess {String} list.user.nickname 用户昵称
     * @apiSuccess {String} list.user.avater 用户头像
     *
     * @apiSuccess {Object} list.postImages 足球圈图片列表
     * @apiSuccess {String} list.postImages.avater 足球圈图片
     *
     * @apiSuccess {Object} list.postCommentList 足球圈评论列表
     * @apiSuccess {Object} list.postCommentList.fUser 评论人
     * @apiSuccess {Long} list.postCommentList.fUser.id 评论人id
     * @apiSuccess {String} list.postCommentList.fUser.nickname 评论人昵称
     * @apiSuccess {String} list.postCommentList.fUser.avater 评论人头像
     * @apiSuccess {Object} list.postCommentList.tUser 被评论人
     * @apiSuccess {Long} list.postCommentList.tUser.id 被评论人id
     * @apiSuccess {String} list.postCommentList.tUser.nickname 被评论人昵称
     * @apiSuccess {String} list.postCommentList.tUser.avater 被评论人头像
     * @apiSuccess {String} list.postCommentList.content 评论内容
     * @apiSuccess {Long} list.postCommentList.createDate 评论时间
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

            if (StringUtils.isNotBlank(post.getUser().getAvater())) {
                post.getUser().setAvater(ConfigUtil.getString("upload.url") + post.getUser().getAvater());
            }
            for (PostImage postImage : post.getPostImages()) {
                if (StringUtils.isNotBlank(postImage.getAvater())) {
                    postImage.setAvater(ConfigUtil.getString("upload.url") + postImage.getAvater());
                }
            }
            for (PostComment postComment : post.getPostCommentList()) {
                if (StringUtils.isNotBlank(postComment.getfUser().getAvater())) {
                    postComment.getfUser().setAvater(ConfigUtil.getString("upload.url") + postComment.getfUser().getAvater());
                }
                if (StringUtils.isNotBlank(postComment.gettUser().getAvater())) {
                    postComment.gettUser().setAvater(ConfigUtil.getString("upload.url") + postComment.gettUser().getAvater());
                }
            }
            post.setCommentNum(post.getPostCommentList().size());
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);

        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj,"mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "gender", "birthday");
        WebUtil.printApi(response, result);

    }

    /**
     *
     * @api {post} /api/interact/commentList 评论列表
     * @apiName interact.commentList
     * @apiGroup interact
     * @apiParam {Long} postId 足球圈id <必传 />
     *
     * @apiSuccess {Object} list 足球圈评论列表
     * @apiSuccess {Object} list.fUser 评论人
     * @apiSuccess {Long} list.fUser.id 评论人id
     * @apiSuccess {String} list.fUser.nickname 评论人昵称
     * @apiSuccess {String} list.fUser.avater 评论人头像
     * @apiSuccess {Object} list.tUser 评论人
     * @apiSuccess {Long} list.tUser.id 被评论人id
     * @apiSuccess {String} list.tUser.nickname 被评论人昵称
     * @apiSuccess {String} list.tUser.avater 被评论人头像
     * @apiSuccess {String} list.content 评论内容
     * @apiSuccess {Long} list.createDate 评论时间
     */
    @RequestMapping(value = "/commentList")
    public void commentList(HttpServletResponse response,
                            Long postId) {
        List<PostComment> list = postCommentService.findByPostId(postId);
        for (PostComment postComment : list) {
            if (StringUtils.isNotBlank(postComment.getfUser().getAvater())) {
                postComment.getfUser().setAvater(ConfigUtil.getString("upload.url") + postComment.getfUser().getAvater());
            }
            if (StringUtils.isNotBlank(postComment.gettUser().getAvater())) {
                postComment.gettUser().setAvater(ConfigUtil.getString("upload.url") + postComment.gettUser().getAvater());
            }
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     *
     * @api {post} /api/interact/publish 发布圈子
     * @apiName interact.publish
     * @apiGroup interact
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {String} content 内容 <必传 />
     * @apiParam {Object} imagesMap 图片数组Map
     *
     *
     */
    @RequestMapping(value = "/publish")
    public void publish(HttpServletRequest request ,
                        HttpServletResponse response,
                        Long userId,
                        String content) {

        if (null == userId || content == null || content == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        postService.publish(request, response, userId, content);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * (完成)
     *
     * @api {post} /api/interact/comment 评论回复
     * @apiName interact.comment
     * @apiGroup interact
     * @apiParam {Long} postId 足球圈id <必传 />
     * @apiParam {Long} userId 评论人id <必传 />
     * @apiParam {Long} touserId 被评论人id <必传 />
     * @apiParam {String} content 内容
     */
    @RequestMapping(value = "/comment")
    public void comment(HttpServletResponse response,
                        Long postId,
                        Long userId,
                        Long touserId,
                        String content) {

        if (null == userId || postId == null || touserId == null|| content == null|| content == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        postCommentService.comment(response, postId, userId, touserId, content);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/addressBook 通讯录
     * @apiName interact.addressBook
     * @apiGroup interact
     * @apiParam {Long} userId 用户id <必传/>
     *
     * @apiSuccess {Object}  list 通讯录列表
     * @apiSuccess {Integer} list.id 通讯录id
     * @apiSuccess {Object} list.toUser 通讯录好友
     * @apiSuccess {Long} list.toUser.id 好友id
     * @apiSuccess {String} list.toUser.nickname 好友昵称
     * @apiSuccess {String} list.toUser.avater 好友头像
     *
     */
    @RequestMapping(value = "/addressBook")
    public void addressBook(HttpServletResponse response, Long userId) {

        if (null == userId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<MessageAdd> list = messageAddService.findUserId(userId);

        for (MessageAdd messageAdd : list) {
            if (StringUtils.isNotBlank(messageAdd.getToUser().getAvater())) {
                messageAdd.getToUser().setAvater(ConfigUtil.getString("upload.url") + messageAdd.getToUser().getAvater());
            }
        }

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"user","content");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/interact/addFriend 添加好友
     * @apiName interact.addFriend
     * @apiGroup interact
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {String} mobile 好友手机号（账号） <必传/>
     *
     */
    @RequestMapping(value = "/addFriend")
    public void addFriend(HttpServletResponse response, Long userId, String mobile) {

        if (null == userId || mobile == null || mobile == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        userService.addFriend(response, userId, mobile);
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
     * @apiSuccess {Long} list.Information.id 资讯id
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

        for (Information information : page.getContent()) {
            if (StringUtils.isNotBlank(information.getAvater())) {
                information.setAvater(ConfigUtil.getString("upload.url") + information.getAvater());
            }
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(dataMap);
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

        for (Activity activity : page.getContent()) {
            if (StringUtils.isNotBlank(activity.getAvater())) {
                activity.setAvater(ConfigUtil.getString("upload.url") + activity.getAvater());
            }
        }
        Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
