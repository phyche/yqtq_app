package com.sixmac.service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sixmac.entity.UserReserve;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017 下午 2:43.
 */
public interface UserReserveService extends ICommonService<UserReserve> {

    public List<UserReserve> findByReserverId(Long reserveId);

    public List<UserReserve> findByUserId(Long userId);

}