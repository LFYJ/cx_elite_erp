package com.erp.dao.personnel;

import com.erp.entity.personnel.Dormitory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DormitoryDao {

    //查询宿舍管理信息
    List<Dormitory> dormitorylist(Dormitory dormitory);



    Dormitory dormitory(Dormitory dormitory);

    //删除宿舍管理信息
    int  dormitorydelete(Long id );

    //添加宿舍信息
    int dormitoryadd(Dormitory dormitory);

    //更新宿舍信息
    int  dormitoryupt(Dormitory dormitory);

}
