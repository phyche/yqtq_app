package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.TeamVo;
import com.sixmac.entity.vo.UserVo;
import com.sixmac.service.*;
import com.sixmac.utils.JsonUtil;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/5/17 0017.
 * 用户
 */
@Controller
@RequestMapping(value = "api/user")
public class UserApi extends CommonController {

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostImageService postImageService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostService postService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GirlUserService girlUserService;

    @Autowired
    private GirlCommentService girlCommentService;

    /**
     * 完成
     *
     *  @api {post} /api/user/info 用户个人资料
     * @apiName user.info
     * @apiGroup user
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object}  user 用户
     * @apiSuccess {String} user.nickname 用户昵称
     * @apiSuccess {String} user.avater 用户头像
     * @apiSuccess {Integer} user.vipNum 用户会员等级
     * @apiSuccess {Integer} user.credibility 用户信誉评分
     *
     * @apiSuccess {Object}  teamList 我加入的球队列表
     * @apiSuccess {Integer} teamList.id 球队id
     * @apiSuccess {String} teamList.name 球队名称
     * @apiSuccess {String} teamList.avater 球队队徽
     * @apiSuccess {Integer} teamList.count 球员总数
     * @apiSuccess {Integer} teamList.battleNum 球队应战数
     * @apiSuccess {Integer} teamList.declareNum 球队宣战数
     * @apiSuccess {Object} teamList.list 我加入的球队的球员列表
     * @apiSuccess {Integer} teamList.list.id 球员id
     * @apiSuccess {String} teamList.list.avater 球员头像
     *
     * @apiSuccess {Object}  myTeam 我的球队
     * @apiSuccess {Integer} myTeam.id 球队id
     * @apiSuccess {String} myTeam.name 球队名称
     * @apiSuccess {String} myTeam.avater 球队队徽
     * @apiSuccess {Integer} myTeam.count 球员总数
     * @apiSuccess {Integer} myTeam.battleNum 球队应战数
     * @apiSuccess {Integer} myTeam.declareNum 球队宣战数
     * @apiSuccess {Object} myTeam.list 我的球队的球员列表
     * @apiSuccess {Integer} myTeam.list.id 球员id
     * @apiSuccess {String} myTeam.list.avater 球员头像
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Integer userId) {
        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.getById(userId);

        //我加入的球队
        List<Team> teamList = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(userId);
        for (TeamMember teamMember : teamMemberList) {
            teamList.add(teamMember.getTeam());
        }

        map.put("teamList", teamList);

        //我的球队
        if (teamService.findListByLeaderId(userId) != null) {
            Team myTeam = teamService.findListByLeaderId(userId);
            map.put("myTeam", myTeam);
        }

        map.put("user", user);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj, "leaderUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/edit 编辑个人资料
     * @apiName user.edit
     * @apiGroup user
     * @apiParam {Integer} userId 用户id
     * @apiParam {String} avater 用户头像
     * @apiParam {String} nickname 用户昵称
     * @apiParam {Integer} gender 用户性别（0：男 1：女）
     * @apiParam {Long} birthday 用户出生日期
     * @apiParam {Integer} cityId 用户城市
     * @apiParam {Double} height 身高
     * @apiParam {Double} weight 体重
     * @apiParam {Integer} position 位置（0：前 1：中 2：后 3：守）
     *
     * @apiSuccess {Object}  teamList 我加入的球队列表
     *
     */
    @RequestMapping(value = "/edit")
    public void edit(HttpServletResponse response,
                     Integer userId,
                     String avater,
                     String nickname,
                     Integer gender,
                     Long birthday,
                     Integer cityId,
                     Double height,
                     Double weight,
                     Integer position) {

        User user = userService.getById(userId);

        int age = 0;

        Date now = new Date();

        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");

        String birth_year = format_y.format(birthday);
        String this_year = format_y.format(now);

        String birth_month = format_M.format(birthday);
        String this_month = format_M.format(now);

        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0) age -= 1;
        if (age < 0) age = 0;


        user.setAvater(avater);
        user.setNickname(nickname);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setAge(age);
        user.setCityId(cityId);
        user.setHeight(height);
        user.setWeight(weight);
        user.setPosition(position);

        userService.update(user);

