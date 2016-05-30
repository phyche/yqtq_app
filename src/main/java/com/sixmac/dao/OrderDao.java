package com.sixmac.dao;

import com.sixmac.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 5:54.
 */
public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {


}