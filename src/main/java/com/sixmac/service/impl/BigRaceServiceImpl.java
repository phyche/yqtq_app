package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.BigRaceDao;
import com.sixmac.entity.BigRace;
import com.sixmac.entity.Girl;
import com.sixmac.entity.GirlImage;
import com.sixmac.entity.HostRace;
import com.sixmac.entity.vo.GirlImageVo;
import com.sixmac.service.BigRaceService;
import com.sixmac.service.GirlService;
import com.sixmac.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23 0023 下午 12:05.
 */
@Service
public class BigRaceServiceImpl implements BigRaceService {

    @Autowired
    private BigRaceDao bigRaceDao;

    @Autowired
    private GirlService girlService;

    @Override
    public List<BigRace> findAll() {
        return bigRaceDao.findAll();
    }

    @Override
    public Page<BigRace> find(int pageNum, int pageSize) {
        return bigRaceDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<BigRace> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public BigRace getById(Long id) {
        return bigRaceDao.findOne(id);
    }

    @Override
    public BigRace deleteById(Long id) {
        BigRace bigRace = getById(id);
        bigRaceDao.delete(bigRace);
        return bigRace;
    }

    @Override
    public BigRace create(BigRace bigRace) {
        return bigRaceDao.save(bigRace);
    }

    @Override
    public BigRace update(BigRace bigRace) {
        return bigRaceDao.save(bigRace);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<BigRace> page(final Long cityId, final Integer status, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<BigRace> page = bigRaceDao.findAll(new Specification<BigRace>() {
            @Override
            public Predicate toPredicate(Root<BigRace> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (cityId != null) {
                    Predicate pre = cb.equal(root.get("stadium").get("cityId").as(Integer.class), cityId);
                    predicateList.add(pre);
                }

                if (status != null) {
                    Predicate pre = cb.equal(root.get("status").as(Integer.class), status);
                    predicateList.add(pre);
                }

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    @Override
    public BigRace getByStartDate(Long startDate) {
        return bigRaceDao.getByStartDate(startDate);
    }

    @Override
    public List<BigRace> page(Long cityId, Integer status) {
        return bigRaceDao.page(cityId, status);
    }

    @Override
    public Map<String, Object> sceneList(HttpServletResponse response, Long cityId) {
        List<BigRace> bigRaceList = page(cityId, 0);
        // Page<BigRace> page = bigRaceService.page(cityId, 0, pageNum, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();

        List<Long> numList = new ArrayList<Long>();
        Long systemTime = System.currentTimeMillis();
        for (BigRace bigRace : bigRaceList) {

            Long time = bigRace.getStartDate() - systemTime;
            if (time > 0) {
                numList.add(time);
            }
        }

        if (numList.size() > 0) {
            Long minnum = numList.get(0);
            for (int i = 0; i < numList.size(); i++) {
                if (numList.get(i) > minnum) {
                    minnum = numList.get(i);
                }
            }
            BigRace bigRace = bigRaceDao.getByStartDate(minnum + systemTime);
            if (StringUtils.isNotBlank(bigRace.getAvater1())) {
                bigRace.setAvater1(ConfigUtil.getString("upload.url") + bigRace.getAvater1());
            }
            if (StringUtils.isNotBlank(bigRace.getAvater2())) {
                bigRace.setAvater2(ConfigUtil.getString("upload.url") + bigRace.getAvater2());
            }

            List<GirlImageVo> girlImageList = new ArrayList<GirlImageVo>();
            if (bigRace == null) {
                girlImageList = null;
            } else {
                List<Girl> list = girlService.page(0, cityId);
                for (Girl girl : list) {
                    for (GirlImage girlImage : girl.getGirlImageList()) {
                        if (girlImage.getType() == 0) {
                            if (StringUtils.isNotBlank(girlImage.getUrl())) {
                                girlImage.setUrl(ConfigUtil.getString("upload.url") + girlImage.getUrl());
                            }
                            GirlImageVo girlImageVo = new GirlImageVo();
                            girlImageVo.setId(girlImage.getId());
                            girlImageVo.setGirlId(girl.getId());
                            girlImageVo.setUrl(girlImage.getUrl());
                            girlImageVo.setName(girl.getName());
                            girlImageVo.setAge(girl.getAge());
                            girlImageVo.setHeight(girl.getHeight());
                            girlImageVo.setWeight(girl.getWeight());
                            girlImageList.add(girlImageVo);
                        }
                    }
                }

            }
            for (int i = 0; i < girlImageList.size(); i++) {
                GirlImageVo girlImage = girlImageList.get(i);
                if (girlImageList.contains(girlImage)) {
                    girlImageList.remove(i);
                }
            }

            map.put("bigRace", bigRace);
            map.put("girlImageList", girlImageList);

        }
        return map;
    }
}