package com.erp.dao.personnel;


import com.erp.entity.personnel.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LeaveDao {

    //查询请假所有请假信息
    List<Leave> selectByListLeave(Map map);


    //查询请假详情
    Leave selectByLeave(Leave leave);

}
