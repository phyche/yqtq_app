package com.sixmac.dao;

import com.sixmac.entity.BigRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/5/23 0023 下午 12:04.
 */
public interface BigRaceDao extends JpaRepository<BigRace, Integer>, JpaSpecificationExecutor<BigRace> {

}