package com.sixmac.dao;

import com.sixmac.entity.ReserveTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/5/24 0024 下午 6:30.
 */
public interface ReserveTeamDao extends JpaRepository<ReserveTeam, Integer>, JpaSpecificationExecutor<ReserveTeam> {

}