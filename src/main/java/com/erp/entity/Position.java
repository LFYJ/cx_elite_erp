package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 职位表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Position {

    /**
     * 编号
     */
    private Long id;
    /**
     * 所属部门
     */
    private Long pid;
    /**
     * 职位名称
     */
    private String jobTitle;
    /**
     * 创建人姓名
     */
    private String creatorName;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 状态 1.使用2.关闭
     */
    private int state;
}