        Result result = new Result(true).data(0);
        WebUtil.printApi(response, result);
    }

    /**
     * 会员操作
     */
    @RequestMapping(value = "/operation")
    public void operation(HttpServletResponse response, Integer userId) {


    }

    /**
     * 我的场地 与我的约球信息相同
     */

    /**
     * 我的赛事
     *
     * 我的约球与orderball里的球友约球相同
     * 我的队赛与orderball里的球友队赛相同
     */

    /**
     * 完成
     *
     * @api {post} /api/user/commentList 我的评论
     * @apiName user.commentList
     * @apiGroup user
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object} tUser 被评论用户
     * @apiSuccess {Integer} tUser.id 被评论用户id
     * @apiSuccess {String} tUser.nickname 被评论用户昵称
     * @apiSuccess {String} content 帖子内容
     * @apiSuccess {String} createDate 评论时间
     *
     *
     */
    @RequestMapping(value = "/commentList")
    public void commentList(HttpServletResponse response, Integer userId) {

        List<PostComment> postComments = postCommentService.findByFuserId(userId);

        Result obj = new Result(true).data(postComments);
        String result = JsonUtil.obj2ApiJson(obj,"fUser","user","mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "status", "gender", "birthday");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/postList 我的帖子
     * @apiName user.postList
     * @apiGroup user
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object} postList 帖子
     * @apiSuccess {Object} postList.user 用户
     * @apiSuccess {String} postList.user.avater 用户头像
     * @apiSuccess {String} postList.user.nickname 用户昵称
     * @apiSuccess {Object} postList.postImage 帖子图片列表
     * @apiSuccess {String} postList.postImage.avater 帖子图片
     * @apiSuccess {String} postList.content 帖子内容
     * @apiSuccess {Integer} postList.commentNum 帖子评论数
     * @apiSuccess {Long} postList.createDate 创建时间
     */
    @RequestMapping(value = "/postList")
    public void postList(HttpServletResponse response, Integer userId) {
        List<Post> postList = postService.findByUserId(userId);
        for (Post post : postList) {
            post.setCommentNum(postCommentService.findByPostId(post.getId()).size());
            post.setPostImages(postImageService.findByPostId(post.getId()));
        }

        Result obj = new Result(true).data(postList);
        String result = JsonUtil.obj2ApiJson(obj,"post");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/user/postInfo 我的帖子详情
     * @apiName user.postInfo
     * @apiGroup user
     * @apiParam {Integer} postId 帖子id
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
     *
     */
    @RequestMapping(value = "/postInfo")
    public void postInfo(HttpServletResponse response, Integer postId) {

        Map<String, Object> map = new HashMap<String, Object>();

        //我的帖子
        Post post = postService.getById(postId);
        //我的帖子图片列表
        List<PostImage> postImages = postImageService.findByPostId(postId);
        //我的帖子评论列表
        List<PostComment> postComments = postCommentService.findByPostId(postId);

        map.put("post",post);
        map.put("postImages", postImages);
        map.put("postComments", postComments);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"post","tUser","mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "status", "gender", "birthday");
        WebUtil.printApi(response, result);
    }

    /**
     *
     *
     *  @api {post} /api/user/watchingList 我的看球列表
     * @apiName user.watchingList
     * @apiGroup user
     * @apiParam {Integer} userId 用户id
     *
     * @apiSuccess {Object} girlUserList 用户约看列表
     * @apiSuccess {Integer} post.content 约看id
     * @apiSuccess {Object} girlUserList.girl 宝贝
     * @apiSuccess {Long} girlUserList.girl.avater 宝贝头像
     * @apiSuccess {Object} girlUserList.girl,nickname 宝贝昵称
     * @apiSuccess {Integer} girlUserList.girl.price 宝贝价格
     * @apiSuccess {String} girlUserList.girl.tip 宝贝红包（小费）
     * @apiSuccess {Object} girlUserList. 时长
     * @apiSuccess {String} postImages.avater 预约时间
     *
     * @apiSuccess {Object} postComments 赛事
     * @apiSuccess {Integer} postComments.content 赛事id
     * @apiSuccess {Long} postComments.createDate 球队1的名字
     * @apiSuccess {Object} postComments.fUser 球队2的名字
     *
     */
    @RequestMapping(value = "/watchingList")
    public void watchingList(HttpServletResponse response, Integer userId) {

        List<GirlUser> girlUserList = girlUserService.findByUserId(userId);

        Result obj = new Result(true).data(girlUserList);
        String result = JsonUtil.obj2ApiJson(obj,"user","stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 我的看球详情
     *
     */
    @RequestMapping(value = "/watchingInfo")
    public void watchingInfo(HttpServletResponse response, Integer girlUserId) {

        GirlUser girlUsers = girlUserService.getById(girlUserId);

        Result obj = new Result(true).data(girlUsers);
        String result = JsonUtil.obj2ApiJson(obj,"user","stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 我的看球 确认
     */
    @RequestMapping(value = "/confirm")
    public void confirm(HttpServletResponse response, Integer watchingId) {

        GirlUser girlUser = girlUserService.getById(watchingId);

        girlUser.setStatus(1);
        girlUserService.update(girlUser);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 我的看球 评价
     */
    @RequestMapping(value = "/comment")
    public void comment(HttpServletResponse response, Integer star, String comment, Integer watchingId) {

        GirlUser girlUser = girlUserService.getById(watchingId);

        if (girlUser.getStatus() == 1) {

            GirlComment girlComment = new GirlComment();
            girlComment.setStar(star);
            girlComment.setContent(comment);
            girlCommentService.create(girlComment);
        }

        WebUtil.printApi(response, new Result(true));
    }
}
