package com.sixmac.dao;

import com.sixmac.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/5/24 0024 上午 10:36.
 */
public interface InsuranceDao extends JpaRepository<Insurance, Long> {

}