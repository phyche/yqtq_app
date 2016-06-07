package com.sixmac.dao;

import com.sixmac.entity.ServiceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:44.
 */
public interface ServiceMessageDao extends JpaRepository<ServiceMessage, Long> {

}