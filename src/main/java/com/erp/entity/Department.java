package com.erp.entity;

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
public class Department {

    /**
     * 编号
     */
    private Long id;
    /**
     * 部门名
     */
    private String name;
    /**
     * 部门主管
     */
    private String supervisor;
    /**
     * 创建人用户ID
     */
    private Long userId;
    /**
     * 创建人姓名
     */
    private String creatorName;
    /**
     * 状态 1使用2删除
     */
    private String state;
    /**
     * 创建日期
     */
    private Date createDate;
}
