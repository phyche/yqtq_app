package com.sixmac.service;

import com.sixmac.entity.Order;
import com.sixmac.entity.Reserve;
import com.sixmac.entity.UserReserve;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/19 0019 上午 11:54.
 */
public interface ReserveService extends ICommonService<Reserve> {

    public List<Reserve> findByUserId(Long userId);

    public Page<Reserve> page(Integer timelimit, Integer type, Long areaId, Integer pageNum, Integer pageSize);

    public List<Reserve> findNew();

    // 约球邀请
    public void order(HttpServletResponse response,
                      Long reserveId,
                      Long userId,
                      Long toUserId);

    // 球友的赛事列表
    public Map<String, Object> raceList(HttpServletResponse response, Long playerId);

    public Order pay(HttpServletResponse response, Long reserveId, Long userId, Long messageId, Double money);

}