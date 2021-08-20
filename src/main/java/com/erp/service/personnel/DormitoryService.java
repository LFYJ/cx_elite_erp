package com.erp.service.personnel;

import com.erp.entity.personnel.Dormitory;

import java.util.List;

public interface DormitoryService {

    //查询所有宿舍信息
    List<Dormitory> dormitorylist(Dormitory dormitory);

    //查询宿舍信息
    Dormitory dormitory(Dormitory dormitory);

    //删除宿舍信息
    int  dormitorydelete(Long id);
    //添加宿舍信息
    boolean  dormitoryadd(Dormitory dormitory);

    //更新宿舍信息
    int dormitoryupt  (Dormitory dormitory);
}
