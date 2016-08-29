package com.sixmac.service;

import com.sixmac.entity.User;
import com.sixmac.entity.UserReserve;
import com.sixmac.service.common.ICommonService;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/17 0017 下午 4:51.
 */
public interface UserService extends ICommonService<User> {

    //根据手机号（账号）查询用户
    public User findByMobile(String mobile);

    // 移动端用户第三方登录
    public User iTLogin(Integer type, String openId, String head, String nickname);

    // 用户获取经验
    public void changeIntegral(User userInfo);

    // 添加好友
    public void addFriend(HttpServletResponse response, Long userId, String mobile);

    // 用户个人首页
    public Map<String, Object> homePage(HttpServletResponse response, Long userId);

    // 会员操作
    public Map<String, Object> operation(HttpServletResponse response, Long userId, Integer num) throws ParseException;
}