package com.erp.dao.personnel;

import com.erp.entity.personnel.Payscale;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayscaleDao {


    //添加员工工资记录
    int  addPayscale(Payscale payscale);

    //删除用户基本薪资信息
    int  deletePayscale(Long id);


}
