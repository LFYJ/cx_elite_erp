package com.erp.service.personnel.impl;

import com.erp.dao.personnel.SalaryChangeDao;
import com.erp.entity.personnel.SalaryChange;
import com.erp.service.personnel.SalaryChangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SalaryChangeServiceImpl implements SalaryChangeService {

    @Resource
    private SalaryChangeDao salaryChangeDao;

    @Override
    public List<SalaryChange> salarychangelist(SalaryChange salaryChange) {
        return salaryChangeDao.salarychangelist(salaryChange);
    }


    /**
     * 删除薪资变动
     * @param id
     * @return
     */
    @Override
    public int salarychangedelete(Long id) {
        return salaryChangeDao.salarychangedelete(id);
    }

    /**
     * 添加薪资变动
     * @param salaryChange
     * @return
     */
    @Override
    public boolean salarychangeadd(SalaryChange salaryChange) {
        if(salaryChangeDao.salarychangeadd(salaryChange)>0){
            return  true;
        }
        return false;
    }


}
