package com.sixmac.dao;

import com.sixmac.entity.Area;
import com.sixmac.entity.BigRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/5/23 0023 下午 12:04.
 */
public interface BigRaceDao extends JpaRepository<BigRace, Long>, JpaSpecificationExecutor<BigRace> {

    @Query("select a from BigRace a where a.startDate = ?1 ")
    public BigRace getByStartDate(Long startDate);
}