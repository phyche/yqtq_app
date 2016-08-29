package com.sixmac.service;

import com.sixmac.entity.Girl;
import com.sixmac.entity.WatchingRace;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23 0023 下午 1:57.
 */
public interface GirlService extends ICommonService<Girl> {

    public List<Girl> page(Integer status, Long cityId);

    // 现场看球宝贝详情
    public Map<String, Object> girlInfo(HttpServletResponse response, Long girlId);
}