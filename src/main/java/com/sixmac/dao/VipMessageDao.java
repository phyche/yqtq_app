package com.sixmac.dao;

import com.sixmac.entity.VipMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:44.
 */
public interface VipMessageDao extends JpaRepository<VipMessage, Integer> {

}