package com.erp.service.personnel.impl;

import com.erp.dao.personnel.PayscaleDao;
import com.erp.entity.personnel.Payscale;
import com.erp.service.personnel.PayscaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 薪资表
 */

@Service
public class PayscaleServiceImpl implements PayscaleService {

    @Resource
    private PayscaleDao payscaleDao;


    @Override
    public int addPayscale(Payscale payscale) {
        return 0;
    }


}
