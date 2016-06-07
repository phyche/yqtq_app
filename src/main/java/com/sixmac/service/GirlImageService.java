package com.sixmac.service;

import com.sixmac.entity.GirlImage;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023 下午 2:11.
 */
public interface GirlImageService extends ICommonService<GirlImage> {

    public List<GirlImage> findByGirlId(Long girlId);

    public List<GirlImage> findByType(Integer type);

    public Page<GirlImage> page(Integer type,Integer pageNum, Integer pageSize);
}