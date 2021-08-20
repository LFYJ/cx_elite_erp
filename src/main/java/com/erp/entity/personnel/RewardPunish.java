package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 奖罚记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RewardPunish {

    /**
     * 编号
     */
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 部门
     */
    private String departmentName;
    /**
     * 职位
     */
    private String positionName;
    /**
     * 奖罚数
     */
    private int  magnitude;
    /**
     * 原因
     */
    private String explain;
    /**
     * 类型 1奖励2.惩罚
     */
    private int type;
    /**
     * 创建日期
     */
    private Date createdate;


}

