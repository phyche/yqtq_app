package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.BannerDao;
import com.sixmac.entity.Banner;
import com.sixmac.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31 0031 下午 2:54.
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> findAll() {
        return bannerDao.findAll();
    }

    @Override
    public Page<Banner> find(int pageNum, int pageSize) {
        return bannerDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Banner> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Banner getById(Long id) {
        return bannerDao.findOne(id);
    }

    @Override
    public Banner deleteById(Long id) {
        Banner banner = getById(id);
        bannerDao.delete(banner);
        return banner;
    }

    @Override
    public Banner create(Banner banner) {
        return bannerDao.save(banner);
    }

    @Override
    public Banner update(Banner banner) {
        return bannerDao.save(banner);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}