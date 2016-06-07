package com.sixmac.dao;

import com.sixmac.entity.HostRace;
import com.sixmac.entity.WatchingRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/5/23 0023 上午 11:20.
 */
public interface HostRaceDao extends JpaRepository<HostRace, Long>, JpaSpecificationExecutor<HostRace> {

}