package com.sixmac.service;

import com.sixmac.entity.Team;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/18 0018 上午 9:55.
 */
public interface TeamService extends ICommonService<Team> {

    public Page<Team> page(String name, Long cityId, Integer pageNum, Integer pageSize);

    public Team findListByLeaderId(Long leaderId);

    // 球队详情
    public Map<String, Object> info(HttpServletResponse response, Long teamId);

    // 邀请朋友加入球队
    public void addFriend(HttpServletResponse response, Long userId, Long toUserId);

    // 申请加入球队
    public void apply(HttpServletResponse response, Long userId, Long teamId);

    // 约球队详情
    public void orderInfo(HttpServletResponse response,
                          Long team1Id,
                          Long team2Id,
                          Long time,
                          Long cityId);

}