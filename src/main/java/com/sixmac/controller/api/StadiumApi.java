package com.sixmac.controller.api;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.StadiumVo;
import com.sixmac.entity.vo.TimeVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2016/5/17 0017.
 * 散客订场
 */
@Controller
@RequestMapping(value = "api/stadium")
public class StadiumApi extends CommonController {
    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private SysInsuranceService sysInsuranceService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReserveTeamService reserveTeamService;

    @Autowired
    private UserReserveService userReserveService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private SiteTimeService siteTimeService;

    @Autowired
    private SiteManageService siteManageService;

    /**
     * 完成
     *
     * @api {post} /api/stadium/list 球场列表
     * @apiName stadium.list
     * @apiGroup stadium
     * @apiParam {Integer} type 球赛类型 N人制 N代表数量
     * @apiParam {Double} longitude 经度  <必传/>
     * @apiParam {Double} latitude 纬度  <必传/>
     * @apiParam {Integer} areaId 区域ID
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     * @apiSuccess {Object}  list 球场列表
     * @apiSuccess {Integer} list.id 球场id
     * @apiSuccess {String} list.name 球场名称
     * @apiSuccess {Integer} list.type 球场类型 （0:私人球场 1:公共球场）
     * @apiSuccess {Integer} list.park 是否有停车场 (0:无 1:免费 2:收费)
     * @apiSuccess {String} list.light 灯光类型
     * @apiSuccess {String} list.giving 赠送
     * @apiSuccess {String} stadium.areaName 球场地区名字
     * @apiSuccess {String} list.address 球场地址
     * @apiSuccess {String} list.avater 球场封面
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     Double longitude,
                     Double latitude,
                     Integer areaId,
                     Integer type,
                     Integer pageNum,
                     Integer pageSize) throws IOException {

        if (null == latitude || longitude == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Page<Stadium> page = stadiumService.page(areaId, type, pageNum, pageSize);
        List<Stadium> stadiumList = page.getContent();
        for (Stadium stadium : stadiumList) {

            stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());

            stadium.setDistance(Distance.GetDistance(longitude, latitude, stadium.getLongitude(), stadium.getLatitude()));

        }

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap, "description", "siteType", "sodType");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/info 球场详情
     * @apiName stadium.info
     * @apiGroup stadium
     * @apiParam {Integer} stadiumId 球场ID  <必传/>
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Integer} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {String} stadium.areaName 球场地区名字
     * @apiSuccess {String} stadium.address 球场地址
     * @apiSuccess {String} stadium.avater 球场封面
     * @apiSuccess {String} stadium.siteType 场地类型
     * @apiSuccess {String} stadium.sodType 草皮类型
     * @apiSuccess {Integer} stadium.light 灯光(0：有灯光 1：没有灯光)
     * @apiSuccess {Integer} stadium.park 停车场（0：有停车场 1；没有停车场）
     * @apiSuccess {String} stadium.giving 赠送
     * @apiSuccess {String} stadium.description 球场简介
     * @apiSuccess {Object} sites 球场场地
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Integer stadiumId) throws ParseException {

        if (null == stadiumId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Stadium stadium = stadiumService.getById(stadiumId);
        stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());

        if (stadiumService.getById(stadiumId).getType() == 1) {

            String week = null;
            String date = null;
            Long time = null;

            List<TimeVo> list = new ArrayList<TimeVo>();
            for (int i = 0; i < 7; i++) {
                TimeVo timeVo = new TimeVo();

                time = DateUtils.dateAddDay(DateUtils.longToDate(System.currentTimeMillis(), "yyyy-MM-dd"), i).getTime();
                week = DateUtils.dateToStringWithFormat(DateUtils.dateAddDay(DateUtils.longToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), i), "MM月dd日");
                date = DateUtils.chinaDayOfWeek(DateUtils.dateAddDay(DateUtils.longToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), i));

                timeVo.setDate(date);
                timeVo.setWeek(week);
                timeVo.setTime(time);

                list.add(timeVo);
            }
            map.put("list", list);

        }

        map.put("stadium", stadium);
        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/order 公共球场约散客
     * @apiName stadium.order
     * @apiGroup stadium
     * @apiParam {Integer} stadiumId 球场ID <必传/>
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Integer} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {String} stadium.areaName 球场地区名字
     * @apiSuccess {String} stadium.avater 球场封面
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response, Integer stadiumId) {

        if (null == stadiumId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Stadium stadium = stadiumService.getById(stadiumId);
        stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());

        Result obj = new Result(true).data(stadium);
        String result = JsonUtil.obj2ApiJson(obj, "type", "description", "siteType", "sodType", "light", "park", "giving");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/publish 公共球场发布
     * @apiName stadium.publish
     * @apiGroup stadium
     * @apiParam {Integer} stadiumId 球场ID <必传/>
     * @apiParam {Integer} userId 用户ID <必传/>
     * @apiParam {String} title 标题  <必传/>
     * @apiParam {Long} time 约球时间 <必传/>
     *
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Integer} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {Integer} stadium.cityId 球场地区
     * @apiSuccess {String} stadium.avater 球场封面
     */
    @RequestMapping(value = "/publish")
    public void publish(HttpServletResponse response,
                        Integer userId,
                        Integer stadiumId,
                        String title,
                        Long time) {

        if (null == userId || stadiumId == null || title == null || title == " " || time == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        User user = userService.getById(userId);
        Stadium stadium = stadiumService.getById(stadiumId);

        Reserve reserve = new Reserve();
        reserve.setUser(user);
        reserve.setStadium(stadium);
        reserve.setStartTime(time);
        reserve.setTitle(title);
        reserveService.create(reserve);

        UserReserve userReserve = new UserReserve();
        userReserve.setUser(user);
        userReserve.setReserve(reserve);
        userReserve.setStatus(1);

        userReserveService.create(userReserve);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * @api {post} /api/stadium/siteSelect 场次选择
     * @apiName stadium.siteSelect
     * @apiGroup stadium
     * @apiParam {Integer} stadiumId 球场ID <必传/>
     * @apiParam {Long} time 当天时间戳 <必传/>
     *
     * @apiSuccess {Object}  site 场地
     * @apiSuccess {String}  site.code 场地编号
     * @apiSuccess {Integer} site.type 场地类型  N人制
     * @apiSuccess {String} list 预定字符串 (0：不可预定 1：可预订)
     */
    @RequestMapping(value = "/siteSelect")
    public void siteSelect(HttpServletResponse response, Long time, Integer stadiumId) {

        if (null == stadiumId || time == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();

        // 查询所有可用场地
        List<Site> siteList = siteService.findByStadiumId(stadiumId);
        for (Site site : siteList) {
            // 根据场地id查询所有被预定的场地的时间
            list = new ArrayList<String>();
            for (int i = 8; i < 24; i++) {

                SiteTime siteTime = siteTimeService.findBySiteAndTime(site.getId(),time + i * 1000 * 3600);
                SiteManage siteManage = siteManageService.findBySiteAndTime(site.getId(),time + i * 1000 * 3600);
                if (siteTime == null && siteManage == null) {
                    list.add("1");
                }else {
                    list.add("0");
                }
            }
            site.setList(list);

        }

        map.put("siteList", siteList);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj, "stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成,缺场地编号时间等，同私人球场详情
     *
     * @api {post} /api/stadium/siteOrder 场地预订
     * @apiName stadium.siteOrder
     * @apiGroup stadium
     * @apiParam {Integer} siteId 场地ID <必传/>
     * @apiParam {Integer} status 运动保险状态（0：不买  1：买） <必传/>
     * @apiParam {Long} time 时间戳  <必传/>
     * @apiParam {Integer} start 开始时间点  <必传/>
     * @apiParam {Integer} end 结束时间点  <必传/>
     *
     * @apiSuccess {Object}  siteTime 场地预定
     * @apiSuccess {Integer} siteTime.id 预定id
     * @apiSuccess {Object} siteTime.site 预定场地
     * @apiSuccess {String} siteTime.site.code 场地编号
     * @apiSuccess {String} siteTime.site.type 场地类型
     * @apiSuccess {Long} siteTime.startTime 开始时间
     * @apiSuccess {Long} siteTime.endTime 结束时间
     * @apiSuccess {Object} siteTime.site.stadium 预定球场
     * @apiSuccess {String} siteTime.site.stadium.name 预定球场名字
     * @apiSuccess {String} siteTime.area 预定球场地区
     *
     */
    @RequestMapping(value = "/siteOrder")
    public void siteOrder(HttpServletResponse response,
                          Integer siteId,
                          Integer status,
                          Long time,
                          Integer start,
                          Integer end) {

        if (null == siteId || status == null || time == null || start == null || end == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Site site = siteService.getById(siteId);

        SiteTime siteTime = new SiteTime();
        siteTime.setSite(site);
        siteTime.setStartTime(time + start * 1000 * 3600);
        siteTime.setEndTime(time + end * 1000 * 3600);
        siteTimeService.create(siteTime);

        //status == 1 代表顾客要买保险
        if (status == 1) {
            List<SysInsurance> sysInsurances = sysInsuranceService.findAll();
            map.put("sysInsurances", sysInsurances);
        }

        String area = areaService.getByAreaId(site.getStadium().getAreaId()).getArea();
        map.put("area",area);
        map.put("siteTime", siteTime);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj, "stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/pay 散客支付订单
     * @apiName stadium.pay
     * @apiGroup stadium
     * @apiParam {Integer} siteTimeId 预定场地ID <必传/>
     * @apiParam {Integer} userId 用户ID <必传/>
     * @apiParam {Integer} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     *
     * @apiSuccess {Object}  site 场地
     * @apiSuccess {Object}  sysInsurance 保险
     * @apiSuccess {Integer} sysInsurance.id 保险id
     * @apiSuccess {Integer} sysInsurance.name 保险名称
     * @apiSuccess {String} sysInsurance.price 保险金额
     * @apiSuccess {Integer} siteMoney 场地费
     * @apiSuccess {Object}  vipNum 会员等级
     * @apiSuccess {Integer} preferente 会员折扣
     * @apiSuccess {String} money 总金额
     * @apiSuccess {Object}  reserve 散客预定（约球）
     * @apiSuccess {Integer} reserve.id 预定id
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Integer userId,
                    Integer siteTimeId,
                    Integer insuranceId,
                    Integer num,
                    Double preferente,
                    Double money) {

        if (null == userId || siteTimeId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        if (insuranceId == null) {
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
                num = 0;
                money = siteTimeService.getById(siteTimeId).getSite().getPrice() * preferente;
            } else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
                num = 0;
                money = siteTimeService.getById(siteTimeId).getSite().getPrice() * preferente;
            }

            Reserve reserve = new Reserve();
            reserve.setStadium(siteTimeService.getById(siteTimeId).getSite().getStadium());
            reserve.setSite(siteTimeService.getById(siteTimeId).getSite());
            reserve.setUser(userService.getById(userId));
            reserve.setPrice(money);
            reserve.setMatchType(siteTimeService.getById(siteTimeId).getSite().getType());
            reserveService.create(reserve);
            map.put("reserve", reserve);

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(userService.getById(userId));
            userReserve.setReserve(reserve);
            userReserve.setStatus(1);
            userReserveService.create(userReserve);

        } else {
            SysInsurance sysInsurance = sysInsuranceService.getById(insuranceId);
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
            } else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
            }

            money = (siteTimeService.getById(siteTimeId).getSite().getPrice() + sysInsurance.getPrice() * num) * preferente;

            Reserve reserve = new Reserve();
            reserve.setStadium(siteTimeService.getById(siteTimeId).getSite().getStadium());
            reserve.setSite(siteTimeService.getById(siteTimeId).getSite());
            reserve.setUser(userService.getById(userId));
            reserve.setInsurance(sysInsurance);
            reserve.setPrice(money);
            reserve.setMatchType(siteTimeService.getById(siteTimeId).getSite().getType());
            reserveService.create(reserve);
            map.put("reserve", reserve);

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(userService.getById(userId));
            userReserve.setReserve(reserve);
            userReserve.setStatus(1);
            userReserveService.create(userReserve);

            map.put("sysInsurance", sysInsurance);
        }

        map.put("preferente", preferente);
        map.put("money", money);
        map.put("siteMoney", siteTimeService.getById(siteTimeId).getSite().getPrice());
        map.put("vipNum", userService.getById(userId).getVipNum());

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/payConfirm 散客确认支付订单
     * @apiName stadium.payConfirm
     * @apiGroup stadium
     * @apiParam {Integer} reserveId 预定ID <必传/>
     * @apiParam {Integer} type 支付类型（0：全额支付  1：AA支付） <必传/>
     * @apiSuccess {Object} order 订单
     * @apiSuccess {String} order.userName 用户昵称
     * @apiSuccess {String} order.stadiumName 球场名称
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/payConfirm")
    public void payConfirm(HttpServletResponse response, Integer reserveId, Integer type, Double money) {

        if (null == reserveId || type == null ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Reserve reserve = reserveService.getById(reserveId);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUsername(reserve.getUser().getNickname());
        order.setStadiumname(reserve.getSite().getStadium().getName());
        order.setPrice(reserve.getPrice());
        order.setSn(sn);
        orderService.create(order);

        if (type == 0) {
            //将球场预订表的支付方式设置为全额支付
            reserve.setPayment(0);
        }

        if (type == 1) {
            //将球场预订表的支付方式设置为AA支付
            reserve.setPayment(1);
        }

        Result obj = new Result(true).data(order);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/teamPay 球队支付订单
     * @apiName stadium.teamPay
     * @apiGroup stadium
     * @apiParam {Integer} siteTimeId 预定场地ID <必传/>
     * @apiParam {Integer} userId 用户ID <必传/>
     * @apiParam {Integer} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     * @apiSuccess {Object}  site 场地
     * @apiSuccess {Object}  sysInsurance 保险
     * @apiSuccess {Integer} sysInsurance.name 保险名称
     * @apiSuccess {String} sysInsurance.price 保险金额
     * @apiSuccess {Integer} siteMoney 场地费
     * @apiSuccess {Object}  vipNum 会员等级
     * @apiSuccess {Integer} preferente 会员折扣
     * @apiSuccess {String} money 总金额
     * @apiSuccess {Object}  reserveTeam 球队预定
     * @apiSuccess {Integer} reserveTeam.id 预定id
     */
    @RequestMapping(value = "/teamPay")
    public void teamPay(HttpServletResponse response,
                        Integer userId,
                        Integer siteTimeId,
                        Integer insuranceId,
                        Integer num,
                        Double preferente,
                        Double money) {

        if (null == userId || siteTimeId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        if (insuranceId == null) {
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
                num = 0;
                money =siteTimeService.getById(siteTimeId).getSite().getPrice() * preferente;
            } else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
                num = 0;
                money = siteTimeService.getById(siteTimeId).getSite().getPrice() * preferente;
            }

            ReserveTeam reserveTeam = new ReserveTeam();

            reserveTeam.setStatus(0);
            reserveTeam.setStadium(siteTimeService.getById(siteTimeId).getSite().getStadium());
            reserveTeam.setSite(siteTimeService.getById(siteTimeId).getSite());
            reserveTeam.setUser(userService.getById(userId));
            reserveTeam.setPrice(money);
            reserveTeamService.create(reserveTeam);

            map.put("reserveTeam", reserveTeam);
        } else {
            SysInsurance sysInsurance = sysInsuranceService.getById(insuranceId);
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
            } else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
            }
            money = (siteTimeService.getById(siteTimeId).getSite().getPrice() + sysInsurance.getPrice() * num) * preferente;

            ReserveTeam reserveTeam = new ReserveTeam();
            reserveTeam.setStatus(0);
            reserveTeam.setStadium(siteTimeService.getById(siteTimeId).getSite().getStadium());
            reserveTeam.setSite(siteTimeService.getById(siteTimeId).getSite());
            reserveTeam.setUser(userService.getById(userId));
            reserveTeam.setInsurance(sysInsurance);
            reserveTeam.setPrice(money);
            reserveTeamService.create(reserveTeam);

            map.put("reserveTeam", reserveTeam);
            map.put("sysInsurance", sysInsurance);
        }

        map.put("preferente", preferente);
        map.put("money", money);
        map.put("siteMoney", siteTimeService.getById(siteTimeId).getSite().getPrice());
        map.put("vipNum", userService.getById(userId).getVipNum());

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/teamPayConfirm 球队确认支付订单
     * @apiName stadium.teamPayConfirm
     * @apiGroup stadium
     * @apiParam {Integer} reserveTeamId 预定ID <必传/>
     * @apiSuccess {Object} order 订单
     * @apiSuccess {String} order.userName 用户昵称
     * @apiSuccess {String} order.stadiumName 球场名称
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/teamPayConfirm")
    public void teamPayConfirm(HttpServletResponse response, Integer reserveTeamId, Double money) {

        if (null == reserveTeamId ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        ReserveTeam reserveTeam = reserveTeamService.getById(reserveTeamId);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUsername(reserveTeam.getUser().getNickname());
        order.setStadiumname(reserveTeam.getSite().getStadium().getName());
        order.setPrice(reserveTeam.getPrice());
        order.setSn(sn);
        orderService.create(order);

        money = order.getPrice();

        Result obj = new Result(true).data(order);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
