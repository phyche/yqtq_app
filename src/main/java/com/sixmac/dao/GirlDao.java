package com.sixmac.dao;

import com.sixmac.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/5/23 0023 下午 1:57.
 */
public interface GirlDao extends JpaRepository<Girl, Long>, JpaSpecificationExecutor<Girl> {

}