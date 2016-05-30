package com.sixmac.service;

import com.sixmac.entity.GirlComment;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026 上午 10:34.
 */
public interface GirlCommentService extends ICommonService<GirlComment> {

    public List<GirlComment> findByGirlId(Integer girlId);
}