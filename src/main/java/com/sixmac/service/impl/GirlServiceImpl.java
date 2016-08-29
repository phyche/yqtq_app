package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.GirlDao;
import com.sixmac.entity.*;
import com.sixmac.service.CityService;
import com.sixmac.service.GirlService;
import com.sixmac.service.GirlUserService;
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
 * Created by Administrator on 2016/5/23 0023 下午 1:58.
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlDao girlDao;

    @Autowired
    private CityService cityService;

    @Autowired
    private GirlUserService girlUserService;

    @Override
    public List<Girl> findAll() {
        return girlDao.findAll();
    }

    @Override
    public Page<Girl> find(int pageNum, int pageSize) {
        return girlDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Girl> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Girl getById(Long id) {
        return girlDao.findOne(id);
    }

    @Override
    public Girl deleteById(Long id) {
        Girl girl = getById(id);
        girlDao.delete(girl);
        return girl;
    }

    @Override
    public Girl create(Girl girl) {
        return girlDao.save(girl);
    }

    @Override
    public Girl update(Girl girl) {
        return girlDao.save(girl);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List<Girl> page(Integer status, Long cityId) {
        return girlDao.page(status, cityId);
    }

    @Override
    public Map<String, Object> girlInfo(HttpServletResponse response, Long girlId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //宝贝个人信息
        Girl girl = girlDao.findOne(girlId);
        girl.setCityName(cityService.getByCityId(girl.getCityId()).getCity());

        //List<GirlImage> girlImages = girlImageService.find(girlId, 0);
        //宝贝封面
        List<GirlImage> girlImages = new ArrayList<GirlImage>();
        //宝贝相册
        List<GirlImage> girlImages1 = new ArrayList<GirlImage>();
        for (GirlImage girlImage : girl.getGirlImageList()) {
            if (StringUtils.isNotBlank(girlImage.getUrl())) {
                girlImage.setUrl(ConfigUtil.getString("upload.url") + girlImage.getUrl());
                if (girlImage.getType() == 0) {
                    girlImages.add(girlImage);
                } else if (girlImage.getType() == 1) {
                    girlImages1.add(girlImage);
                }
            }
        }

        //宝贝预约数
        List<GirlUser> girlUserList = girlUserService.findByGirlId(girlId);
        if (girlUserList == null) {
            girl.setOrderNum(0);
        }
        girl.setOrderNum(girlUserList.size());
        girlDao.save(girl);

        Double star = 0.0;
        for (GirlComment girlComment : girl.getGirlComments()) {
            star += girlComment.getStar();
        }
        if (girl.getGirlComments().size() != 0) {
            girl.setAveStar(star / girl.getGirlComments().size());
        } else {
            girl.setAveStar(0.0);
        }

        map.put("girl", girl);
        map.put("girlImages", girlImages);
        map.put("girlImages1", girlImages1);

        return map;
    }
}