package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.ReportDao;
import com.sixmac.entity.Report;
import com.sixmac.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008 11:10.
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<Report> findAll() {
        return reportDao.findAll();
    }

    @Override
    public Page<Report> find(int pageNum, int pageSize) {
        return reportDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Report> find(int pageNum) {
        return null;
    }

    @Override
    public Report getById(Long id) {
        return reportDao.findOne(id);
    }

    @Override
    public Report deleteById(Long id) {
        Report report = getById(id);
        reportDao.delete(report);
        return report;
    }

    @Override
    public Report create(Report report) {
        return reportDao.save(report);
    }

    @Override
    public Report update(Report report) {
        return reportDao.save(report);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}