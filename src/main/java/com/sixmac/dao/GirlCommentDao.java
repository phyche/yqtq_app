package com.sixmac.dao;

import com.sixmac.entity.GirlComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026 上午 10:34.
 */
public interface GirlCommentDao extends JpaRepository<GirlComment, Integer> {

    @Query("select a from GirlComment a where a.girl.id = ?1 ")
    public List<GirlComment> findByGirlId(Integer girlId);
}