package com.erp.entity.personnel;

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
    private Long userId;
    /**
     * 工号
     */
    private Long jobNumber;
    /**
     * 用户名
     */
    private String userName;
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
     * 所属职务
     */
    private Long   projectTeam;
    /**
     * 学历
     */
    private String education;
    /**
     * 专业
     */
    private String specialty;
    /**
     * 身份证
     */
    private String identityArd ;
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
     * 开户行
     */

    private String bank;

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
    private int userState;
    /**
     * 员工照片
     */
    private String userImg;
    /**
     * 工作经历
     */
    private String occupational;

    /**
     * 教育经历
     */
    private String educationUndergo;




    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 转正日期
     */
    private Date positiveDates;


    /**
     * 离职日期
     */
    private Date dimissionDate;
    /**
     * 年龄
     */
    private  int age;

    /**
     * 出生日期
     */
    private  Date birthdate;


    /**
     * 孝善银行卡账号
     */
    private String filialityFineCard ;
    /**
     * 孝善银行卡账号开户行
     */

    private String filialityFineBank;



    /**
     * 部门类
     */
    private Department department;
    /**
     * 职位类
     */
    private Position position;
    /**
     * 职位类
     */
    private Grouping grouping;






}
