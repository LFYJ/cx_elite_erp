package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 职位调动
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Mobility {

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
     * 出生日期
     */
    private Date birthdate;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 身份证号码
     */
    private String identityArd ;
    /**
     * 原部门
     */
    private Long departmentId;
    /**
     * 原部门名
     */
    private String departmentName;
    /**
     * 原岗位
     */
    private Long positionId;
    /**
     * 原岗位名
     */
    private String positionName;
    /**
     * 调至部门
     */
    private Long toDepartmentId;
    /**
     * 调至部门名
     */
    private String toDepartmentName;
    /**
     * 调至岗位
     */
    private Long toPositionId;
    /**
     * 调至岗位名
     */
    private String toPositionName;

    /**
     * 原职务
     */
    private Long groupingId;
    /**
     * 原职务名
     */
    private String groupingName;


    /**
     * 调至职务
     */
    private Long toGroupingId;
    /**
     * 调至职务名
     */
    private String toGroupingName;


    /**
     * 调动生效日期
     */
    private Date transferData;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 状态 1.晋升2.降级3.岗位调动
     */
    private int type;

}
