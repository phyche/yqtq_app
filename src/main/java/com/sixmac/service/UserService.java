package com.sixmac.service;

import com.sixmac.entity.User;
import com.sixmac.entity.UserReserve;
import com.sixmac.service.common.ICommonService;

/**
 * Created by Administrator on 2016/5/17 0017 下午 4:51.
 */
public interface UserService extends ICommonService<User> {

    public User findByMobile(String mobile);

    public User findByName(String nickname);

}