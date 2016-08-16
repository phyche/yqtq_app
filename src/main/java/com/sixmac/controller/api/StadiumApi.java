package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.TimeVo;
import com.sixmac.service.*;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private InsuranceService insuranceService;


    /**
     * 完成
     *
     * @api {post} /api/stadium/list 球场列表
     * @apiName stadium.list
     * @apiGroup stadium
     * @apiParam {Integer} type 球赛类型 N人制 N代表数量
     * @apiParam {Double} longitude 经度  <必传/>
     * @apiParam {Double} latitude 纬度  <必传/>
     * @apiParam {Long} cityId 城市ID
     * @apiParam {Long} areaId 区域ID
     *
     * @apiSuccess {Object}  list 区域列表
     * @apiSuccess {Integer} list.id 区域id
     * @apiSuccess {String} list.areaId 区域名称
     * @apiSuccess {String} list.stadiumList 区域球场
     * @apiSuccess {String} list.stadiumList.id 球场id
     * @apiSuccess {String} list.stadiumList.name 球场名称
     * @apiSuccess {Integer} list.stadiumList.type 球场类型 （0:私人球场 1:公共球场）
     * @apiSuccess {Integer} list.stadiumList.park 是否有停车场 (0:无 1:免费 2:收费)
     * @apiSuccess {String} list.stadiumList.light 灯光类型
     * @apiSuccess {String} list.stadiumList.giving 赠送
     * @apiSuccess {String} list.stadiumList.areaName 球场地区名字
     * @apiSuccess {String} list.stadiumList.address 球场地址
     * @apiSuccess {String} list.stadiumList.avater 球场封面
     *
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     Long cityId,
                     Double longitude,
                     Double latitude,
                     Long areaId,
                     Integer type) throws IOException {

        if (null == latitude || longitude == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Page<Stadium> page = stadiumService.page(areaId, type, 1, 100);
        List<Stadium> stadiumList = page.getContent();
        List<Area> areaList = areaService.getByCityId(cityId);
        for (Area area : areaList) {
            for (Stadium stadium : stadiumList) {
                if (stadium.getAreaId().toString().equals(area.getAreaId().toString())) {
                    stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());
                    if (longitude == 0.0 && latitude == 0.0) {
                        stadium.setDistance(-1);
                    } else {
                        stadium.setDistance(CountDistance.GetDistance(longitude, latitude, stadium.getLongitude(), stadium.getLatitude()));
                    }
                    if (stadium.getAvater() != null) {
                        if (StringUtils.isNotBlank(stadium.getAvater())) {
                            stadium.setAvater(ConfigUtil.getString("upload.url") + stadium.getAvater());
                        }
                    }
                    area.getStadiumList().add(stadium);
                }
            }
        }
        /*List<Stadium> stadiumList = page.getContent();
        for (Stadium stadium : stadiumList) {

            stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());
            if (longitude == 0.0 && latitude == 0.0) {
                stadium.setDistance(-1);
            } else {
                stadium.setDistance(CountDistance.GetDistance(longitude, latitude, stadium.getLongitude(), stadium.getLatitude()));
            }
            if (stadium.getAvater() != null) {
                if (StringUtils.isNotBlank(stadium.getAvater())) {
                    stadium.setAvater(ConfigUtil.getString("upload.url") + stadium.getAvater());
                }
            }
        }*/

        //Map<String, Object> dataMap = APIFactory.fitting(page);
        Result obj = new Result(true).data(createMap("list", areaList));
        String result = JsonUtil.obj2ApiJson(obj, "description", "siteType", "sodType");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/info 球场详情
     * @apiName stadium.info
     * @apiGroup stadium
     * @apiParam {Long} stadiumId 球场ID  <必传/>
     * @apiSuccess {Object}  info.stadium 球场
     * @apiSuccess {Long} info.stadium.id 球场id
     * @apiSuccess {String} info.stadium.name 球场名称
     * @apiSuccess {String} info.stadium.areaName 球场地区名字
     * @apiSuccess {String} info.stadium.address 球场地址
     * @apiSuccess {String} info.stadium.avater 球场封面
     * @apiSuccess {String} info.stadium.siteType 场地类型
     * @apiSuccess {String} info.stadium.sodType 草皮类型
     * @apiSuccess {String} info.stadium.light 灯光
     * @apiSuccess {Integer} info.stadium.park 停车场（0：有停车场 1；没有停车场）
     * @apiSuccess {String} info.stadium.giving 赠送
     * @apiSuccess {String} info.stadium.description 球场简介
     * @apiSuccess {Object} info.time 时间
     * @apiSuccess {String} info.time.week 星期
     * @apiSuccess {String} info.time.date 日期
     * @apiSuccess {Long} info.time.time 具体时间
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Long stadiumId) throws ParseException {

        if (null == stadiumId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Stadium stadium = stadiumService.getById(stadiumId);
        stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());
        if (StringUtils.isNotBlank(stadium.getAvater())) {
            stadium.setAvater(ConfigUtil.getString("upload.url") + stadium.getAvater());
        }

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
            map.put("time", list);

        }

        map.put("stadium", stadium);
        Result obj = new Result(true).data(createMap("info", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/order 公共球场约散客
     * @apiName stadium.order
     * @apiGroup stadium
     * @apiParam {Long} stadiumId 球场ID <必传/>
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Long} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {String} stadium.areaName 球场地区名字
     * @apiSuccess {String} stadium.avater 球场封面
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response, Long stadiumId) {

        if (null == stadiumId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Stadium stadium = stadiumService.getById(stadiumId);
        stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());
        if (StringUtils.isNotBlank(stadium.getAvater())) {
            stadium.setAvater(ConfigUtil.getString("upload.url") + stadium.getAvater());
        }

        Result obj = new Result(true).data(createMap("stadium", stadium));
        String result = JsonUtil.obj2ApiJson(obj, "type", "description", "siteType", "sodType", "light", "park", "giving");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/publish 公共球场发布
     * @apiName stadium.publish
     * @apiGroup stadium
     * @apiParam {Long} stadiumId 球场ID <必传/>
     * @apiParam {Long} userId 用户ID <必传/>
     * @apiParam {String} title 标题  <必传/>
     * @apiParam {Long} time 约球时间 <必传/>
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Long} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {Long} stadium.cityId 球场地区
     * @apiSuccess {String} stadium.avater 球场封面
     */
    @RequestMapping(value = "/publish")
    public void publish(HttpServletResponse response,
                        Long userId,
                        Long stadiumId,
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
        reserve.setType(1);
        reserveService.create(reserve);

        UserReserve userReserve = new UserReserve();
        userReserve.setUser(user);
        userReserve.setReserveId(reserve.getId());
        //userReserve.setReserve(reserve);
        userReserve.setStatus(1);

        userReserveService.create(userReserve);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * @api {post} /api/stadium/siteSelect 场次选择
     * @apiName stadium.siteSelect
     * @apiGroup stadium
     * @apiParam {Long} stadiumId 球场ID <必传/>
     * @apiParam {Long} time 当天时间戳 <必传/>
     * @apiSuccess {Object}  site 场地
     * @apiSuccess {String}  site.code 场地编号
     * @apiSuccess {Integer} site.type 场地类型  N人制
     * @apiSuccess {String} site.numList 预定字符串 (0：不可预定 1：可预订)
     */
    @RequestMapping(value = "/siteSelect")
    public void siteSelect(HttpServletResponse response, Long time, Long stadiumId) {

        if (null == stadiumId || time == null) {
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

                SiteTime siteTime = siteTimeService.findBySiteAndTime(site.getId(), time + i * 1000 * 3600);
                SiteManage siteManage = siteManageService.findBySiteAndTime(site.getId(), time + i * 1000 * 3600);
                if (siteTime == null && siteManage == null) {
                    list.add("1");
                } else {
                    list.add("0");
                }
            }
            site.setNumList(list);

        }

        map.put("siteList", siteList);

        Result obj = new Result(true).data(createMap("site", map));
        String result = JsonUtil.obj2ApiJson(obj, "stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成,缺场地编号时间等，同私人球场详情
     *
     * @api {post} /api/stadium/siteOrder 场地预订
     * @apiName stadium.siteOrder
     * @apiGroup stadium
     * @apiParam {Long} siteId 场地ID <必传/>
     * @apiParam {Integer} status 运动保险状态（0：不买  1：买） <必传/>
     * @apiParam {Long} time 时间戳  <必传/>
     * @apiParam {Integer} start 开始时间点  <必传/>
     * @apiParam {Integer} end 结束时间点  <必传/>
     * @apiSuccess {Object}  siteIfo.siteTime 场地预定
     * @apiSuccess {Long} siteIfo.siteTime.id 预定id
     * @apiSuccess {Object} siteIfo.siteTime.site 预定场地
     * @apiSuccess {String} siteIfo.siteTime.site.code 场地编号
     * @apiSuccess {String} siteIfo.siteTime.site.type 场地类型
     * @apiSuccess {Long} siteIfo.siteTime.startTime 开始时间
     * @apiSuccess {Long} siteIfo.siteTime.endTime 结束时间
     * @apiSuccess {Object} siteIfo.siteTime.site.stadium 预定球场
     * @apiSuccess {String} siteIfo.siteTime.site.stadium.name 预定球场名字
     * @apiSuccess {String} siteIfo.area 预定球场地区
     */
    @RequestMapping(value = "/siteOrder")
    public void siteOrder(HttpServletResponse response,
                          Long siteId,
                          Integer status,
                          Long time,
                          Integer start,
                          Integer end) {

        if (null == siteId || status == null || time == null || start == null || end == null) {
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
        map.put("area", area);
        map.put("siteTime", siteTime);

        Result obj = new Result(true).data(createMap("siteIfo", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/pay 散客支付订单
     * @apiName stadium.pay
     * @apiGroup stadium
     * @apiParam {Long} siteTimeId 预定场地ID <必传/>
     * @apiParam {Long} userId 用户ID <必传/>
     * @apiParam {Long} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     * @apiSuccess {Object}  payInfo.sysInsurance 保险
     * @apiSuccess {Long} payInfo.sysInsurance.id 保险id
     * @apiSuccess {Integer} payInfo.sysInsurance.name 保险名称
     * @apiSuccess {String} payInfo.sysInsurance.price 保险金额
     * @apiSuccess {Integer} payInfo.siteMoney 场地费
     * @apiSuccess {Object}  payInfo.vipNum 会员等级
     * @apiSuccess {Integer} payInfo.preferente 会员折扣
     * @apiSuccess {String} payInfo.money 总金额
     * @apiSuccess {Object}  payInfo.reserve 散客预定（约球）
     * @apiSuccess {Long} payInfo.reserve.id 预定id
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Long userId,
                    Long siteTimeId,
                    Long insuranceId,
                    Integer num,
                    Double preferente,
                    Double money) {

        if (null == userId || siteTimeId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        preferente = 1.0;
        if (userService.getById(userId).getVipNum() != 0) {
            preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
        }

        SysInsurance sysInsurance = null;
        if (insuranceId == null) {
            money = siteTimeService.getById(siteTimeId).getSite().getPrice() * preferente;

        } else {
            sysInsurance = sysInsuranceService.getById(insuranceId);
            money = (siteTimeService.getById(siteTimeId).getSite().getPrice() + sysInsurance.getPrice() * num) * preferente;

            /*Insurance insurance = new Insurance();
            insurance.setUser(userService.getById(userId));
            insurance.setReserve(reserve);
            insurance.setSysInsurance(sysInsurance);
            insurance.setMoney(sysInsurance.getPrice() * num * preferente);
            insuranceService.create(insurance);*/
        }

        Reserve reserve = new Reserve();
        reserve.setStadium(siteTimeService.getById(siteTimeId).getSite().getStadium());
        reserve.setSiteId(siteTimeService.getById(siteTimeId).getSite().getId());
        reserve.setUser(userService.getById(userId));
        reserve.setInsurance(sysInsurance);
        reserve.setPrice(money);
        reserve.setMatchType(siteTimeService.getById(siteTimeId).getSite().getType());
        reserve.setStartTime(siteTimeService.getById(siteTimeId).getStartTime());
        reserve.setPayStatus(0);
        reserve.setCityId(siteTimeService.getById(siteTimeId).getSite().getStadium().getCityId());
        reserve.setType(0);
        reserveService.create(reserve);

        map.put("reserve", reserve);
        map.put("preferente", preferente);
        map.put("money", money);
        map.put("siteMoney", siteTimeService.getById(siteTimeId).getSite().getPrice());
        map.put("vipNum", userService.getById(userId).getVipNum());
        map.put("sysInsurance", sysInsurance);

        Result obj = new Result(true).data(createMap("payInfo", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/payConfirm 散客确认支付订单
     * @apiName stadium.payConfirm
     * @apiGroup stadium
     * @apiParam {Long} reserveId 预定ID <必传/>
     * @apiParam {Integer} type 支付类型（0：全额支付  1：AA支付） <必传/>
     * @apiSuccess {Object} order 订单
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/payConfirm")
    public void payConfirm(HttpServletResponse response, Long reserveId, Integer type, Double money) {

        if (null == reserveId || type == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Reserve reserve = reserveService.getById(reserveId);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUser(reserve.getUser());
        //order.setStadium(reserve.getSite().getStadium());
        order.setReserve(reserve);
        order.setPrice(reserve.getPrice());
        order.setAction(2);
        order.setSn(sn);
        orderService.create(order);

        if (type == 0) {
            //将球场预订表的支付方式设置为全额支付
            reserve.setPayment(0);
        } else if (type == 1) {
            //将球场预订表的支付方式设置为AA支付
            reserve.setPayment(1);
        }

        // 当前没有支付接口，因此状态直接为已支付
        PayCallBackApi.changeOrderStatus(orderService, order.getSn(), null, response);

        Result obj = new Result(true).data(createMap("order", order));
        String result = JsonUtil.obj2ApiJson(obj, "reserve", "user", "reserveTeam", "girlUser", "stadium");
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/teamPay 球队支付订单
     * @apiName stadium.teamPay
     * @apiGroup stadium
     * @apiParam {Long} siteTimeId 预定场地ID <必传/>
     * @apiParam {Long} userId 用户ID <必传/>
     * @apiParam {Long} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     * @apiSuccess {Object}  payInfo.sysInsurance 保险
     * @apiSuccess {String} payInfo.sysInsurance.name 保险名称
     * @apiSuccess {Double} payInfo.sysInsurance.price 保险金额
     * @apiSuccess {Double} payInfo.siteMoney 场地费
     * @apiSuccess {Integer}  payInfo.vipNum 会员等级
     * @apiSuccess {Double} payInfo.preferente 会员折扣
     * @apiSuccess {Double} payInfo.money 总金额
     * @apiSuccess {Object}  payInfo.reserveTeam 球队预定
     * @apiSuccess {Long} payInfo.reserveTeam.id 预定id
     */
    @RequestMapping(value = "/teamPay")
    public void teamPay(HttpServletResponse response,
                        Long userId,
                        Long siteTimeId,
                        Long insuranceId,
                        Integer num,
                        Double preferente,
                        Double money) {

        if (null == userId || siteTimeId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        preferente = 1.0;
        if (userService.getById(userId).getVipNum() != 0) {
            preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
        }
        SysInsurance sysInsurance = null;
        if (insuranceId == null) {
            money = siteTimeService.getById(siteTimeId).getSite().getPrice() * preferente;
        } else {
            sysInsurance = sysInsuranceService.getById(insuranceId);
            money = (siteTimeService.getById(siteTimeId).getSite().getPrice() + sysInsurance.getPrice() * num) * preferente;

            /*Insurance insurance = new Insurance();
            insurance.setUser(userService.getById(userId));
            insurance.setReserveTeam(reserveTeam);
            insurance.setSysInsurance(sysInsurance);
            insurance.setMoney(sysInsurance.getPrice() * num * preferente);
            insuranceService.create(insurance);*/

        }

        ReserveTeam reserveTeam = new ReserveTeam();
        reserveTeam.setStatus(0);
        //reserveTeam.setStadium(siteTimeService.getById(siteTimeId).getSite().getStadium());
        reserveTeam.setSite(siteTimeService.getById(siteTimeId).getSite());
        reserveTeam.setUser(userService.getById(userId));
        reserveTeam.setStartTime(siteTimeService.getById(siteTimeId).getStartTime());
        reserveTeam.setPrice(money);
        reserveTeamService.create(reserveTeam);

        map.put("reserveTeam", reserveTeam);
        map.put("preferente", preferente);
        map.put("money", money);
        map.put("siteMoney", siteTimeService.getById(siteTimeId).getSite().getPrice());
        map.put("vipNum", userService.getById(userId).getVipNum());
        map.put("sysInsurance", sysInsurance);

        Result obj = new Result(true).data(createMap("payInfo", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/teamPayConfirm 球队确认支付订单
     * @apiName stadium.teamPayConfirm
     * @apiGroup stadium
     * @apiParam {Long} reserveTeamId 预定ID <必传/>
     * @apiSuccess {Object} order 订单
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/teamPayConfirm")
    public void teamPayConfirm(HttpServletResponse response, Long reserveTeamId) {

        if (null == reserveTeamId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        ReserveTeam reserveTeam = reserveTeamService.getById(reserveTeamId);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUser(reserveTeam.getUser());
        //order.setStadium(reserveTeam.getSite().getStadium());
        order.setPrice(reserveTeam.getPrice());
        order.setSn(sn);
        order.setAction(2);
        order.setReserveTeam(reserveTeam);
        orderService.create(order);

        // 当前没有支付接口，因此状态直接为已支付
        PayCallBackApi.changeOrderStatus(orderService, order.getSn(), null, response);

        Result obj = new Result(true).data(createMap("order", order));
        String result = JsonUtil.obj2ApiJson(obj, "reserve", "user", "reserveTeam", "girlUser", "stadium");
        WebUtil.printApi(response, result);
    }
}
