package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    /**
     * 编号
     */
    private Long id;
    /**
     * 工号
     */
    private Long jobNumber;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 部门ID
     */
    private Long departmentId;
    /**
     * 性别
     */
    private String gender;
    /**
     * 职位
     */
    private Long positionId;
    /**
     * 学历
     */
    private String education;
    /**
     * 身份证
     */
    private int identityArd ;
    /**
     * 民族
     */
    private String nation;
    /**
     * 本人手机号
     */
    private String phone;
    /**
     * 紧急联系人
     */
    private String emergencyUser;
    /**
     * 紧急联系人电话
     */
    private String emergencyPhone;
    /**
     * 社保
     */
    private String socialSecurity ;
    /**
     * 银行卡账号
     */
    private String bankCard ;
    /**
     * 户口类型
     */
    private String hukouType;
    /**
     * 现住址
     */
    private String presentAddress;
    /**
     * 户籍地址
     */
    private String address;
    /**
     * 毕业院校
     */
    private String schoolBy;
    /**
     * 婚姻情况
     */
    private String marriage;
    /**
     * 状态 1表示在职，2表示离职
     */
    private int state;
    /**
     * 员工照片
     */
    private String userImg;
    /**
     * 工作经历
     */
    private String occupational;
    /**
     * 入职日期
     */
    private Date entryDate;
    /**
     * 离职日期
     */
    private Date dimissionDate;



}
