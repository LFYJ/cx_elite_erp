package com.erp.dao.personnel;

import com.erp.entity.personnel.RightsRelation;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RightsRelationDao {


    //添加权限信息
    int  addRightsRelation(RightsRelation rightsRelation);

    //更新用户权限
    int  uopdRightsRelation(RightsRelation rightsRelation);


    //查询
    RightsRelation selectByidrightsRelation(RightsRelation rightsRelation);



}
