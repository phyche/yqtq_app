package com.sixmac.dao;

import com.sixmac.entity.GirlComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026 上午 10:34.
 */
public interface GirlCommentDao extends JpaRepository<GirlComment, Long>, JpaSpecificationExecutor<GirlComment> {

    @Query("select a from GirlComment a where a.girlId = ?1 order by a.createDate desc")
    public List<GirlComment> findByGirlId(Long girlId);
}