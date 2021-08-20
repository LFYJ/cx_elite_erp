package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 领取实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Receive {
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
     * 领取物品
     */
    private String goods;
    /**
     * 领取时间
     */
    private Date createdate;
    /**
     * 状态 1.领取2.归还
     */
    private int state;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 部门类
     */
    private Department department;
    /**
     * 职位类
     */
    private Position position;
}
