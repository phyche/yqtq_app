package com.sixmac.dao;

import com.sixmac.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/5/19 0019 上午 10:08.
 */
public interface InformationDao extends JpaRepository<Information, Integer>, JpaSpecificationExecutor<Information> {

}