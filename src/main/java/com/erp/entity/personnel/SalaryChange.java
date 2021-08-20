package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 薪资变动
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SalaryChange {

    /**
     * 编号
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 姓名
     */
    private String username;
    /**
     * 性别
     */
    private String gender;
    /**
     * 部门
     */
    private String departmentName;
    /**
     * 职务
     */
    private String positionName;
    /**
     * 入职日期
     */
    private Date entryTime;
    /**
     * 调整日期
     */
    private Date adjustmentdata;
    /**
     * 创建日期
     */
    private Date createdate;
    /**
     * 1晋升2.降级3.转正4.能力晋升5.调岗
     */
     private int  type;
    /**
     * 1.同意2驳回
     */
    private  int    state;

}
