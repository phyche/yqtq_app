package com.sixmac.dao;

import com.sixmac.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/7/8 0008 11:09.
 */
public interface ReportDao extends JpaRepository<Report, Long> {

}