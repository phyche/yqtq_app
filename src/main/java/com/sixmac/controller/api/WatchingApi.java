package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
@Controller
@RequestMapping(value = "api/watching")
public class WatchingApi extends CommonController {

    @Autowired
    private WatchingRaceService watchingRaceService;

    @Autowired
    private BigRaceService bigRaceService;

    @Autowired
    private GirlService girlService;

    @Autowired
    private GirlImageService girlImageService;

    @Autowired
    private UserService userService;

    @Autowired
    private GirlUserService girlUserService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GirlCommentService girlCommentService;

    @Autowired
    private GirlServiceMessageService girlServiceMessageService;

    @Autowired
    private CityService cityService;

    @Autowired
    private MessageWatchingService messageWatchingService;

    @Autowired
    private VipLevelService vipLevelService;

    /**
     * 完成
     *
     * @api {post} /api/watching/telecastList 直播看球列表
     * @apiName watching.telecastList
     * @apiGroup watching
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 直播看球列表
     * @apiSuccess {Long} list.id 看球id
     * @apiSuccess {String} list.name 看球名称
     * @apiSuccess {String} list.avater 封面
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/telecastList")
    public void telecastList(HttpServletResponse response,
                             Integer pageNum,
                             Integer pageSize) {

        Page<WatchingRace> page = watchingRaceService.page(0, pageNum, pageSize);

        for (WatchingRace watchingRace : page.getContent()) {
            if (StringUtils.isNotBlank(watchingRace.getAvater())) {
                watchingRace.setAvater(ConfigUtil.getString("upload.url") + watchingRace.getAvater());
            }
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj,"cityId","description");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/telecastInfo 直播看球详情
     * @apiName watching.telecastInfo
     * @apiGroup watching
     * @apiParam {Long} telecastId 看球id <必传/>
     *
     * @apiSuccess {Object}  watchingRace 直播看球列表
     * @apiSuccess {Long} watchingRace.id 看球id
     * @apiSuccess {String} watchingRace.name 看球名称
     * @apiSuccess {String} watchingRace.avater 看球封面
     * @apiSuccess {String} watchingRace.description 介绍
     *
     */
    @RequestMapping(value = "/telecastInfo")
    public void telecastInfo(HttpServletResponse response, Long telecastId) {

        if (telecastId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        WatchingRace watchingRace = watchingRaceService.getById(telecastId);
        if (StringUtils.isNotBlank(watchingRace.getAvater())) {
            watchingRace.setAvater(ConfigUtil.getString("upload.url") + watchingRace.getAvater());
        }

        Result obj = new Result(true).data(createMap("watchingRace", watchingRace));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/telecastOrder 直播看球邀请
     * @apiName watching.telecastOrder
     * @apiGroup watching
     * @apiParam {Long} telecastId 看球id <必传/>
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} toUserId 好友id <必传/>
     *
     */
    @RequestMapping(value = "/telecastOrder")
    public void telecastOrder(HttpServletResponse response,
                              Long telecastId,
                              Long userId,
                              Long toUserId) {

        if (userId == null || telecastId == null || toUserId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageWatching messageWatching = new MessageWatching();

        messageWatching.setUser(userService.getById(userId));
        messageWatching.setType(1);
        messageWatching.setToUser(userService.getById(toUserId));
        messageWatching.setWatchingRace(watchingRaceService.getById(telecastId));

        messageWatchingService.create(messageWatching);

        WebUtil.printApi(response, new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/sceneList 现场看球列表
     * @apiName watching.sceneList
     * @apiGroup watching
     * @apiParam {Long} cityId 城市id <必传/>
     *
     * @apiSuccess {Object}  info.bigRace 现场看球列表
     * @apiSuccess {Long} info.bigRace.id 看球id
     * @apiSuccess {String} info.bigRace.team1name 球队1名称
     * @apiSuccess {String} info.bigRace.avater1 球队1队徽
     * @apiSuccess {String} info.bigRace.team2name 球队2名称
     * @apiSuccess {String} info.bigRace.avater2 球队2队徽
     * @apiSuccess {Long} info.bigRace.startDate 开始时间
     * @apiSuccess {Object}  info.bigRace.stadium 现场看球球场
     * @apiSuccess {String} info.bigRace.stadium.name 球场名字
     *
     * @apiSuccess {Object} info.girlImageList.girlImage 宝贝
     * @apiSuccess {Long} info.girlImageList.girlImage.id 宝贝id
     * @apiSuccess {String} info.girlImageList.girlImage.url 宝贝封面
     *
     */
    @RequestMapping(value = "/sceneList")
    public void sceneList(HttpServletResponse response,
                          Long cityId,
                          Integer pageNum,
                          Integer pageSize) {

        List<BigRace> bigRaceList = bigRaceService.page(cityId, 0);
       // Page<BigRace> page = bigRaceService.page(cityId, 0, pageNum, pageSize);

        Map<String,Object> map = new HashMap<String,Object>();

        List<Long> numList = new ArrayList<Long>();
        //NumVo numVo = new NumVo();
        for (BigRace bigRace : bigRaceList) {

            if (bigRace.getStartDate() - System.currentTimeMillis() > 0) {
                numList.add(bigRace.getStartDate() - System.currentTimeMillis());
            }
        }

        if (numList.size() > 0){
            Long minnum = numList.get(0);
            for (int i=0; i<numList.size(); i ++) {
                if (numList.get(i) >= minnum) {
                    minnum = numList.get(i);
                }
            }

            BigRace bigRace = bigRaceService.getByStartDate(minnum + System.currentTimeMillis());

            if (StringUtils.isNotBlank(bigRace.getAvater1())) {
                bigRace.setAvater1(ConfigUtil.getString("upload.url") + bigRace.getAvater1());
            }
            if (StringUtils.isNotBlank(bigRace.getAvater2())) {
                bigRace.setAvater2(ConfigUtil.getString("upload.url") + bigRace.getAvater2());
            }

            List<GirlImage> girlImageList = new ArrayList<GirlImage>();
            if (bigRace == null) {
                girlImageList = null;
            }else {
                //Page<GirlImage> pageGirlImage = girlImageService.page(0, 0, cityId, pageNum, pageSize);
                List<GirlImage> list = girlImageService.page(0, 0, cityId);
                for (GirlImage girlImage : list) {
                    if (StringUtils.isNotBlank(girlImage.getUrl())) {
                        girlImage.setUrl(ConfigUtil.getString("upload.url") + girlImage.getUrl());
                    }
                    girlImageList.add(girlImage);
                }
            }
            for (int i=0; i<girlImageList.size(); i++) {
                GirlImage girlImage = girlImageList.get(i);
                if (girlImageList.contains(girlImage)) {
                    girlImageList.remove(i);
                }
            }

            map.put("bigRace",bigRace);
            map.put("girlImageList",girlImageList);
        }

        //Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(createMap("info",map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/sceneInfo 现场看球详情
     * @apiName watching.sceneInfo
     * @apiGroup watching
     * @apiParam {Long} sceneId 看球id <必传/>
     *
     * @apiSuccess {Object}  bigRace 现场看球列表
     * @apiSuccess {Long} bigRace.id 看球id
     * @apiSuccess {String} bigRace.name 看球名称
     * @apiSuccess {String} bigRace.description 看球描述
     * @apiSuccess {String} bigRace.team1name 球队1名称
     * @apiSuccess {String} bigRace.avater1 球队1队徽
     * @apiSuccess {String} bigRace.team2name 球队2名称
     * @apiSuccess {String} bigRace.avater2 球队2队徽
     * @apiSuccess {Long} bigRace.startDate 开始时间
     * @apiSuccess {Object}  bigRace.stadium 现场看球球场
     * @apiSuccess {String} bigRace.stadium.name 球场名字
     *
     */
    @RequestMapping(value = "/sceneInfo")
    public void sceneInfo(HttpServletResponse response, Long sceneId) {

        if (sceneId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        BigRace bigRace = bigRaceService.getById(sceneId);
        if (StringUtils.isNotBlank(bigRace.getAvater1())) {
            bigRace.setAvater1(ConfigUtil.getString("upload.url") + bigRace.getAvater1());
        }
        if (StringUtils.isNotBlank(bigRace.getAvater2())) {
            bigRace.setAvater2(ConfigUtil.getString("upload.url") + bigRace.getAvater2());
        }

        Result obj = new Result(true).data(createMap("bigRace",bigRace));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/sceneOrder 现场看球邀请
     * @apiName watching.sceneOrder
     * @apiGroup watching
     * @apiParam {Long} sceneId 看球id <必传/>
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} toUserId 好友id <必传/>
     *
     */
    @RequestMapping(value = "/sceneOrder")
    public void sceneOrder(HttpServletResponse response,
                           Long sceneId,
                           Long userId,
                           Long toUserId) {

        if (userId == null || sceneId == null || toUserId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        MessageWatching messageWatching = new MessageWatching();

        messageWatching.setUser(userService.getById(userId));
        messageWatching.setType(0);
        messageWatching.setToUser(userService.getById(toUserId));
        messageWatching.setBigRace(bigRaceService.getById(sceneId));

        messageWatchingService.create(messageWatching);

        WebUtil.printApi(response, new Result(true).data(0));
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/information 足球宝贝服务说明
     * @apiName watching.information
     * @apiGroup watching
     *
     * @apiSuccess {String} content 足球宝贝服务说明内容
     *
     */
    @RequestMapping(value = "/information")
    public void information(HttpServletResponse response) {

        GirlServiceMessage girlServiceMessage = girlServiceMessageService.getById(1l);

        String description = girlServiceMessage.getContent();
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
     * @api {post} /api/watching/girlInfo 现场看球宝贝详情
     * @apiName watching.girlInfo
     * @apiGroup watching
     * @apiParam {Long} girlId 宝贝id <必传/>
     *
     * @apiSuccess {Object}  girlInfo.girl 看球宝贝列表
     * @apiSuccess {Long} girlInfo.girl.id 宝贝id
     * @apiSuccess {String} girlInfo.girl.name 宝贝昵称
     * @apiSuccess {Integer} girlInfo.girl.age 宝贝年龄
     * @apiSuccess {Double} girlInfo.girl.height 宝贝身高
     * @apiSuccess {Double} girlInfo.girl.weight 宝贝体重
     * @apiSuccess {Double} girlInfo.girl.price 宝贝价格
     * @apiSuccess {Integer} girlInfo.girl.orderNum 宝贝预约次数
     * @apiSuccess {String} girlInfo.girl.interest 宝贝兴趣
     * @apiSuccess {String} girlInfo.girl.profession 宝贝职业
     * @apiSuccess {String} girlInfo.girl.favoriteTeam 宝贝喜欢球队
     * @apiSuccess {String} girlInfo.girl.label 宝贝签名
     * @apiSuccess {Integer} girlInfo.girl.cityId 宝贝陪看区域
     *
     * @apiSuccess {Object} girlInfo.girlImages 宝贝相册列表
     * @apiSuccess {Integer} girlInfo.girlImages.id 宝贝相册id
     * @apiSuccess {String} girlInfo.girlImages.avater 宝贝相册
     */
    @RequestMapping(value = "/girlInfo")
    public void girlInfo(HttpServletResponse response, Long girlId) {

        if (girlId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String,Object> map = new HashMap<String,Object>();
        //宝贝个人信息
        Girl girl = girlService.getById(girlId);
        girl.setCityName(cityService.getByCityId(girl.getCityId()).getCity());

        //宝贝相册
        List<GirlImage> girlImages = girlImageService.findByGirlId(girlId);
        for (GirlImage girlImage : girlImages) {
            if (StringUtils.isNotBlank(girlImage.getUrl())) {
                girlImage.setUrl(ConfigUtil.getString("upload.url") + girlImage.getUrl());
            }
        }

        //宝贝预约数
        List<GirlUser> girlUserList = girlUserService.findByGirlId(girlId);
        if (girlUserList == null) {
            girl.setOrderNum(0);
        }
        girl.setOrderNum(girlUserList.size());
        girlService.update(girl);

        map.put("girl",girl);
        map.put("girlImages",girlImages);

        Result obj = new Result(true).data(createMap("girlInfo",map));
        String result = JsonUtil.obj2ApiJson(obj,"girl");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/girlComment 宝贝陪看评价
     * @apiName watching.girlComment
     * @apiGroup watching
     * @apiParam {Long} girlId 宝贝id <必传/>
     *
     * @apiSuccess {Object}  list 宝贝陪看评价列表
     * @apiSuccess {Object}  list.GirlComment 宝贝陪看评价列表
     * @apiSuccess {Long} list.GirlComment.id 评价id
     * @apiSuccess {Integer} list.GirlComment.star 服务打分
     * @apiSuccess {String} list.GirlComment.content 评论内容
     * @apiSuccess {Long} list.GirlComment.createDate 评论时间
     *
     */
    @RequestMapping(value = "/girlComment")
    public void girlComment(HttpServletResponse response, Long girlId) {

        if (girlId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<GirlComment> list = girlCommentService.findByGirlId(girlId);

        Result obj = new Result(true).data(createMap("list",list));
        String result = JsonUtil.obj2ApiJson(obj,"girl","user");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/orderGirl 约宝贝看球确认
     * @apiName watching.orderGirl
     * @apiGroup watching
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} girlId 宝贝id <必传/>
     * @apiParam {Long} sceneId 比赛id <必传/>
     *
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
     * @apiSuccess {Integer} girlUsers.bigRace.id 赛事id
     * @apiSuccess {Long} girlUsers.bigRace.team1name 球队1的名字
     * @apiSuccess {Object} girlUsers.bigRace.team2name 球队2的名字
     */
    @RequestMapping(value = "/orderGirl")
    public void orderGirl(HttpServletResponse response,
                          Long userId,
                          Long girlId,
                          Long sceneId) {

        if (userId == null || girlId == null || sceneId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String,Object> map = new HashMap<String,Object>();

        User user = userService.getById(userId);
        //宝贝个人信息
        Girl girl = girlService.getById(girlId);
        //赛事的信息
        BigRace bigRace = bigRaceService.getById(sceneId);

        GirlUser girlUser = new GirlUser();
        girlUser.setUser(user);
        girlUser.setGirl(girl);
        girlUser.setBigRace(bigRace);
        girlUser.setStartDate(bigRace.getStartDate());
        girlUser.setStadium(bigRace.getStadium());
        girlUser.setPrice(girl.getPrice());
        girlUserService.create(girlUser);

        map.put("girlUser",girlUser);

        Result obj = new Result(true).data(createMap("girlUser",map));
        String result = JsonUtil.obj2ApiJson(obj,"user","stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 支付（完成）
     *
     * @api {post} /api/watching/pay 约宝贝支付
     * @apiName watching.pay
     * @apiGroup watching
     * @apiParam {Long} girlUserId 约看id <必传/>
     * @apiParam {Double} tip 红包费
     *
     * @apiSuccess {Object} girlUsers 用户约看列表
     * @apiSuccess {Double} girlUsers.tip 红包（小费）
     * @apiSuccess {Double} girlUsers.price 总费用
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Double tip,
                    Long girlUserId,
                    Double money) {

        if (girlUserId == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        GirlUser girlUser = girlUserService.getById(girlUserId);
        if (tip == null) {
            girlUser.setTip(0.0);
        }else {
            girlUser.setTip(tip);
        }

        girlUserService.update(girlUser);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUser(girlUser.getUser());
        order.setStadiumname(girlUser.getStadium().getName());

        VipLevel vipLevel = vipLevelService.findBylevel(girlUser.getUser().getVipNum());
        if (girlUser.getUser().getVipNum() != 0) {
            order.setPrice((girlUser.getPrice() + girlUser.getTip()) * vipLevel.getPreferente());
        }else {
            order.setPrice(girlUser.getPrice() + girlUser.getTip());
        }
        order.setSn(sn);
        order.setAction(3);
        orderService.create(order);

        Result obj = new Result(true).data(createMap("order",order));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
