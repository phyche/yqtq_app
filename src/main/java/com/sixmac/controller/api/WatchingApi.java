package com.sixmac.controller.api;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.APIFactory;
import com.sixmac.utils.CommonUtils;
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
     * @apiSuccess {Integer} list.id 看球id
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

        Page<WatchingRace> page = watchingRaceService.page(pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"cityId","description");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/telecastInfo 直播看球详情
     * @apiName watching.telecastInfo
     * @apiGroup watching
     * @apiParam {Integer} telecastId 看球id
     *
     * @apiSuccess {Object}  list 直播看球列表
     * @apiSuccess {Integer} list.id 看球id
     * @apiSuccess {String} list.name 看球名称
     * @apiSuccess {String} list.avater 封面
     * @apiSuccess {String} list.description 介绍
     *
     */
    @RequestMapping(value = "/telecastInfo")
    public void telecastInfo(HttpServletResponse response, Integer telecastId) {

        WatchingRace watchingRace = watchingRaceService.getById(telecastId);

        Result obj = new Result(true).data(watchingRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/sceneList 现场看球列表
     * @apiName watching.sceneList
     * @apiGroup watching
     * @apiParam {Integer} cityId 城市id
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 现场看球列表
     * @apiSuccess {Integer} list.id 看球id
     * @apiSuccess {String} list.team1name 球队1名称
     * @apiSuccess {String} list.avater1 球队1队徽
     * @apiSuccess {String} list.team2name 球队2名称
     * @apiSuccess {String} list.avater2 球队2队徽
     * @apiSuccess {Long} list.startDate 开始时间
     * @apiSuccess {Object}  list.stadium 现场看球球场
     * @apiSuccess {String} list.stadium.name 球场名字
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/sceneList")
    public void sceneList(HttpServletResponse response,
                          Integer cityId,
                          Integer pageNum,
                          Integer pageSize) {

        Page<BigRace> page = bigRaceService.page(cityId, pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap);
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/watching/sceneInfo 现场看球详情
     * @apiName watching.sceneInfo
     * @apiGroup watching
     * @apiParam {Integer} sceneId 看球id <必传/>
     *
     * @apiSuccess {Object}  list 现场看球列表
     * @apiSuccess {Integer} list.id 看球id
     * @apiSuccess {String} list.name 看球名称
     * @apiSuccess {String} list.description 看球描述
     * @apiSuccess {String} list.team1name 球队1名称
     * @apiSuccess {String} list.avater1 球队1队徽
     * @apiSuccess {String} list.team2name 球队2名称
     * @apiSuccess {String} list.avater2 球队2队徽
     * @apiSuccess {Long} list.startDate 开始时间
     * @apiSuccess {Object}  list.stadium 现场看球球场
     * @apiSuccess {String} list.stadium.name 球场名字
     */
    @RequestMapping(value = "/sceneInfo")
    public void sceneInfo(HttpServletResponse response, Integer sceneId) {

        BigRace bigRace = bigRaceService.getById(sceneId);

        Result obj = new Result(true).data(bigRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/girlList 现场看球宝贝列表
     * @apiName watching.girlList
     * @apiGroup watching
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 现场看球宝贝列表
     * @apiSuccess {Object} list.girl 宝贝
     * @apiSuccess {Integer} list.girl.id 宝贝id
     * @apiSuccess {String} list.avater 宝贝封面
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     *
     */
    @RequestMapping(value = "/girlList")
    public void girlList(HttpServletResponse response,
                         Integer pageNum,
                         Integer pageSize) {

        Page<GirlImage> page = girlImageService.page(0, pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"girl");
        WebUtil.printApi(response, result);

    }

    /**
     * 完成
     *
     * @api {post} /api/watching/girlInfo 现场看球宝贝详情
     * @apiName watching.girlInfo
     * @apiGroup watching
     * @apiParam {Integer} girlId 宝贝id <必传/>
     *
     * @apiSuccess {Object}  girl 看球宝贝列表
     * @apiSuccess {Integer} girl.id 宝贝id
     * @apiSuccess {String} girl.name 宝贝昵称
     * @apiSuccess {Integer} girl.age 宝贝年龄
     * @apiSuccess {Double} girl.height 宝贝身高
     * @apiSuccess {Double} girl.weight 宝贝体重
     * @apiSuccess {Double} girl.price 宝贝价格
     * @apiSuccess {Integer} girl.orderNum 宝贝预约次数
     * @apiSuccess {String} girl.interest 宝贝兴趣
     * @apiSuccess {String} girl.profession 宝贝职业
     * @apiSuccess {String} girl.favoriteTeam 宝贝喜欢球队
     * @apiSuccess {String} girl.label 宝贝签名
     * @apiSuccess {Integer} girl.cityId 宝贝陪看区域
     *
     * @apiSuccess {Object} girlImages 宝贝相册列表
     * @apiSuccess {Integer} girlImages.id 宝贝相册id
     * @apiSuccess {String} girlImages.avater 宝贝相册
     */
    @RequestMapping(value = "/girlInfo")
    public void girlInfo(HttpServletResponse response, Integer girlId) {
        Map<String,Object> map = new HashMap<String,Object>();
        //宝贝个人信息
        Girl girl = girlService.getById(girlId);

        //宝贝相册
        List<GirlImage> girlImages = girlImageService.findByGirlId(girlId);

        //宝贝预约数
        List<GirlUser> girlUserList = girlUserService.findByGirlId(girlId);
        if (girlUserList == null) {
            girl.setOrderNum(0);
        }
        girl.setOrderNum(girlUserList.size());
        girlService.update(girl);

        map.put("girl",girl);
        map.put("girlImages",girlImages);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"girl");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/girlComment 宝贝陪看评价
     * @apiName watching.girlComment
     * @apiGroup watching
     * @apiParam {Integer} girlId 宝贝id <必传/>
     *
     * @apiSuccess {Object}  girlCommentList 宝贝陪看评价列表
     * @apiSuccess {Integer} girlCommentList.id 评价id
     * @apiSuccess {Integer} girl.name 服务打分
     * @apiSuccess {String} girl.age 评论内容
     * @apiSuccess {Long} girl.height 评论时间
     *
     */
    @RequestMapping(value = "/girlComment")
    public void girlComment(HttpServletResponse response, Integer girlId) {

        List<GirlComment> girlCommentList = girlCommentService.findByGirlId(girlId);

        Result obj = new Result(true).data(girlCommentList);
        String result = JsonUtil.obj2ApiJson(obj,"girl","user");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/orderGirl 约宝贝看球确认
     * @apiName watching.orderGirl
     * @apiGroup watching
     * @apiParam {Integer} userId 用户id <必传/>
     * @apiParam {Integer} girlId 宝贝id <必传/>
     * @apiParam {Integer} sceneId 比赛id <必传/>
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
                          Integer userId,
                          Integer girlId,
                          Integer sceneId) {

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

        //约宝贝时间 暂无
        girlUser.setDuration(2);
        girlUser.setPrice(girl.getPrice());
        girlUserService.create(girlUser);

        map.put("girlUser",girlUser);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"user","stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 支付（完成）
     *
     * @api {post} /api/watching/pay 约宝贝支付
     * @apiName watching.pay
     * @apiGroup watching
     * @apiParam {Integer} girlUserId 约看id <必传/>
     * @apiParam {Double} tip 红包费
     *
     * @apiSuccess {Object} girlUsers 用户约看列表
     * @apiSuccess {Double} girlUsers.tip 红包（小费）
     * @apiSuccess {Double} girlUsers.price 总费用
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Double tip,
                    Integer girlUserId,
                    Double money) {

        GirlUser girlUser = girlUserService.getById(girlUserId);
        if (tip == null) {
            girlUser.setTip(0.0);
        }
        girlUser.setTip(tip);
        girlUserService.update(girlUser);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUsername(girlUser.getUser().getNickname());
        order.setStadiumname(girlUser.getStadium().getName());
        order.setPrice(girlUser.getPrice() + girlUser.getTip());
        order.setSn(sn);
        orderService.create(order);

        money = order.getPrice();

        Result obj = new Result(true).data(money);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
