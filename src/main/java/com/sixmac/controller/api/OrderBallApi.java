package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.WatchBallVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2016/5/17 0017.
 * 约散客
 */
@Controller
@RequestMapping(value = "/api/orderBall")
public class OrderBallApi extends CommonController {

    @Autowired
    private UserReserveService userReserveService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private TeamRaceService teamRaceService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageOrderBallService messageOrderBallService;

    @Autowired
    private SysExperienceService sysExperienceService;

    @Autowired
    private SysCredibilityService sysCredibilityService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MessageRecordService messageRecordService;


    /**
     * 完成
     *
     * @api {post} /api/orderBall/list 约球列表
     * @apiName orderBall.list
     * @apiGroup orderBall
     * @apiParam {Integer} timelimit 时间期限
     * @apiParam {Integer} type 球赛类型 N人制 N代表数量
     * @apiParam {Long} areaId 区域ID
     * @apiParam {Double} longitude 经度  <必传/>
     * @apiParam {Double} latitude 纬度  <必传/>
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Integer} list.id 约球id
     * @apiSuccess {String} list.content 约球内容
     * @apiSuccess {Integer} list.matchType 赛制
     * @apiSuccess {Integer} list.joinCount 已报人数
     * @apiSuccess {Integer} list.lackCount 剩余人数
     * @apiSuccess {Object} list.user 创建人
     * @apiSuccess {String} list.user.nickname 创建人昵称
     * @apiSuccess {String} list.user.avater 创建人头像
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {Double} list.stadium.distance 距离
     * @apiSuccess {String} list.stadium.name 球场名字
     * @apiSuccess {Integer} list.stadium.type 球场类型 (0:私人球场 1:公共球场)
     * @apiSuccess {Long} list.startTime 开始时间
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     Double longitude,
                     Double latitude,
                     Integer timelimit,
                     Integer type,
                     Long areaId,
                     Integer pageNum,
                     Integer pageSize) throws ParseException {

        //initPageable(pageNum, pageSize);
        Page<Reserve> page = reserveService.page(timelimit, type, areaId, pageNum, pageSize);
        List<Reserve> list = page.getContent();
        for (Reserve reserve : list) {
            if (longitude == 0.0 && latitude == 0.0) {
                reserve.getStadium().setDistance(-1);
            } else {
                reserve.getStadium().setDistance(CountDistance.gps2m(latitude, longitude, reserve.getStadium().getLatitude(), reserve.getStadium().getLongitude()));
            }
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(DateUtils.longToDate(reserve.getStartTime(),"yyyy-MM-dd HH:mm:ss")) + "," + reserve.getStadium().getName() + "约球了");
            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }
            if (reserve.getType() == 0) {
                reserve.setJoinCount(reserve.getUserReservelist() != null ? reserve.getUserReservelist().size() : 0);
                reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
            }
            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }
        }

        Map<String, Object> dataMap = APIFactory.fitting(page);

        Result obj = new Result(true).data(dataMap);
        String result = JsonUtil.obj2ApiJson(obj, "set", "insurance", "list");
        WebUtil.printApi(response, result);
    }


    /**
     * 完成
     *
     * @api {post} /api/orderBall/orderInfo 约球的详情
     * @apiName orderBall.orderInfo
     * @apiGroup orderBall
     * @apiParam {Long} reserveId 约球ID <必传 />
     * @apiSuccess {Object}  reserveInfo 约球列表
     * @apiSuccess {Long} reserveInfo.id 约球id
     * @apiSuccess {Integer} reserveInfo.type 约球类型（0：散客，1：公共）
     * @apiSuccess {Integer} reserveInfo.status 状态（0:正在组队1:组队成功2:组队失败3:比赛结束）
     * @apiSuccess {Integer} reserveInfo.payment 付款方式 （0:AA 1:全额）
     * @apiSuccess {String} reserveInfo.content 约球内容
     * @apiSuccess {Integer} reserveInfo.matchType 赛制
     * @apiSuccess {Integer} reserveInfo.joinCount 已报人数
     * @apiSuccess {Integer} reserveInfo.lackCount 剩余人数
     * @apiSuccess {Object} reserveInfo.user 创建人
     * @apiSuccess {String} reserveInfo.user.nickname 创建人昵称
     * @apiSuccess {String} reserveInfo.user.avater 创建人头像
     * @apiSuccess {Object} reserveInfo.stadium 球场
     * @apiSuccess {Long} reserveInfo.stadium.id 球场id
     * @apiSuccess {String} reserveInfo.stadium.name 球场名字
     * @apiSuccess {Object} reserveInfo.insurance 保险
     * @apiSuccess {Long} reserveInfo.insurance.id 保险id
     * @apiSuccess {String} reserveInfo.insurance.name 保险名字
     * @apiSuccess {Double} reserveInfo.insurance.price 保险金额
     * @apiSuccess {Double} reserveInfo.avePrice 支付金额
     * @apiSuccess {Double} reserveInfo.sumPrice AA制总金额
     * @apiSuccess {Object} reserveInfo.userReservelist 已报名球友列表
     * @apiSuccess {Long} reserveInfo.userReservelist.id 报名球友id
     * @apiSuccess {String} reserveInfo.userReservelist.nickname 报名球友昵称
     * @apiSuccess {String} reserveInfo.userReservelist.avater 报名球友头像
     * @apiSuccess {Long} reserveInfo.userReservelist.startTime 开始时间
     */
    @RequestMapping(value = "/orderInfo")
    public void orderInfo(HttpServletResponse response, Long reserveId) {

        if (null == reserveId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        try {
            Map<String, Object> map = new HashMap<String, Object>();

            Reserve reserve = reserveService.getById(reserveId);
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(DateUtils.longToDate(reserve.getStartTime(),"yyyy-MM-dd HH:mm:ss")) + "," + reserve.getStadium().getName() + "约球了");

            if (reserve.getType() == 0) {
                reserve.setJoinCount(reserve.getUserReservelist() != null ? reserve.getUserReservelist().size() : 0);
                reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
            }

            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }

            if (null == reserve) {
                WebUtil.printApi(response, new Result(false).msg(ErrorCode.ERROR_CODE_0003));
                return;
            }

            if (reserve.getType() == 0) {
                reserve.setAvePrice(reserve.getPrice() / reserve.getMatchType());
                reserve.setSumPrice(reserve.getPrice());
                reserveService.update(reserve);
            }

            if (reserve.getInsurance() == null) {
                reserve.setInsurance(new SysInsurance());
            }

            Result obj = new Result(true).data(createMap("reserveInfo", reserve));
            String result = JsonUtil.obj2ApiJson(obj, "set", "site", "list");
            WebUtil.printApi(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/orderList 球友的约球列表
     * @apiName orderBall.orderList
     * @apiGroup orderBall
     * @apiParam {Long} playerId 球友ID <必传 />
     * @apiSuccess {Object}  list 约球列表
     * @apiSuccess {Long} list.id 约球id
     * @apiSuccess {Integer} list.type 约球类型（0：散客，1：公共）
     * @apiSuccess {String} list.content 约球内容
     * @apiSuccess {Integer} list.matchType 赛制
     * @apiSuccess {Integer} list.joinCount 已报人数
     * @apiSuccess {Integer} list.lackCount 剩余人数
     * @apiSuccess {Object} list.user 创建人
     * @apiSuccess {String} list.user.nickname 创建人昵称
     * @apiSuccess {String} list.user.avater 创建人头像
     * @apiSuccess {Object} list.stadium 球场
     * @apiSuccess {String} list.stadium.name 球场名字
     * @apiSuccess {Long} list.startTime 开始时间
     */
    @RequestMapping(value = "/orderList")
    public void orderList(HttpServletResponse response, Long playerId) throws ParseException {

        if (null == playerId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<UserReserve> userReserves = userReserveService.findByUserId(playerId);
        for (UserReserve userReserve : userReserves) {

            userReserve.getUser().setAvater(userReserve.getUser().getAvater());
            reserveList.add(userReserve.getReserve());
        }
        for (Reserve reserve : reserveList) {
            reserve.setContent(DateUtils.chinaDayOfWeekAndAM(DateUtils.longToDate(reserve.getStartTime(),"yyyy-MM-dd HH:mm:ss")) + "," + reserve.getStadium().getName() + "约球了");
            if (reserve.getType() == 0) {
                reserve.setJoinCount(reserve.getUserReservelist() != null ? reserve.getUserReservelist().size() : 0);
                reserve.setLackCount(reserve.getMatchType() * 2 - reserve.getJoinCount());
            }
            if (StringUtils.isNotBlank(reserve.getUser().getAvater())) {
                reserve.getUser().setAvater(ConfigUtil.getString("upload.url") + reserve.getUser().getAvater());
            }
        }

        Result obj = new Result(true).data(createMap("list", reserveList));
        String result = JsonUtil.obj2ApiJson(obj, "set", "insurance", "list", "userReservelist");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/order 约球邀请
     * @apiName orderBall.order
     * @apiGroup orderBall
     * @apiParam {Long} reserveId 约球id <必传/>
     * @apiParam {Long} userId 用户id <必传/>
     * @apiParam {Long} toUserId 好友id <必传/>
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response,
                      Long reserveId,
                      Long userId,
                      Long toUserId) {

        if (null == userId || reserveId == null || toUserId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        reserveService.order(response, reserveId, userId, toUserId);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 完成
     *
     * @api {post} /api/orderBall/raceList 球友的赛事列表
     * @apiName orderBall.raceList
     * @apiGroup orderBall
     * @apiParam {Long} playerId 球友ID <必传 />
     * @apiSuccess {Object}  schedule.watchBallVos 球员所在球队为主队赛事列表
     * @apiSuccess {String} schedule.watchBallVos.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVos.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVos.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVos.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVos.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVos.stadiumName 地址
     * @apiSuccess {Long} schedule.watchBallVos.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVos.createDate 发起时间
     * @apiSuccess {String} schedule.watchBallVos.mobile 手机号
     * @apiSuccess {Object}  schedule.watchBallVoList 球员所在球队为客队赛事列表
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamName 主队队名
     * @apiSuccess {String} schedule.watchBallVoList.homeTeamAvater 主队队徽
     * @apiSuccess {String} schedule.watchBallVoList.vTeamName 客队队名
     * @apiSuccess {String} schedule.watchBallVoList.vTeamAvater 客队队徽
     * @apiSuccess {Integer} schedule.watchBallVoList.status 赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）
     * @apiSuccess {String} schedule.watchBallVoList.stadiumName 地址
     * @apiSuccess {Long} schedule.watchBallVoList.startTime 开始时间
     * @apiSuccess {Long} schedule.watchBallVoList.createDate 发起时间
     * @apiSuccess {String} schedule.watchBallVoList.mobile 手机号
     */
    @RequestMapping(value = "/raceList")
    public void raceList(HttpServletResponse response, Long playerId) {

        if (null == playerId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = reserveService.raceList(response, playerId);

        Result obj = new Result(true).data(createMap("schedule", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }


    /**
     * 球友的帖子
     *
     * 与UserApi里我的帖子相同
     */

    /**
     * 完成
     *
     * @api {post} /api/orderBall/pay 球友的约球支付
     * @apiName orderBall.pay
     * @apiGroup orderBall
     * @apiParam {Long} reserveId 约球ID <必传 />
     * @apiParam {Long} userId 用户ID <必传 />
     * @apiParam {Long} messageId 约球消息ID
     * @apiSuccess {Object} payInfo 订单
     * @apiSuccess {String} payInfo.userName 用户昵称
     * @apiSuccess {String} payInfo.stadiumName 球场名称
     * @apiSuccess {Double} payInfo.price 订单金额
     * @apiSuccess {Long} payInfo.sn 订单号
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response, Long reserveId, Long userId, Long messageId, Double money) {

        if (null == userId || reserveId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Order order = reserveService.pay(response, reserveId, userId, messageId, money);

        // 当前没有支付接口，因此状态直接为已支付
        PayCallBackApi.changeOrderStatus(orderService, order.getSn(), null, response);

        Result obj = new Result(true).data(createMap("payInfo", order));
        String result = JsonUtil.obj2ApiJson(obj, "reserve", "user", "reserveTeam", "girlUser", "stadium");
        WebUtil.printApi(response, result);
    }
}
