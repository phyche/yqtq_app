package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.GirlImageVo;
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
    private CityService cityService;

    @Autowired
    private MessageWatchingService messageWatchingService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private MessageRecordService messageRecordService;

    /**
     * 完成
     *
     * @api {post} /api/watching/telecastList 直播看球列表
     * @apiName watching.telecastList
     * @apiGroup watching
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     * @apiSuccess {Object}  list 直播看球列表
     * @apiSuccess {Long} list.id 看球id
     * @apiSuccess {String} list.name 看球名称
     * @apiSuccess {String} list.avater 封面
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
        String result = JsonUtil.obj2ApiJson(obj, "cityId");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/inviteBall 看球邀请
     * @apiName watching.inviteBall
     * @apiGroup watching
     * @apiParam {Integer} type 类型（1：直播看球，0：现场看球） <必传/>
     * @apiParam {Long} id 看球id <必传/>
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} toUserId 好友id <必传/>
     */
    @RequestMapping(value = "/inviteBall")
    public void inviteBall(HttpServletResponse response,
                           Integer type,
                           Long id,
                           Long userId,
                           Long toUserId) {

        if (userId == null || id == null || toUserId == null || type == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        messageWatchingService.inviteBall(response, type, id, userId, toUserId);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/sceneList 现场看球列表
     * @apiName watching.sceneList
     * @apiGroup watching
     * @apiParam {Long} cityId 城市id <必传/>
     * @apiSuccess {Object}  info.bigRace 现场看球列表
     * @apiSuccess {Long} info.bigRace.id 看球id
     * @apiSuccess {String} info.bigRace.team1name 球队1名称
     * @apiSuccess {String} info.bigRace.avater1 球队1队徽
     * @apiSuccess {String} info.bigRace.team2name 球队2名称
     * @apiSuccess {String} info.bigRace.avater2 球队2队徽
     * @apiSuccess {Long} info.bigRace.startDate 开始时间
     * @apiSuccess {Object}  info.bigRace.stadium 现场看球球场
     * @apiSuccess {String} info.bigRace.stadium.name 球场名字
     * @apiSuccess {String} info.bigRace.description 看球描述
     * @apiSuccess {Object} info.girlImageList 宝贝
     * @apiSuccess {Long} info.girlImageList.id 图片id
     * @apiSuccess {Long} info.girlImageList.girlId 宝贝id
     * @apiSuccess {String} info.girlImageList.url 宝贝封面
     * @apiSuccess {String} info.girlImageList.name 宝贝名字
     * @apiSuccess {Integer} info.girlImageList.age 宝贝年龄
     * @apiSuccess {Double} info.girlImageList.height 宝贝身高
     * @apiSuccess {Double} info.girlImageList.weight 宝贝体重
     */
    @RequestMapping(value = "/sceneList")
    public void sceneList(HttpServletResponse response, Long cityId) {

        if (cityId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = bigRaceService.sceneList(response, cityId);

        //Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(createMap("info", map));
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
     * @apiSuccess {Object}  girlInfo.girl 看球宝贝列表
     * @apiSuccess {Long} girlInfo.girl.id 宝贝id
     * @apiSuccess {String} girlInfo.girl.name 宝贝昵称
     * @apiSuccess {Integer} girlInfo.girl.age 宝贝年龄
     * @apiSuccess {Double} girlInfo.girl.height 宝贝身高
     * @apiSuccess {Double} girlInfo.girl.weight 宝贝体重
     * @apiSuccess {Double} girlInfo.girl.price 宝贝价格
     * @apiSuccess {Double} girlInfo.girl.aveStar 宝贝陪看评价
     * @apiSuccess {Integer} girlInfo.girl.orderNum 宝贝预约次数
     * @apiSuccess {String} girlInfo.girl.interest 宝贝兴趣
     * @apiSuccess {String} girlInfo.girl.profession 宝贝职业
     * @apiSuccess {String} girlInfo.girl.favoriteTeam 宝贝喜欢球队
     * @apiSuccess {String} girlInfo.girl.label 宝贝签名
     * @apiSuccess {Integer} girlInfo.girl.cityId 宝贝陪看区域
     * @apiSuccess {Object} girlInfo.girlImages1 宝贝相册列表
     * @apiSuccess {Integer} girlInfo.girlImages1.id 宝贝相册id
     * @apiSuccess {String} girlInfo.girlImages1.avater 宝贝相册
     * @apiSuccess {Object} girlInfo.girlImages 宝贝封面列表
     * @apiSuccess {Integer} girlInfo.girlImages.id 宝贝封面id
     * @apiSuccess {String} girlInfo.girlImages.avater 宝贝封面
     */
    @RequestMapping(value = "/girlInfo")
    public void girlInfo(HttpServletResponse response, Long girlId) {

        if (girlId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = girlService.girlInfo(response, girlId);

        Result obj = new Result(true).data(createMap("girlInfo", map));
        String result = JsonUtil.obj2ApiJson(obj, "girl", "type", "girlImageList");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/watching/girlComment 宝贝陪看评价
     * @apiName watching.girlComment
     * @apiGroup watching
     * @apiParam {Long} girlId 宝贝id <必传/>
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     * @apiSuccess {Object}  list 宝贝陪看评价列表
     * @apiSuccess {Object}  list.GirlComment 宝贝陪看评价列表
     * @apiSuccess {Long} list.GirlComment.id 评价id
     * @apiSuccess {Integer} list.GirlComment.star 服务打分
     * @apiSuccess {String} list.GirlComment.content 评论内容
     * @apiSuccess {Long} list.GirlComment.createDate 评论时间
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/girlComment")
    public void girlComment(HttpServletResponse response,
                            Long girlId,
                            Integer pageNum,
                            Integer pageSize) {

        if (girlId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Page<GirlComment> page = girlCommentService.page(girlId, pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj, "cityId");
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
     * @apiParam {Double} tip 红包费
     *
     * @apiSuccess {Object} order 订单
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/orderGirl")
    public void orderGirl(HttpServletResponse response,
                          Long userId,
                          Long girlId,
                          Long sceneId,
                          Double tip) {

        if (userId == null || girlId == null || sceneId == null || tip == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Order order = orderService.orderGirl(response, userId, girlId, sceneId, tip);

        // 当前没有支付接口，因此状态直接为已支付
        PayCallBackApi.changeOrderStatus(orderService, order.getSn(), null, response);

        Result obj = new Result(true).data(createMap("order", order));
        String result = JsonUtil.obj2ApiJson(obj, "reserve", "user", "reserveTeam", "girlUser", "stadium");
        WebUtil.printApi(response, result);
    }

}
