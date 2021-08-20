package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * 部门表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Department   {

    /**
     * 编号
     */
    private Long departmentId;
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 部门主管
     */
    private String supervisor;
    /**
     * 负责人ID
     */
    private Long createUserId;
    /**
     * 创建人姓名
     */
    private String creatorName;
    /**
     * 状态 1使用2删除
     */
    private int  departmentState;
    /**
     * 创建日期
     */
    private Date departmentCreateDate;



}
