package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 请假表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Leave {


    /**
     * 编号
     */
    private Long id;
    /**
     * 请假人编号
     */
    private Long userId;
    /**
     * 工号
     */
    private Long jobNumber;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 请假类型
     */
    private String type;
    /**
     * 请假事由
     */
    private String reason;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date finishDate;
    /**
     * 时长
     */
    private int duration;
    /**
     * 状态 1.待审批2通过3未通过
     */
    private int state;
    /**
     * 所属部门
     */
    private Long departmentId;
    /**
     * 审批人
     */
    private String approvalName;
    /**
     * 审批人ID
     */
    private String approvalId;
    /**
     * 创建时间
     */
    private Date creationTime;

}
