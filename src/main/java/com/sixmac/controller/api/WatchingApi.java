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
     * 直播看球列表（完成）
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
     * 直播看球详情（完成）
     */
    @RequestMapping(value = "/telecastInfo")
    public void telecastInfo(HttpServletResponse response, Integer telecastId) {

        WatchingRace watchingRace = watchingRaceService.getById(telecastId);

        Result obj = new Result(true).data(watchingRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 现场看球列表（完成）
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
     * 现场看球详情（完成）
     */
    @RequestMapping(value = "/sceneInfo")
    public void sceneInfo(HttpServletResponse response, Integer sceneId) {

        BigRace bigRace = bigRaceService.getById(sceneId);

        Result obj = new Result(true).data(bigRace);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 宝贝列表
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
     * 宝贝个人信息
     */
    @RequestMapping(value = "/girlInfo")
    public void girlInfo(HttpServletResponse response, Integer girlId) {
        Map<String,Object> map = new HashMap<String,Object>();
        //宝贝个人信息
        Girl girl = girlService.getById(girlId);

        //宝贝相册
        List<GirlImage> girlImages = girlImageService.findByGirlId(girlId);

        map.put("girl",girl);
        map.put("girlImages",girlImages);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"girl");
        WebUtil.printApi(response, result);
    }

    /**
     * 宝贝陪看评价(完成)
     */
    @RequestMapping(value = "/girlComment")
    public void girlComment(HttpServletResponse response, Integer girlId) {

        List<GirlComment> girlCommentList = girlCommentService.findByGirlId(girlId);

        Result obj = new Result(true).data(girlCommentList);
        String result = JsonUtil.obj2ApiJson(obj,"girl","user");
        WebUtil.printApi(response, result);
    }

    /**
     * 约宝贝 看球确认（完成）
     */
    @RequestMapping(value = "/orderGirl")
    public void orderGirl(HttpServletResponse response,
                          Integer userId,
                          Integer girlId,
                          Integer sceneId,
                          Double tip) {

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
        girlUser.setTip(tip);
        //约宝贝时间 暂无
        girlUser.setDuration(2);
        girlUser.setPrice(girl.getPrice() * girlUser.getDuration() + tip);
        girlUserService.create(girlUser);

        map.put("girl",girl);
        //map.put("bigRace",bigRace);
        map.put("girlUser",girlUser);

        Result obj = new Result(true).data(map);
        String result = JsonUtil.obj2ApiJson(obj,"user");
        WebUtil.printApi(response, result);
    }

    /**
     * 支付（完成）
     */
    @RequestMapping(value = "/pay")
    public void pay(HttpServletResponse response,
                    Integer girlUserId,
                    Integer userId,
                    Double money) {

        GirlUser girlUser = girlUserService.getById(girlUserId);

        String sn = CommonUtils.generateSn(); // 订单号

        Order order = new Order();
        order.setUsername(girlUser.getUser().getNickname());
        order.setStadiumname(girlUser.getStadium().getName());
        order.setPrice(girlUser.getPrice());
        order.setSn(sn);
        orderService.create(order);

        money = order.getPrice();

        Result obj = new Result(true).data(money);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}
