package com.erp.entity.personnel;

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
    private double duration;
    /**
     * 状态 1.待审批2通过3未通过
     */
    private int state;
    /**
     * 所属部门
     */
    private String departmentName;
    /**
     * 审批人
     */
    private String examineName;
    /**
     * 审批人ID
     */
    private Long examineId;
    /**
     * 抄送人id
     */
    private String peopleId;
    /**
     * 抄送人姓名
     */
    private String peopleName;
    /**
     * 创建时间
     */
    private Date creationTime;

}
