package com.sixmac.dao;

import com.sixmac.entity.GirlUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 2:29.
 */
public interface GirlUserDao extends JpaRepository<GirlUser, Long>, JpaSpecificationExecutor<GirlUser> {

    @Query("select a from GirlUser a where a.user.id = ?1 and a.status != 3")
    public List<GirlUser> findByUserId(Long userId);

    @Query("select a from GirlUser a where a.girl.id = ?1 ")
    public List<GirlUser> findByGirlId(Long girlId);

}