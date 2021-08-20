package com.erp.dao.personnel;

import com.erp.entity.personnel.SalaryChange;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalaryChangeDao {

    //查询薪资变动记录
    List<SalaryChange> salarychangelist(SalaryChange salaryChange);

    //删除薪资变动
    int  salarychangedelete(Long id );

    //添加薪资变动
    int  salarychangeadd(SalaryChange salaryChange);

}
