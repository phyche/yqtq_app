package com.sixmac.service;

import com.sixmac.entity.Order;
import com.sixmac.service.common.ICommonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24 0024 下午 5:54.
 */
public interface OrderService extends ICommonService<Order> {

    // 根据订单流水号获取微信支付信息
    public Map<String, Object> getPayInfo(HttpServletRequest request, HttpServletResponse response, String orderNum);

    // 根据订单流水号获取订单详情
    public Order iFindOneByOrderNum(String orderNum);
}