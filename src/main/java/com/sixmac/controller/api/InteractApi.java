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

    /**
     * 足球圈列表(完成)
     */
    @RequestMapping(value = "/listPost")
    public void listPost(HttpServletResponse response,
                         Integer pageNum,
                         Integer pageSize){

        Page<Post> page = postService.page(pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "status", "gender", "birthday");
        WebUtil.printApi(response, result);

    }

    /**
     * 发布圈子
     */
    @RequestMapping(value = "/publish")
    public void publish(HttpServletResponse response,
                        Integer userId,
                        String content,
                        String img) {

        User user = userService.getById(userId);
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setStatus(0);
        //帖子图片

        postService.create(post);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 足球圈详情（完成）
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
     * 评论回复(完成)
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

        Result obj = new Result(true).data(postComment);
        String result = JsonUtil.obj2ApiJson(obj,"user");
        WebUtil.printApi(response, result);
    }

    /**
     * 资讯列表(完成)
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
     * 资讯详情（完成）
     */
    @RequestMapping(value = "/messageInfo")
    public void messageInfo(HttpServletResponse response, Integer messageId) {

        Information information = informationService.getById(messageId);

        Result obj = new Result(true).data(information);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 活动列表（完成）
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
     * 活动详情（完成）
     */
    @RequestMapping(value = "/activityInfo")
    public void activityInfo(HttpServletResponse response, Integer activityId) {

        Activity activity = activityService.getById(activityId);

        Result obj = new Result(true).data(createMap("activity",activity));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

}
