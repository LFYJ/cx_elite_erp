package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 基本薪资表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Payscale {
    /**
     * 编号
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 工号
     */
    private Long wno;
    /**
     * 所属部门
     */
    private Long departmentId;
    /**
     * 所属职位
     */
    private Long positionId;
    /**
     * 基本薪资
     */
    private double baseSalary ;
    /**
     * 岗位薪资
     */
    private double postSalary;
    /**
     * 创建日期
     */
    private Date createdate;

}
