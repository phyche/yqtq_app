package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.TeamVo;
import com.sixmac.entity.vo.UserVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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

    @Autowired
    private UserVipService userVipService;

    @Autowired
    private CredibilityMessageService credibilityMessageService;

    @Autowired
    private VipMessageService vipMessageService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private VipLevelMessageService vipLevelMessageService;

    /**
     * 完成
     *
     *  @api {post} /api/user/homePage 用户个人首页
     * @apiName user.homePage
     * @apiGroup user
     * @apiParam {Integer} userId 用户id <必传 />
     *
     * @apiSuccess {Object}  user 用户
     * @apiSuccess {String} user.nickname 用户昵称
     * @apiSuccess {String} user.avater 用户头像
     * @apiSuccess {Integer} user.vipNum 用户会员等级
     * @apiSuccess {Integer} user.credibility 用户信誉评分
     *
     * @apiSuccess {Object}  teamList 我加入的球队列表
     * @apiSuccess {Long} teamList.id 球队id
     * @apiSuccess {String} teamList.name 球队名称
     * @apiSuccess {String} teamList.avater 球队队徽
     * @apiSuccess {Integer} teamList.count 球员总数
     * @apiSuccess {Integer} teamList.battleNum 球队应战数
     * @apiSuccess {Integer} teamList.declareNum 球队宣战数
     * @apiSuccess {Object} teamList.list 我加入的球队的球员列表
     * @apiSuccess {Long} teamList.list.id 球员id
     * @apiSuccess {String} teamList.list.avater 球员头像
     *
     * @apiSuccess {Object}  myTeam 我的球队
     * @apiSuccess {Long} myTeam.id 球队id
     * @apiSuccess {String} myTeam.name 球队名称
     * @apiSuccess {String} myTeam.avater 球队队徽
     * @apiSuccess {Integer} myTeam.count 球员总数
     * @apiSuccess {Integer} myTeam.battleNum 球队应战数
     * @apiSuccess {Integer} myTeam.declareNum 球队宣战数
     * @apiSuccess {Object} myTeam.list 我的球队的球员列表
     * @apiSuccess {Long} myTeam.list.id 球员id
     * @apiSuccess {String} myTeam.list.avater 球员头像
     */
    @RequestMapping(value = "/homePage")
    public void homePage(HttpServletResponse response, Long userId) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

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
     * @api {post} /api/user/credibilityMessage 信誉评分
     * @apiName user.credibilityMessage
     * @apiGroup user
     *
     * @apiSuccess {String} content 信誉评分内容
     *
     */
    @RequestMapping(value = "/credibilityMessage")
    public void credibilityMessage(HttpServletResponse response) {

        CredibilityMessage credibilityMessage = credibilityMessageService.getById(1l);

        String description = credibilityMessage.getContent();
        String others = "<html><head><style type='text/css'>body{overflow-x:hidden;margin:0;padding:0;background:#fff;color:#000;font-size:18px;font-family:Arial,'microsoft yahei',Verdana}body,div,fieldset,form,h1,h2,h3,h4,h5,h6,html,p,span{-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:normal}applet,dd,div,dl,dt,h1,h2,h3,h4,h5,h6,html,iframe,img,object,p,span{margin:0;padding:0;border:0}img{margin:0;padding:0;border:0;vertical-align:top}li,ul{margin:0;padding:0;list-style:none outside none}input[type=text],select{margin:0;padding:0;border:0;background:0;text-indent:3px;font-size:14px;font-family:Arial,'microsoft yahei',Verdana;-webkit-appearance:none;-moz-appearance:none}.wrapper{box-sizing:border-box;padding:10px;width:100%}p{color:#666;line-height:1.6em}.wrapper img{width:auto!important;height:auto!important;max-width:100%}p,span,p span{font-size:18px!important}</head></style>";
        description = description.format("<body><div class='wrapper'>%s</div></body></html>", description);
        description = others + description;

        Result obj = new Result(true).data(createMap("content", description));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/vipMessage 会员优惠说明
     * @apiName user.vipMessage
     * @apiGroup user
     *
     * @apiSuccess {String} content 会员优惠说明内容
     *
     */
    @RequestMapping(value = "/vipMessage")
    public void vipMessage(HttpServletResponse response) {

        VipMessage vipMessage = vipMessageService.getById(1l);

        String description = vipMessage.getContent();
        String others = "<html><head><style type='text/css'>body{overflow-x:hidden;margin:0;padding:0;background:#fff;color:#000;font-size:18px;font-family:Arial,'microsoft yahei',Verdana}body,div,fieldset,form,h1,h2,h3,h4,h5,h6,html,p,span{-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:normal}applet,dd,div,dl,dt,h1,h2,h3,h4,h5,h6,html,iframe,img,object,p,span{margin:0;padding:0;border:0}img{margin:0;padding:0;border:0;vertical-align:top}li,ul{margin:0;padding:0;list-style:none outside none}input[type=text],select{margin:0;padding:0;border:0;background:0;text-indent:3px;font-size:14px;font-family:Arial,'microsoft yahei',Verdana;-webkit-appearance:none;-moz-appearance:none}.wrapper{box-sizing:border-box;padding:10px;width:100%}p{color:#666;line-height:1.6em}.wrapper img{width:auto!important;height:auto!important;max-width:100%}p,span,p span{font-size:18px!important}</head></style>";
        description = description.format("<body><div class='wrapper'>%s</div></body></html>", description);
        description = others + description;

        Result obj = new Result(true).data(createMap("content", description));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/vipLevelMessage 会员等级说明
     * @apiName user.vipLevelMessage
     * @apiGroup user
     *
     * @apiSuccess {String} content 会员等级说明内容
     *
     */
    @RequestMapping(value = "/vipLevelMessage")
    public void vipLevelMessage(HttpServletResponse response) {

        VipLevelMessage vipLevelMessage = vipLevelMessageService.getById(1l);

        String description = vipLevelMessage.getContent();
        String others = "<html><head><style type='text/css'>body{overflow-x:hidden;margin:0;padding:0;background:#fff;color:#000;font-size:18px;font-family:Arial,'microsoft yahei',Verdana}body,div,fieldset,form,h1,h2,h3,h4,h5,h6,html,p,span{-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:normal}applet,dd,div,dl,dt,h1,h2,h3,h4,h5,h6,html,iframe,img,object,p,span{margin:0;padding:0;border:0}img{margin:0;padding:0;border:0;vertical-align:top}li,ul{margin:0;padding:0;list-style:none outside none}input[type=text],select{margin:0;padding:0;border:0;background:0;text-indent:3px;font-size:14px;font-family:Arial,'microsoft yahei',Verdana;-webkit-appearance:none;-moz-appearance:none}.wrapper{box-sizing:border-box;padding:10px;width:100%}p{color:#666;line-height:1.6em}.wrapper img{width:auto!important;height:auto!important;max-width:100%}p,span,p span{font-size:18px!important}</head></style>";
        description = description.format("<body><div class='wrapper'>%s</div></body></html>", description);
        description = others + description;

        Result obj = new Result(true).data(createMap("content", description));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/info 用户个人资料
     * @apiName user.info
     * @apiGroup user
     *
     * @apiSuccess {Object} user 用户
     * @apiSuccess {Long} user.id 用户id
     * @apiSuccess {String} user.avater 用户头像
     * @apiSuccess {String} user.nickname 用户昵称
     * @apiSuccess {Integer} user.gender 用户性别（0：男 1：女）
     * @apiSuccess {Long} user.birthday 用户出生日期
     * @apiSuccess {Integer} user.cityId 用户城市
     * @apiSuccess {Double} user.height 身高
     * @apiSuccess {Double} user.weight 体重
     * @apiSuccess {Integer} user.position 位置（0：前 1：中 2：后 3：守）
     *
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Long userId) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        User user = userService.getById(userId);

        Result obj = new Result(true).data(user);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/edit 编辑个人资料
     * @apiName user.edit
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {String} avater 用户头像
     * @apiParam {String} nickname 用户昵称
     * @apiParam {Integer} gender 用户性别（0：男 1：女）
     * @apiParam {Long} birthday 用户出生日期
     * @apiParam {Integer} provinceId 用户省份
     * @apiParam {Integer} cityId 用户城市
     * @apiParam {Double} height 身高
     * @apiParam {Double} weight 体重
     * @apiParam {Integer} position 位置（0：前 1：中 2：后 3：守）
     *
     */
    @RequestMapping(value = "/edit")
    public void edit(HttpServletResponse response,
                     Long userId,
                     String nickname,
                     Integer gender,
                     Long birthday,
                     Long provinceId,
                     Long cityId,
                     Double height,
                     Double weight,
                     Integer position,
                     MultipartRequest multipartRequest) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

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

        MultipartFile multipartFile = multipartRequest.getFile("mainImage");
        if (null != multipartFile) {
            String url = QiNiuUploadImgUtil.upload(multipartFile);
            //magazine.setCover(url);
            user.setAvater(url);
        }
        user.setNickname(nickname);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setAge(age);
        user.setProvinceId(provinceId);
        user.setCityId(cityId);
        user.setHeight(height);
        user.setWeight(weight);
        user.setPosition(position);

        userService.update(user);

        Result obj = new Result(true).data(user);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 计算几年后时间戳有问题
     *
     * @api {post} /api/user/operation 会员操作
     * @apiName user.operation
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {Integer} num 会员时长（1：一年 2：两年 3：三年 默认为1）
     *
     * @apiSuccess {String} status 会员状态
     * @apiSuccess {Integer} level 会员等级
     * @apiSuccess {String} endDate 会员时间
     * @apiSuccess {Double} price 价格
     *
     */
    @RequestMapping(value = "/operation")
    public void operation(HttpServletResponse response, Long userId, Integer num) throws ParseException {

        Map<String, Object> map = new HashMap<String, Object>();
        Integer level = 0;
        String endDate = null;
        String status = null;
        UserVip userVip = userVipService.findByUserId(userId);

        if (userVip == null || userVip.getEndDate() < System.currentTimeMillis()) {
            level = 1;
            status = "您不是会员";
            //userVip.setStatus("您不是会员");
            if (num == 1) {

                endDate = DateUtils.longToString(System.currentTimeMillis() + 1000 * 3600 * 365,"yyyy年-MM月-dd日 HH:mm:ss");
            }else if (num == 2) {
                endDate = DateUtils.longToString(System.currentTimeMillis() + 2 * 1000 * 3600 * 365,"yyyy年-MM月-dd日 HH:mm:ss");
            }else if (num == 3) {
                endDate = DateUtils.longToString(System.currentTimeMillis() + 3 * 1000 * 3600 * 365,"yyyy年-MM月-dd日 HH:mm:ss");
            }
        }else {
            level = userService.getById(userId).getVipNum();
            status = "会员到期时间" + DateUtils.longToString(userVip.getEndDate(),"yyyy年-MM月-dd日 HH:mm:ss");
            //userVip.setStatus("会员到期时间" + DateUtils.longToString(userVip.getEndDate(),"yyyy年-MM月-dd日 HH:mm:ss"));
            if (num == 1) {
                endDate = DateUtils.longToString(userVip.getEndDate() + 1000 * 3600 * 365,"yyyy年-MM月-dd日 HH:mm:ss");
            }else if (num == 2) {
                endDate = DateUtils.longToString(userVip.getEndDate() + 2 * 1000 * 3600 * 365,"yyyy年-MM月-dd日 HH:mm:ss");
            }else if (num == 3) {
                endDate = DateUtils.longToString(userVip.getEndDate() + 3 * 1000 * 3600 * 365,"yyyy年-MM月-dd日 HH:mm:ss");
            }
        }

        Double price = vipLevelService.findBylevel(level).getSysVip().getPrice();

        map.put("level",level);
        map.put("endDate",endDate);
        map.put("price",price);
        map.put("status",status);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 计算几年后时间戳有问题
     *
     * @api {post} /api/user/operation 会员付款
     * @apiName user.operation
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {Integer} num 会员时长（1：一年 2：两年 3：三年 默认为1）
     * @apiSuccess {Integer} level 会员等级
     * @apiParam {String} endDate 会员时间
     * @apiParam {Double} price 价格
     *
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Long userId,
                    Integer num,
                    Integer level,
                    String endDate,
                    Double price) throws ParseException {

        UserVip userVip = new UserVip();
        userVip.setUser(userService.getById(userId));
        userVip.setDuration(num);
        userVip.setEndDate(DateUtils.stringToDate(endDate,"yyyy-MM-dd HH:mm:ss").getTime());

        User user = userService.getById(userId);
        user.setVipNum(level);
        if (num == 1) {

            user.setExperience(user.getExperience() + 30);
        }else if (num == 2) {

            user.setExperience(user.getExperience() + 100);
        }else if (num == 3) {

            user.setExperience(user.getExperience() + 500);
        }
        userService.update(user);

        String sn = CommonUtils.generateSn(); // 订单号
        Order order = new Order();
        order.setUser(userService.getById(userId));
        order.setPrice(price * num);
        order.setSn(sn);
        orderService.create(order);

        Result obj = new Result(true).data(order);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
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
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object} tUser 被评论用户
     * @apiSuccess {Long} tUser.id 被评论用户id
     * @apiSuccess {String} tUser.nickname 被评论用户昵称
     * @apiSuccess {String} content 帖子内容
     * @apiSuccess {String} createDate 评论时间
     *
     *
     */
    @RequestMapping(value = "/commentList")
    public void commentList(HttpServletResponse response, Long userId) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

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
     * @apiParam {Long} userId 用户id <必传 />
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
    public void postList(HttpServletResponse response, Long userId) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

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
     * @apiParam {Long} postId 帖子id <必传 />
     *
     * @apiSuccess {Object} post 帖子
     * @apiSuccess {String} post.content 帖子内容
     * @apiSuccess {Long} post.createDate 帖子创建时间
     *
     * @apiSuccess {Object} post.user 用户列表
     * @apiSuccess {Long} post.user.id 用户id
     * @apiSuccess {String} post.user.nickname 用户昵称
     *
     * @apiSuccess {Object} postImages 帖子图片列表
     * @apiSuccess {String} postImages.avater 帖子图片
     *
     * @apiSuccess {Object} postComments 帖子评论列表
     * @apiSuccess {String} postComments.content 帖子内容
     * @apiSuccess {Long} postComments.createDate 帖子创建时间
     * @apiSuccess {Object} postComments.fUser 帖子评论人
     * @apiSuccess {Long} postComments.fUser.id 帖子评论人id
     * @apiSuccess {String} postComments.fUser.avater 帖子评论人头像
     * @apiSuccess {String} postComments.fUser.nickname 帖子评论人昵称
     *
     */
    @RequestMapping(value = "/postInfo")
    public void postInfo(HttpServletResponse response, Long postId) {

        if (postId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

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
     * 完成
     *
     *  @api {post} /api/user/watchingList 我的看球列表
     * @apiName user.watchingList
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object} girlUserList 用户约看列表
     * @apiSuccess {Long} girlUserList.id 约看id
     * @apiSuccess {Double} girlUserList.tip 红包（小费）
     * @apiSuccess {Long} girlUserList.startDate 预约时间
     *
     * @apiSuccess {Object} girlUserList.girl 宝贝
     * @apiSuccess {String} girlUserList.girl.avater 宝贝头像
     * @apiSuccess {String} girlUserList.girl,nickname 宝贝昵称
     * @apiSuccess {Double} girlUserList.girl.price 宝贝价格
     *
     * @apiSuccess {Object} girlUserList.bigRace 赛事
     * @apiSuccess {Long} girlUserList.bigRace.id 赛事id
     * @apiSuccess {Long} girlUserList.bigRace.team1name 球队1的名字
     * @apiSuccess {Object} girlUserList.bigRace.team2name 球队2的名字
     *
     */
    @RequestMapping(value = "/watchingList")
    public void watchingList(HttpServletResponse response, Long userId) {

        if (userId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<GirlUser> girlUserList = girlUserService.findByUserId(userId);

        Result obj = new Result(true).data(girlUserList);
        String result = JsonUtil.obj2ApiJson(obj,"user","stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/watchingInfo 我的约看详情
     * @apiName user.watchingInfo
     * @apiGroup user
     * @apiParam {Long} girlUserId 约看id <必传 />
     *
     * @apiSuccess {Object} girlUsers 用户约看列表
     * @apiSuccess {Double} girlUsers.tip 红包（小费）
     * @apiSuccess {Double} girlUsers.price 总费用
     *
     * @apiSuccess {Object} girlUsers.girl 宝贝
     * @apiSuccess {String} girlUsers.girl.avater 宝贝头像
     * @apiSuccess {String} girlUsers.girl,nickname 宝贝昵称
     * @apiSuccess {Integer} girlUsers.duration 宝贝年龄
     * @apiSuccess {Double} girlUsers.tip 宝贝身高
     * @apiSuccess {Double} girlUsers.tip 宝贝体重
     *
     * @apiSuccess {Object} girlUsers.bigRace 赛事
     * @apiSuccess {Long} girlUsers.bigRace.id 赛事id
     * @apiSuccess {Long} girlUsers.bigRace.team1name 球队1的名字
     * @apiSuccess {Object} girlUsers.bigRace.team2name 球队2的名字
     *
     */
    @RequestMapping(value = "/watchingInfo")
    public void watchingInfo(HttpServletResponse response, Long girlUserId) {

        if (girlUserId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        GirlUser girlUsers = girlUserService.getById(girlUserId);

        Result obj = new Result(true).data(girlUsers);
        String result = JsonUtil.obj2ApiJson(obj,"user","stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/confirm 我的看球确认
     * @apiName user.confirm
     * @apiGroup user
     * @apiParam {Long} watchingId 约看id
     *
     */
    @RequestMapping(value = "/confirm")
    public void confirm(HttpServletResponse response, Long watchingId) {

        if (watchingId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        GirlUser girlUser = girlUserService.getById(watchingId);

        girlUser.setStatus(1);
        girlUserService.update(girlUser);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/user/comment 我的看球评价
     * @apiName user.comment
     * @apiGroup user
     * @apiParam {Long} watchingId 约看id <必传 />
     * @apiParam {Integer} star 服务打分 <必传 />
     * @apiParam {String} content 评论内容 <必传 />
     *
     */
    @RequestMapping(value = "/comment")
    public void comment(HttpServletResponse response, Integer star, String content, Long watchingId) {

        if (watchingId == null || star == null || content == null || content == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        GirlUser girlUser = girlUserService.getById(watchingId);

        if (girlUser.getStatus() == 1) {

            GirlComment girlComment = new GirlComment();
            girlComment.setUser(girlUser.getUser());
            girlComment.setGirl(girlUser.getGirl());
            girlComment.setStar(star);
            girlComment.setContent(content);
            girlCommentService.create(girlComment);
        }

        WebUtil.printApi(response, new Result(true));
    }
}
