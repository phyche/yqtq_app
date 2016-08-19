package com.sixmac.controller.api;

import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.GirlImageVo;
import com.sixmac.entity.vo.NumVo;
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
     *
     * @apiSuccess {Object}  list 区域列表
     * @apiSuccess {Integer} list.id 区域id
     * @apiSuccess {String} list.areaId 区域名称
     * @apiSuccess {String} list.stadiumList 区域球场
     * @apiSuccess {String} list.stadiumList.id 球场id
     * @apiSuccess {String} list.stadiumList.name 球场名称
     * @apiSuccess {Double} list.stadiumList.distance 球场距离
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
                     Integer type) throws IOException {

        if (null == latitude || longitude == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        List<Stadium> stadiumList = new ArrayList<Stadium>();
        if (type == null) {
            stadiumList = stadiumService.findAll();
        }else {
            List<Site> siteList = siteService.page(type);
            for (Site site : siteList) {
                stadiumList.add(site.getStadium());
            }
            for (int i = 0; i < stadiumList.size(); i++) {
                Stadium stadium = stadiumList.get(i);
                if (stadiumList.contains(stadium)) {
                    stadiumList.remove(i);
                }
            }
        }
        List<Area> areaList = areaService.getByCityId(cityId);
        for (Area area : areaList) {
            for (Stadium stadium : stadiumList) {
                if (stadium.getAreaId().toString().equals(area.getAreaId().toString())) {
                    stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());
                    if (longitude == 0.0 && latitude == 0.0) {
                        stadium.setDistance(-1);
                    } else {
                        stadium.setDistance(CountDistance.gps2m(latitude, longitude, stadium.getLatitude(), stadium.getLongitude()));
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

        /*Iterator<Area> areaIterator = areaList.iterator();
        while (areaIterator.hasNext() && areaId != areaIterator.next().getAreaId()) {
            areaList.remove(areaIterator.next());
        }*/

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
     * @apiSuccess {Object}  stadiumInfo 球场
     * @apiSuccess {Object}  stadiumInfo.stadium 球场详情
     * @apiSuccess {Long} stadiumInfo.stadium.id 球场id
     * @apiSuccess {String} stadiumInfo.stadium.name 球场名称
     * @apiSuccess {String} stadiumInfo.stadium.mobile 球场联系方式
     * @apiSuccess {String} stadiumInfo.stadium.areaName 球场地区名字
     * @apiSuccess {String} stadiumInfo.stadium.address 球场地址
     * @apiSuccess {String} stadiumInfo.stadium.avater 球场封面
     * @apiSuccess {Integer} stadiumInfo.stadium.siteType 场地类型 (0:室内 1:室外)
     * @apiSuccess {String} stadiumInfo.stadium.sodType 草皮类型
     * @apiSuccess {String} stadiumInfo.stadium.light 灯光
     * @apiSuccess {Integer} stadiumInfo.stadium.park 停车场（ 0:无 1:免费 2:收费）
     * @apiSuccess {String} stadiumInfo.stadium.giving 赠送
     * @apiSuccess {String} stadiumInfo.stadium.description 球场简介
     * @apiSuccess {Object} stadiumInfo.time 时间
     * @apiSuccess {String} stadiumInfo.time.week 星期
     * @apiSuccess {String} stadiumInfo.time.date 日期
     * @apiSuccess {Long} stadiumInfo.time.time 具体时间
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response, Long stadiumId) throws ParseException {

        if (null == stadiumId) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Stadium stadium = stadiumService.getById(stadiumId);
        if (stadium.getType() == 0) {
            stadium.setMobile(userService.getById(stadium.getUserId()).getMobile());
        }
        stadium.setAreaName(areaService.getByAreaId(stadium.getAreaId()).getArea());
        if (StringUtils.isNotBlank(stadium.getAvater())) {
            stadium.setAvater(ConfigUtil.getString("upload.url") + stadium.getAvater());
        }

        List<TimeVo> list = new ArrayList<TimeVo>();
        if (stadiumService.getById(stadiumId).getType() == 0) {

            String week = null;
            String date = null;
            Long time = null;


            for (int i = 0; i < 7; i++) {
                TimeVo timeVo = new TimeVo();

                time = DateUtils.dateAddDay(DateUtils.longToDate(System.currentTimeMillis(), "yyyy-MM-dd"), i).getTime();
                date = DateUtils.dateToStringWithFormat(DateUtils.dateAddDay(DateUtils.longToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), i), "MM月dd日");
                week = DateUtils.chinaDayOfWeek(DateUtils.dateAddDay(DateUtils.longToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), i));

                timeVo.setDate(date);
                timeVo.setWeek(week);
                timeVo.setTime(time);

                list.add(timeVo);
            }
        }
        map.put("time", list);

        map.put("stadium", stadium);
        Result obj = new Result(true).data(createMap("stadiumInfo", map));
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
        reserve.setCityId(stadium.getCityId());
        reserve.setPayStatus(1);
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
     *
     * @apiSuccess {Object}  list 场地
     * @apiSuccess {Long}  list.id 场地id
     * @apiSuccess {String}  list.code 场地编号
     * @apiSuccess {Integer} list.type 场地类型  N人制
     * @apiSuccess {String} list.numList 预定字符串
     * @apiSuccess {String} list.numList.status 状态 (0：不可预定 1：可预订)
     */
    @RequestMapping(value = "/siteSelect")
    public void siteSelect(HttpServletResponse response, Long time, Long stadiumId) {

        if (null == stadiumId || time == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        //Map<String, Object> map = new HashMap<String, Object>();
        List<NumVo> list = null;
        NumVo numVo = null;

        // 查询所有可用场地
        List<Site> siteList = siteService.findByStadiumId(stadiumId);
        List<SiteTime> siteTime = null;
        List<SiteManage> siteManage = null;
        for (Site site : siteList) {
            // 根据场地id查询所有被预定的场地的时间
            list = new ArrayList<NumVo>();
            for (int i = 8; i < 24; i+=2) {

                numVo = new NumVo();
                siteTime = siteTimeService.findBySiteAndTime(site.getId(), time + i * 1000 * 3600);
                siteManage = siteManageService.findBySiteAndTime(site.getId(), time + i * 1000 * 3600);
                if (siteTime.size() == 0 && siteManage.size() == 0) {
                    numVo.setStatus(1);
                    list.add(numVo);
                } else {
                    numVo.setStatus(0);
                    list.add(numVo);
                }
            }
            site.setNumList(list);

        }

        //map.put("siteList", siteList);

        Result obj = new Result(true).data(createMap("list", siteList));
        String result = JsonUtil.obj2ApiJson(obj, "stadium");
        WebUtil.printApi(response, result);
    }

    /**
     *
     * @api {post} /api/stadium/insurance 保险
     * @apiName stadium.insurance
     * @apiGroup stadium
     *
     * @apiSuccess {Object}  list 保险
     * @apiSuccess {Long} list.id 预定id
     * @apiSuccess {String} list.name 保险名字
     * @apiSuccess {Double} list.price 保险价格
     *
     */
    @RequestMapping(value = "/insurance")
    public void insurance(HttpServletResponse response) {
        List<SysInsurance> sysInsurances = sysInsuranceService.findList();
        Result obj = new Result(true).data(createMap("list", sysInsurances));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/pay 支付订单
     * @apiName stadium.pay
     * @apiGroup stadium
     * @apiParam {Long} siteId 预定场地ID <必传/>
     * @apiParam {Long} userId 用户ID <必传/>
     * @apiParam {Long} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     * @apiSuccess {Object}  pay.sysInsurance 保险
     * @apiSuccess {Long} pay.sysInsurance.id 保险id
     * @apiSuccess {Integer} pay.sysInsurance.name 保险名称
     * @apiSuccess {String} pay.sysInsurance.price 保险金额
     * @apiSuccess {Integer} pay.siteMoney 场地费
     * @apiSuccess {Object}  pay.vipNum 会员等级
     * @apiSuccess {Integer} pay.preferente 会员折扣
     * @apiSuccess {String} pay.money 总金额
     *
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Long userId,
                    Long siteId,
                    Long insuranceId,
                    Integer num,
                    Double preferente,
                    Double money) {

        if (null == userId || siteId == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        preferente = 10.0;
        if (userService.getById(userId).getVipNum() != 0) {
            preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
        }

        SysInsurance sysInsurance = null;
        if (insuranceId == null) {
            sysInsurance = new SysInsurance();
            money = siteService.getById(siteId).getPrice() * preferente / 10;

        } else {
            sysInsurance = sysInsuranceService.getById(insuranceId);
            money = (siteService.getById(siteId).getPrice() + sysInsurance.getPrice() * num) * preferente / 10;
        }

        map.put("preferente", preferente);
        map.put("money", money);
        map.put("siteMoney", siteService.getById(siteId).getPrice());
        map.put("vipNum", userService.getById(userId).getVipNum());
        map.put("sysInsurance", sysInsurance);

        Result obj = new Result(true).data(createMap("pay", map));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 完成
     *
     * @api {post} /api/stadium/payConfirm 确认支付订单
     * @apiName stadium.payConfirm
     * @apiGroup stadium
     * @apiParam {Long} siteId 预定场地ID <必传/>
     * @apiParam {Long} userId 用户ID <必传/>
     * @apiParam {Long} time 时间戳  <必传/>
     * @apiParam {Integer} start 开始时间点  <必传/>
     * @apiParam {Integer} end 结束时间点  <必传/>
     * @apiParam {Integer} type 支付类型（0：全额支付  1：AA支付） <必传/>
     * @apiParam {Double} price 金额 <必传/>
     * @apiParam {Long} insuranceId 保险ID
     * @apiParam {Integer} status 状态（0：散客  1：球队） <必传/>
     * @apiSuccess {Object} payInfo 订单
     * @apiSuccess {Double} payInfo.price 订单金额
     * @apiSuccess {Long} payInfo.sn 订单号
     */
    @RequestMapping(value = "/payConfirm")
    public void payConfirm(HttpServletResponse response,
                           Long userId,
                           Long siteId,
                           Integer start,
                           Long time,
                           Integer end,
                           Integer type,
                           Long insuranceId,
                           Integer status,
                           Double price) {

        if (status == null || null == userId || siteId == null || time == null || start == null || end == null || type == null || price == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        String sn = CommonUtils.generateSn(); // 订单号

        Site site = siteService.getById(siteId);
        SiteTime siteTime = new SiteTime();
        siteTime.setSite(site);
        siteTime.setStartTime(time + start * 1000 * 3600);
        siteTime.setEndTime(time + end * 1000 * 3600);
        siteTimeService.create(siteTime);

        Reserve reserve = null;
        ReserveTeam reserveTeam = null;
        if (status == 0) {
            reserve = new Reserve();
            reserve.setStadium(siteService.getById(siteId).getStadium());
            reserve.setSiteId(siteId);
            reserve.setUser(userService.getById(userId));
            if (insuranceId != null) {
                reserve.setInsurance(sysInsuranceService.getById(insuranceId));
            }
            if (type == 0) {
                //将球场预订表的支付方式设置为全额支付
                reserve.setPayment(0);
            } else if (type == 1) {
                //将球场预订表的支付方式设置为AA支付
                reserve.setPayment(1);
            }
            reserve.setPrice(price);
            reserve.setMatchType(siteService.getById(siteId).getType());
            reserve.setStartTime(siteTime.getStartTime());
            reserve.setPayStatus(0);
            reserve.setCityId(siteService.getById(siteId).getStadium().getCityId());
            reserve.setType(0);
            reserveService.create(reserve);
        }else if (status == 1) {
            reserveTeam = new ReserveTeam();
            reserveTeam.setStatus(0);
            reserveTeam.setSite(site);
            reserveTeam.setUser(userService.getById(userId));
            reserveTeam.setStartTime(siteTime.getStartTime());
            if (insuranceId != null) {
                reserveTeam.setInsurance(sysInsuranceService.getById(insuranceId));
            }
            reserveTeam.setPrice(price);
            reserveTeamService.create(reserveTeam);
        }

        Order order = new Order();
        order.setUser(userService.getById(userId));
        //order.setStadium(reserve.getSite().getStadium());
        order.setReserve(reserve);
        order.setReserveTeam(reserveTeam);
        order.setPrice(price);
        order.setAction(2);
        order.setSn(sn);
        orderService.create(order);

        // 当前没有支付接口，因此状态直接为已支付
        PayCallBackApi.changeOrderStatus(orderService, order.getSn(), null, response);

        Result obj = new Result(true).data(createMap("payInfo", order));
        String result = JsonUtil.obj2ApiJson(obj, "reserve", "user", "reserveTeam", "girlUser", "stadium");
        WebUtil.printApi(response, result);
    }
}
