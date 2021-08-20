package com.erp.service.personnel.impl;

import com.erp.dao.personnel.DepartmentDao;
import com.erp.entity.personnel.Department;
import com.erp.service.personnel.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门Service
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {





    @Resource
    private DepartmentDao departmentDao;

    /**
     * 查询当前部门所有信息
     * @param departmentr
     * @return
     */
    @Override
    public List<Department> selectByListDepartment(Department departmentr) {
        return departmentDao.selectByListDepartment(departmentr);
    }

    /**
     * 查询当前部门信息
     * @param departmentr
     * @return
     */

    @Override
    public Department selectByDepartment(Department departmentr) {
        return departmentDao.selectByDepartment(departmentr);
    }

    /**
     * 添加部门信息记录
     * @param departmentr
     * @return
     */
    @Override
    public int addDepartment(Department departmentr) {

        return departmentDao.addDepartment(departmentr);
    }

    /**
     * 删除此部门
     * @param id
     * @return
     */
    @Override
    public int deletedepartment(Long id) {
        return departmentDao.deletedepartment(id);
    }

    /**
     * 更新部门
     * @param departmentr
     * @return
     */
    @Override
    public int uptDepartment(Department departmentr) {
        return departmentDao.uptDepartment(departmentr);
    }
}
