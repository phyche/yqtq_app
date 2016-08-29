package com.sixmac.service;

import com.sixmac.entity.BigRace;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23 0023 下午 12:05.
 */
public interface BigRaceService extends ICommonService<BigRace> {

    public Page<BigRace> page(Long cityId, Integer status, Integer pageNum, Integer pageSize);

    public BigRace getByStartDate(Long startDate);

    public List<BigRace> page(Long cityId, Integer status);

    // 现场看球列表
    public Map<String, Object> sceneList(HttpServletResponse response, Long cityId);
}