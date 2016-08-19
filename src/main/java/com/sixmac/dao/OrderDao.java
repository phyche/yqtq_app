package com.sixmac.dao;

import com.sixmac.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024 下午 5:54.
 */
public interface OrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("select a from Order a where a.sn= ?1")
    public Order iFindOneByOrderNum(String orderNum);

    @Query("select a from Order a where a.action = ?1 and a.user.id = ?2 and a.status = 1")
    public List<Order> findByUserIdAndAction(Integer action, Long userId);
}