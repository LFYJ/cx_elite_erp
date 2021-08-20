package com.erp.dao.personnel;

import com.erp.entity.personnel.Department;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentDao {

    //查询所有用户信息
    List<Department> selectByListDepartment(Department pepartment);

    //查询用户信息
    Department selectByDepartment(Department pepartment);

    int  addDepartment(Department departmentr);


    int  deletedepartment(Long id );

    //更新部门信息
    int uptDepartment(Department pepartment);

}
