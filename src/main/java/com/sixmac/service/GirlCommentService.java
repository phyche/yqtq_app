package com.sixmac.service;

import com.sixmac.entity.GirlComment;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026 上午 10:34.
 */
public interface GirlCommentService extends ICommonService<GirlComment> {

    public List<GirlComment> findByGirlId(Long girlId);

    public Page<GirlComment> page(Long girlId, Integer pageNum, Integer pageSize);
}