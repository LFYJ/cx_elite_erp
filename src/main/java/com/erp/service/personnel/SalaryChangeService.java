package com.erp.service.personnel;

import com.erp.entity.personnel.SalaryChange;

import java.util.List;

public interface SalaryChangeService {

    //查询薪资变动记录
    List<SalaryChange> salarychangelist(SalaryChange salaryChange);
    //删除薪资变动
    int  salarychangedelete(Long id );

    //添加薪资变动
    boolean salarychangeadd(SalaryChange salaryChange);
}
