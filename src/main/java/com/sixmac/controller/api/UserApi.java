package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.GirlImageVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private GirlCommentService girlCommentService;

    @Autowired
    private GirlImageService girlImageService;

    @Autowired
    private UserVipService userVipService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SysVipService sysVipService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private GirlService girlService;

    @Autowired
    private GirlUserService girlUserService;

    @Autowired
    private SiteService siteService;

    /**
     * 完成
     *
     * @api {post} /api/user/homePage 用户个人首页
     * @apiName user.homePage
     * @apiGroup user
     * @apiParam {Integer} userId 用户id <必传 />
     * @apiSuccess {Object}  userInfo 用户
     * @apiSuccess {Object}  userInfo.user 用户
     * @apiSuccess {String} userInfo.user.nickname 用户昵称
     * @apiSuccess {String} userInfo.user.avater 用户头像
     * @apiSuccess {Integer} userInfo.user.vipNum 用户会员等级
     * @apiSuccess {Integer} userInfo.user.endDays 用户会员天数
     * @apiSuccess {Integer} userInfo.user.credibility 用户信誉评分
     * @apiSuccess {Integer} userInfo.user.age 用户年龄
     * @apiSuccess {Integer} userInfo.user.gender 用户性别 0:男 1：女
     * @apiSuccess {Double} userInfo.user.height 用户身高
     * @apiSuccess {Double} userInfo.user.weight 用户体重
     * @apiSuccess {Integer} userInfo.user.position 用户位置 0：前 1：中 2：后 3：守
     * @apiSuccess {Long} userInfo.user.birthday 用户出生日期
     * @apiSuccess {Long} userInfo.user.cityId 用户城市
     * @apiSuccess {Object}  userInfo.teamList 加入的球队列表
     * @apiSuccess {Long} userInfo.teamList.id 球队id
     * @apiSuccess {String} userInfo.teamList.name 球队名称
     * @apiSuccess {String} userInfo.teamList.avater 球队队徽
     * @apiSuccess {Integer} userInfo.teamList.count 球员总数
     * @apiSuccess {Integer} userInfo.teamList.battleNum 球队应战数
     * @apiSuccess {Integer} userInfo.teamList.declareNum 球队宣战数
     * @apiSuccess {Object} userInfo.teamList.list 我加入的球队的球员列表
     * @apiSuccess {Long} userInfo.teamList.list.id 球员id
     * @apiSuccess {String} userInfo.teamList.list.avater 球员头像
     * @apiSuccess {Object}  userInfo.myTeam 自己创建的球队
     * @apiSuccess {Long} userInfo.myTeam.id 球队id
     * @apiSuccess {String} userInfo.myTeam.name 球队名称
     * @apiSuccess {String} userInfo.myTeam.avater 球队队徽
     * @apiSuccess {Integer} userInfo.myTeam.count 球员总数
     * @apiSuccess {Integer} userInfo.myTeam.battleNum 球队应战数
     * @apiSuccess {Integer} userInfo.myTeam.declareNum 球队宣战数
     * @apiSuccess {Object} userInfo.myTeam.list 我的球队的球员列表
     * @apiSuccess {Long} userInfo.myTeam.list.id 球员id
     * @apiSuccess {String} userInfo.myTeam.list.avater 球员头像
     */
    @RequestMapping(value = "/homePage")
    public void homePage(HttpServletResponse response, Long userId) {

        if (userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = userService.homePage(response, userId);

        Result obj = new Result(true).data(createMap("userInfo", map));
        String result = JsonUtil.obj2ApiJson(obj, "leaderUser");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/message 说明
     * @apiName user.message
     * @apiGroup user
     * @apiParam {Integer} type 类型 <必传 /> (1:信誉评分说明，2：足球宝贝服务说明，3：保险说明，4：约球须知，5：服务条款说明，6：会员等级说明，7：会员优惠说明，8：看球说明)
     * @apiSuccess {String} content 内容
     */
    @RequestMapping(value = "/message")
    public void message(HttpServletResponse response, Integer type) {

        Message message = messageService.getByType(type);

        String description = message.getContent();
        String others = "<html><head><style type='text/css'>body{overflow-x:hidden;margin:0;padding:0;background:#fff;color:#000;font-size:18px;font-family:Arial,'microsoft yahei',Verdana}body,div,fieldset,form,h1,h2,h3,h4,h5,h6,html,p,span{-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:normal}applet,dd,div,dl,dt,h1,h2,h3,h4,h5,h6,html,iframe,img,object,p,span{margin:0;padding:0;border:0}img{margin:0;padding:0;border:0;vertical-align:top}li,ul{margin:0;padding:0;list-style:none outside none}input[type=text],select{margin:0;padding:0;border:0;background:0;text-indent:3px;font-size:14px;font-family:Arial,'microsoft yahei',Verdana;-webkit-appearance:none;-moz-appearance:none}.wrapper{box-sizing:border-box;padding:10px;width:100%}p{color:#666;line-height:1.6em}.wrapper img{width:auto!important;height:auto!important;max-width:100%}p,span,p span{font-size:18px!important}</head></style>";
        description = description.format("<body><div class='wrapper'>%s</div></body></html>", description);
        description = others + description;

        Result obj = new Result(true).data(createMap("content", description));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * @api {post} /api/user/provinceList 查询省份列表
     * @apiName user.provinceList
     * @apiGroup user
     * @apiSuccess {Object} list 省份列表
     * @apiSuccess {Object} list.province 省份
     * @apiSuccess {Long} list.province.provinceId 省份id
     * @apiSuccess {String} list.province.province 省份名字
     * @apiSuccess {Object} list.province.cityList 城市列表
     * @apiSuccess {Object} list.province.cityList.city 城市
     * @apiSuccess {Long} list.province.cityList.city.id 城市id
     * @apiSuccess {String} list.province.cityList.city.city 城市名字
     */
    @RequestMapping(value = "/provinceList")
    public void provinceList(HttpServletResponse response) {

        List<Province> list = provinceService.findList();

        Result obj = new Result(true).data(createMap("list", list));
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
     * @apiParam {String} nickname 用户昵称
     * @apiParam {Integer} gender 用户性别（0：男 1：女）
     * @apiParam {Long} birthday 用户出生日期
     * @apiParam {Long} provinceId 用户省份
     * @apiParam {Long} cityId 用户城市
     * @apiParam {Double} height 身高
     * @apiParam {Double} weight 体重
     * @apiParam {Integer} position 位置（0：前 1：中 2：后 3：守）
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(HttpServletRequest request,
                     HttpServletResponse response,
                     Long userId,
                     String nickname,
                     Integer gender,
                     Long birthday,
                     Long provinceId,
                     Long cityId,
                     Double height,
                     Double weight,
                     Integer position) {

        MultipartFile multipartFile = null;
        MultipartRequest multipartRequest = null;

        if (request instanceof MultipartRequest) {
            multipartRequest = (MultipartRequest) request;

        }

        if (userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        User user = userService.getById(userId);

        int age = 0;

        Date now = new Date();

        if (birthday != null) {
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
        }

        if (multipartRequest != null) {
            multipartFile = multipartRequest.getFile("avater");
            if (null != multipartFile) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(multipartFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.setAvater(fileBo.getPath());
            }
        }

        /*MultipartFile multipartFile = multipartRequest.getFile("mainImage");
        if (null != multipartFile) {
            String url = QiNiuUploadImgUtil.upload(multipartFile);
            //magazine.setCover(url);
            user.setAvater(url);
        }*/

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

        Result obj = new Result(true).data(createMap("user", user));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     *
     * @api {post} /api/user/operation 会员操作
     * @apiName user.operation
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {Integer} num 会员时长（1：一年 2：两年 3：三年 默认为1）<必传 />
     *
     * @apiSuccess {String} vip.status 会员状态
     * @apiSuccess {Integer} vip.level 会员等级
     * @apiSuccess {String} vip.endDate 会员时间
     * @apiSuccess {Double} vip.price 价格
     * @apiSuccess {Integer} vip.experience 当前经验值
     * @apiSuccess {Integer} vip.leftExperience 到下一级经验值
     */
    @RequestMapping(value = "/operation")
    public void operation(HttpServletResponse response, Long userId, Integer num) throws ParseException {

        if (userId == null || num == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = userService.operation(response, userId, num);

        Result obj = new Result(true).data(createMap("vip", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 计算几年后时间戳有问题
     *
     * @api {post} /api/user/pay 会员付款
     * @apiName user.pay
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {Integer} num 会员时长（1：一年 2：两年 3：三年 默认为1）<必传 />
     * @apiParam {Double} price 价格 <必传 />
     * @apiSuccess {Object} payInfo 订单
     * @apiSuccess {String} payInfo.userName 用户昵称
     * @apiSuccess {Double} payInfo.price 订单金额
     * @apiSuccess {Long} payInfo.sn 订单号
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Long userId,
                    Integer num,
                    Long endDate,
                    Double price) throws ParseException {

        if (userId == null || num == null || price == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        Order order = orderService.pay(response, userId, num, endDate, price);

        Result obj = new Result(true).data(createMap("payInfo", order));
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
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object} list 评论
     * @apiSuccess {Object} list.tUser 被评论用户
     * @apiSuccess {Long} list.tUser.id 被评论用户id
     * @apiSuccess {String} list.tUser.nickname 被评论用户昵称
     * @apiSuccess {Object} list.post 被评论帖子
     * @apiSuccess {String} list.post.content 帖子内容
     * @apiSuccess {String} list.createDate 评论时间
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/commentList")
    public void commentList(HttpServletResponse response,
                            Long userId,
                            Integer pageNum,
                            Integer pageSize ) {

        if (userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Page<PostComment> page = postCommentService.findByFuserId(userId, pageNum, pageSize);
        Post post = null;
        for (PostComment postComment : page.getContent()) {
            post = postService.getById(postComment.getPostId());
            postComment.setPost(post);
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);

        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj,"postId", "postImages", "postCommentList", "fUser", "user", "mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "gender", "birthday");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/user/postList 我的帖子
     * @apiName user.postList
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object} list 帖子
     * @apiSuccess {Object} list.user 用户
     * @apiSuccess {String} list.user.avater 用户头像
     * @apiSuccess {String} list.user.nickname 用户昵称
     * @apiSuccess {Object} list.postImage 帖子图片列表
     * @apiSuccess {String} list.postImage.avater 帖子图片
     * @apiSuccess {String} list.content 帖子内容
     * @apiSuccess {Integer} list.commentNum 帖子评论数
     * @apiSuccess {Long} list.createDate 创建时间
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/postList")
    public void postList(HttpServletResponse response,
                         Long userId,
                         Integer pageNum,
                         Integer pageSize) {

        if (userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Page<Post> page = postService.page(userId, pageNum, pageSize);
        for (Post post : page.getContent()) {
            post.setCommentNum(postCommentService.findByPostId(post.getId()).size());
            //post.setPostImages(postImageService.findByPostId(post.getId()));
            for (PostImage postImage : postImageService.findByPostId(post.getId())) {
                if (StringUtils.isNotBlank(postImage.getAvater())) {
                    postImage.setAvater(ConfigUtil.getString("upload.url") + postImage.getAvater());
                }
            }
            if (StringUtils.isNotBlank(post.getUser().getAvater())) {
                post.getUser().setAvater(ConfigUtil.getString("upload.url") + post.getUser().getAvater());
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
     * @api {post} /api/user/postInfo 我的帖子详情
     * @apiName user.postInfo
     * @apiGroup user
     * @apiParam {Long} postId 帖子id <必传 />
     * @apiSuccess {Object} postInfo 帖子
     * @apiSuccess {String} postInfo.content 帖子内容
     * @apiSuccess {Long} postInfo.createDate 帖子创建时间
     * @apiSuccess {Object} postInfo.user 用户列表
     * @apiSuccess {Long} postInfo.user.id 用户id
     * @apiSuccess {String} postInfo.user.nickname 用户昵称
     * @apiSuccess {Object} postInfo.postImages 帖子图片列表
     * @apiSuccess {String} postInfo.postImages.avater 帖子图片
     * @apiSuccess {Object} postInfo.postComments 帖子评论列表
     * @apiSuccess {String} postInfo.postComments.content 帖子内容
     * @apiSuccess {Long} postInfo.postComments.createDate 帖子创建时间
     * @apiSuccess {Object} postInfo.postComments.fUser 帖子评论人
     * @apiSuccess {Long} postInfo.postComments.fUser.id 帖子评论人id
     * @apiSuccess {String} postInfo.postComments.fUser.avater 帖子评论人头像
     * @apiSuccess {String} postInfo.postComments.fUser.nickname 帖子评论人昵称
     */
    @RequestMapping(value = "/postInfo")
    public void postInfo(HttpServletResponse response, Long postId) {

        if (postId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        //我的帖子
        Post post = postService.getById(postId);

        Result obj = new Result(true).data(createMap("postInfo", post));
        String result = JsonUtil.obj2ApiJson(obj, "post", "mobile", "password", "age", "height", "weight", "position", "credibility", "vipNum", "integral", "experience", "proviceId", "endDate", "cityId", "gender", "birthday");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/watchingList 我的看球列表
     * @apiName user.watchingList
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object} list 用户约看列表
     * @apiSuccess {Long} list.id 约看id
     * @apiSuccess {Double} list.tip 红包（小费）
     * @apiSuccess {Long} list.startDate 预约时间
     * @apiSuccess {Integer} list.status 状态（0 ：未确认 1：已确认 2；已评价 ）
     * @apiSuccess {Object} list.girl 宝贝
     * @apiSuccess {Long} list.girl.id 宝贝id
     * @apiSuccess {String} list.girl,nickname 宝贝昵称
     * @apiSuccess {Double} list.girl.price 宝贝价格
     * @apiSuccess {Integer} list.girl.age 宝贝年龄
     * @apiSuccess {Double} list.girl.height 宝贝身高
     * @apiSuccess {Double} list.girl.weight 宝贝体重
     * @apiSuccess {Object} list.girl.girlImageList 宝贝封面列表
     * @apiSuccess {Long} list.girl.girlImageList.id  宝贝封面id
     * @apiSuccess {String} list.girl.girlImageList.url  宝贝封面路径
     * @apiSuccess {Object} list.bigRace 赛事
     * @apiSuccess {Long} list.bigRace.id 赛事id
     * @apiSuccess {String} list.bigRace.team1name 球队1的名字
     * @apiSuccess {String} list.bigRace.team2name 球队2的名字
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {String} list.stadium.name 球场名字
     */
    @RequestMapping(value = "/watchingList")
    public void watchingList(HttpServletResponse response, Long userId) {
        GirlImage tempImage = null;
        if (userId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<GirlUser> list = girlUserService.findByUserId(userId);
        for (GirlUser girlUser : list) {
            Iterator<GirlImage> tempImages = girlUser.getGirl().getGirlImageList().iterator();
            while (tempImages.hasNext()) {
                GirlImage girlImage = tempImages.next();
                if (girlImage.getType() == 1) {
                    tempImages.remove();
                } else if (girlImage.getType() == 0) {
                    if (StringUtils.isNotBlank(girlImage.getUrl())) {
                        girlImage.setUrl(ConfigUtil.getString("upload.url") + girlImage.getUrl());
                    }

                }
            }
            if (girlUser.getGirl().getGirlImageList().size() > 1) {
                tempImage = girlUser.getGirl().getGirlImageList().iterator().next();
                girlUser.getGirl().getGirlImageList().clear();
                girlUser.getGirl().getGirlImageList().add(tempImage);
            }
        }

        Result obj = new Result(true).data(createMap("list", list));
        String result = JsonUtil.obj2ApiJson(obj, "user", "girlComments");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/user/confirm 我的看球确认
     * @apiName user.confirm
     * @apiGroup user
     * @apiParam {Long} watchingId 约看id <必传 />
     */
    @RequestMapping(value = "/confirm")
    public void confirm(HttpServletResponse response, Long watchingId) {

        if (watchingId == null) {
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
     * @apiParam {Double} star 服务打分 <必传 />
     * @apiParam {String} content 评论内容 <必传 />
     */
    @RequestMapping(value = "/comment")
    public void comment(HttpServletResponse response, Double star, String content, Long watchingId) {

        if (watchingId == null || star == null || content == null || content == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        girlCommentService.comment(response, star, content, watchingId);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/user/report 问题反馈
     * @apiName user.report
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     * @apiParam {String} mobile 联系方式
     * @apiParam {String} content 内容
     */
    @RequestMapping(value = "/report")
    public void report(HttpServletResponse response, Long userId, String mobile, String content) {

        User user = userService.getById(userId);

        Report report = new Report();
        if (mobile == null || mobile == "") {
            report.setMobile(user.getMobile());
        } else {
            report.setMobile(mobile);
        }
        report.setUserId(userId);
        report.setContent(content);
        reportService.create(report);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/user/siteList 我的场地
     * @apiName user.siteList
     * @apiGroup user
     * @apiParam {Long} userId 用户id <必传 />
     *
     * @apiSuccess {Object} site 用户场地列表
     * @apiSuccess {Object} site.reserveList 场地列表
     * @apiSuccess {Long} site.reserveList.startTime 预约时间
     * @apiSuccess {Object} site.reserveList.stadium 用户散客球场
     * @apiSuccess {Long} site.reserveList.stadium.id 球场id
     * @apiSuccess {String} site.reserveList.stadium.name 球场名字
     * @apiSuccess {String} site.reserveList.stadium.address 球场地址
     * @apiSuccess {String} site.reserveList.stadium.areaName 球场区域
     * @apiSuccess {String} site.reserveList.stadium.avater 球场封面
     * @apiSuccess {Double} site.reserveList.stadium.price 场地价格
     *
     */
    @RequestMapping(value = "/siteList")
    public void siteList(HttpServletResponse response, Long userId) {

        Map<String, Object> map = new HashMap<String, Object>();

        List<Order> orderList = orderService.findByUserIdAndAction(2, userId);
        List<Reserve> reserveList = new ArrayList<Reserve>();
        for (Order order : orderList) {
            order.getReserve().getStadium().setPrice(siteService.getById(order.getReserve().getSiteId()).getPrice());
            String areaname = areaService.getByAreaId(order.getReserve().getStadium().getAreaId()).getArea();
            order.getReserve().getStadium().setAreaName(areaname);
            reserveList.add(order.getReserve());
        }

        map.put("reserveList", reserveList);
        Result obj = new Result(true).data(createMap("site", map));
        String result = JsonUtil.obj2ApiJson(obj, "user", "insurance", "userReservelist");
        WebUtil.printApi(response, result);
    }
}
