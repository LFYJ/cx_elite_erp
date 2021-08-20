package com.erp.service.personnel;

import com.erp.entity.personnel.Grouping;

import java.util.List;

public interface GroupingService {

    //查询职位信息
    List<Grouping> selectByListGroupingBypid(Grouping grouping);

    //查询职位信息
    Grouping selectByGrouping(Grouping grouping);

    //添加职位
    int  addGrouping(Grouping grouping);

    //删除此职位
    int groupingdelete(Long id);

    //更新职位信息
    int  uptGrouping(Grouping grouping);

}
