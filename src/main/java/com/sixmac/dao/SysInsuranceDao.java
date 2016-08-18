package com.sixmac.dao;

import com.sixmac.entity.SysInsurance;
import com.sixmac.entity.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 1:53.
 */
public interface SysInsuranceDao extends JpaRepository<SysInsurance, Long> {

    @Query("select a from SysInsurance a where a.status = ?1 ")
    public List<SysInsurance> findList(Integer status);
}