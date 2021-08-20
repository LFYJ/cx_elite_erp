package com.erp.dao.personnel;

import com.erp.entity.personnel.Grouping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupingDao {


    //查询岗位 pid
    List<Grouping> selectByListGroupingBypid(Grouping grouping);


    //查询岗位
    Grouping selectByGrouping(Grouping grouping);



    //添加职位信息
    int  addGrouping(Grouping grouping);

    //删除职位信息
    int  groupingdelete(Long id);

    //更新职位信息
    int uptGrouping(Grouping grouping);
}
