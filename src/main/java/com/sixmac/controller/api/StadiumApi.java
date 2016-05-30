package com.sixmac.controller.api;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.StadiumVo;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 完成
     *
     * @api {post} /api/stadium/list 球场列表
     * @apiName stadium.list
     * @apiGroup stadium
     * @apiParam {Integer} type 球赛类型 N人制 N代表数量
     * @apiParam {Integer} areaId 区域ID
     * @apiParam {Integer} pageNum 当前页
     * @apiParam {Integer} pageSize 每页显示数
     *
     * @apiSuccess {Object}  list 球场列表
     * @apiSuccess {Integer} list.id 球场id
     * @apiSuccess {String} list.name 球场名称
     * @apiSuccess {Integer} list.type 球场类型 （0:私人球场 1:公共球场）
     * @apiSuccess {Integer} list.cityId 球场地区
     * @apiSuccess {String} list.address 球场地址
     * @apiSuccess {String} list.avater 球场封面
     *
     * @apiSuccess {Object}  page 翻页信息
     * @apiSuccess {Integer} page.totalNum 总记录数
     * @apiSuccess {Integer} page.totalPage 总页数
     * @apiSuccess {Integer} page.currentPage 当前页
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse response,
                     Integer areaId,
                     Integer type,
                     Integer pageNum,
                     Integer pageSize){

        Page<Stadium> page = stadiumService.page(areaId, type, pageNum, pageSize);

        Map<String, Object> dataMap = APIFactory.fitting(page);
        String result = JsonUtil.obj2ApiJson(dataMap,"description","siteType","sodType");
        WebUtil.printApi(response, result);
    }

    /**
     * 公共球场完成，私人球场场次预定缺表，缺字段
     *
     * @api {post} /api/stadium/info 球场详情
     * @apiName stadium.info
     * @apiGroup stadium
     * @apiParam {Integer} stadiumId 球场ID  <必传/>
     *
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Integer} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {Integer} stadium.cityId 球场地区
     * @apiSuccess {String} stadium.address 球场地址
     * @apiSuccess {String} stadium.avater 球场封面
     * @apiSuccess {String} stadium.siteType 场地类型
     * @apiSuccess {String} stadium.sodType 草皮类型
     * @apiSuccess {Integer} stadium.light 灯光(0：有灯光 1：没有灯光)
     * @apiSuccess {Integer} stadium.park 停车场（0：有停车场 1；没有停车场）
     * @apiSuccess {String} stadium.giving 赠送
     * @apiSuccess {String} stadium.description 球场简介
     *
     * @apiSuccess {Object} sites 球场场地
     *
     *
     */
    @RequestMapping(value = "/info")
    public void info(HttpServletResponse response,Integer stadiumId) {

        Map<String, Object> map = new HashMap<String, Object>();

        Stadium stadium = stadiumService.getById(stadiumId);

        if(stadiumService.getById(stadiumId).getType() == 1) {
            //私有球场的可预订场地列表
            List<Site> sites = siteService.findByStadiumId(stadiumId);
            map.put("sites",sites);
        }

        map.put("stadium",stadium);
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
     *
     * @apiSuccess {Object}  stadium 球场
     * @apiSuccess {Integer} stadium.id 球场id
     * @apiSuccess {String} stadium.name 球场名称
     * @apiSuccess {Integer} stadium.cityId 球场地区
     * @apiSuccess {String} stadium.avater 球场封面
     *
     */
    @RequestMapping(value = "/order")
    public void order(HttpServletResponse response, Integer stadiumId) {

        Stadium stadium = stadiumService.getById(stadiumId);

        Result obj = new Result(true).data(stadium);
        String result = JsonUtil.obj2ApiJson(obj,"type","description","siteType","sodType","light","park","giving");
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

        User user = userService.getById(userId);
        Stadium stadium = stadiumService.getById(stadiumId);

        Reserve reserve = new Reserve();
        reserve.setUser(user);
        reserve.setStadium(stadium);
        reserve.setStartTime(time);
        reserveService.create(reserve);

        UserReserve userReserve = new UserReserve();
        userReserve.setUser(user);
        userReserve.setReserve(reserve);
        userReserve.setStatus(1);
        userReserve.setTitle(title);
        userReserveService.create(userReserve);

        WebUtil.printApi(response, new Result(true));
    }

    /**
     * 场次选择
     */

    /**
     * 完成,缺场地编号时间等，同私人球场详情
     *
     * @api {post} /api/stadium/siteOrder 场地预订
     * @apiName stadium.siteOrder
     * @apiGroup stadium
     * @apiParam {Integer} siteId 场地ID <必传/>
     * @apiParam {Integer} status 运动保险状态（0：不买  1：买） <必传/>
     *
     * @apiSuccess {Object}  site 场地
     *
     * @apiSuccess {Object}  stadiumVo 场地
     * @apiSuccess {Integer} stadiumVo.id 球场id
     * @apiSuccess {String} stadiumVo.name 球场名称
     * @apiSuccess {Integer} stadiumVo.cityId 球场地区
     *
     */
    @RequestMapping(value = "/siteOrder")
    public void siteOrder(HttpServletResponse response, Integer siteId, Integer status) {

        Map<String,Object> map = new HashMap<String,Object>();

        Site site = siteService.getById(siteId);

        //status == 1 代表顾客要买保险
        if (status == 1) {
            List<SysInsurance> sysInsurances = sysInsuranceService.findAll();
            map.put("sysInsurances",sysInsurances);
        }

        StadiumVo stadiumVo = new StadiumVo();
        stadiumVo.setId(site.getStadium().getId());
        stadiumVo.setName(site.getStadium().getName());
        stadiumVo.setCityId(site.getStadium().getCityId());

        map.put("site",site);
        map.put("stadiumVo",stadiumVo);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"stadium");
        WebUtil.printApi(response, result);
    }

    /**
     *完成
     *
     * @api {post} /api/stadium/pay 散客支付订单
     * @apiName stadium.pay
     * @apiGroup stadium
     * @apiParam {Integer} siteId 场地ID <必传/>
     * @apiParam {Integer} userId 用户ID（0：不买  1：买） <必传/>
     * @apiParam {Integer} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     *
     * @apiSuccess {Object}  site 场地
     *
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
     *
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Integer userId,
                    Integer siteId,
                    Integer insuranceId,
                    Integer num,
                    Double preferente,
                    Double money) {

        Map<String,Object> map = new HashMap<String,Object>();

        if (insuranceId == null) {
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
                num = 0;
                money = siteService.getById(siteId).getPrice() * preferente;
            }else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
                num = 0;
                money = siteService.getById(siteId).getPrice() * preferente;
            }

            Reserve reserve = new Reserve();
            reserve.setStadium(siteService.getById(siteId).getStadium());
            reserve.setSite(siteService.getById(siteId));
            reserve.setUser(userService.getById(userId));
            reserve.setPrice(money);
            reserveService.create(reserve);
            map.put("reserve",reserve);

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(userService.getById(userId));
            userReserve.setReserve(reserve);
            userReserve.setStatus(1);
            userReserveService.create(userReserve);

        }else {
            SysInsurance sysInsurance = sysInsuranceService.getById(insuranceId);
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
            }else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
            }

            money = (siteService.getById(siteId).getPrice() + sysInsurance.getPrice() * num) * preferente;

            Reserve reserve = new Reserve();
            reserve.setStadium(siteService.getById(siteId).getStadium());
            reserve.setSite(siteService.getById(siteId));
            reserve.setUser(userService.getById(userId));
            reserve.setInsurance(sysInsurance);
            reserve.setPrice(money);
            reserveService.create(reserve);
            map.put("reserve",reserve);

            UserReserve userReserve = new UserReserve();
            userReserve.setUser(userService.getById(userId));
            userReserve.setReserve(reserve);
            userReserve.setStatus(1);
            userReserveService.create(userReserve);

            map.put("sysInsurance",sysInsurance);
        }

        map.put("preferente",preferente);
        map.put("money",money);
        map.put("siteMoney",siteService.getById(siteId).getPrice());
        map.put("vipNum",userService.getById(userId).getVipNum());

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
     *
     * @apiSuccess {Object} order 订单
     * @apiSuccess {String} order.userName 用户昵称
     * @apiSuccess {String} order.stadiumName 球场名称
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/payConfirm")
    public void payConfirm(HttpServletResponse response, Integer reserveId, Integer type, Double money) {

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
     * @apiParam {Integer} siteId 场地ID <必传/>
     * @apiParam {Integer} userId 用户ID（0：不买  1：买） <必传/>
     * @apiParam {Integer} insuranceId 保险ID
     * @apiParam {Integer} num 购买保险数
     *
     * @apiSuccess {Object}  site 场地
     *
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
                    Integer siteId,
                    Integer insuranceId,
                    Integer num,
                    Double preferente,
                    Double money) {

        Map<String,Object> map = new HashMap<String,Object>();

        if (insuranceId == null) {
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
                num = 0;
                money = siteService.getById(siteId).getPrice() * preferente;
            }else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
                num = 0;
                money = siteService.getById(siteId).getPrice() * preferente;
            }

            ReserveTeam reserveTeam = new ReserveTeam();
            reserveTeam.setSite(siteService.getById(siteId));
            reserveTeam.setUser(userService.getById(userId));
            reserveTeam.setPrice(money);
            reserveTeamService.create(reserveTeam);

            map.put("reserveTeam",reserveTeam);
        }else {
            SysInsurance sysInsurance = sysInsuranceService.getById(insuranceId);
            if (userService.getById(userId).getVipNum() == 0) {
                preferente = 1.0;
            }else {
                preferente = vipLevelService.findBylevel(userService.getById(userId).getVipNum()).getPreferente();
            }
            money = (siteService.getById(siteId).getPrice() + sysInsurance.getPrice() * num) * preferente;

            ReserveTeam reserveTeam = new ReserveTeam();
            reserveTeam.setSite(siteService.getById(siteId));
            reserveTeam.setUser(userService.getById(userId));
            reserveTeam.setInsurance(sysInsurance);
            reserveTeam.setPrice(money);
            reserveTeamService.create(reserveTeam);

            map.put("reserveTeam",reserveTeam);
            map.put("sysInsurance",sysInsurance);
        }

        map.put("preferente",preferente);
        map.put("money",money);
        map.put("siteMoney",siteService.getById(siteId).getPrice());
        map.put("vipNum",userService.getById(userId).getVipNum());

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
     *
     * @apiSuccess {Object} order 订单
     * @apiSuccess {String} order.userName 用户昵称
     * @apiSuccess {String} order.stadiumName 球场名称
     * @apiSuccess {Double} order.price 订单金额
     * @apiSuccess {Long} order.sn 订单号
     */
    @RequestMapping(value = "/teamPayConfirm")
    public void teamPayConfirm(HttpServletResponse response, Integer reserveTeamId, Double money) {

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
