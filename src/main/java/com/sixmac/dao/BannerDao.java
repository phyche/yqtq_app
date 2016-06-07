package com.sixmac.dao;

import com.sixmac.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/5/31 0031 下午 2:54.
 */
public interface BannerDao extends JpaRepository<Banner, Long>, JpaSpecificationExecutor<Banner> {

}