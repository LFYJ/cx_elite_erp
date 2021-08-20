package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 合同
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Contract {


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
     * 姓名
     */
    private String name;
    /**
     * 签订日期
     */
    private Date signedDate;
    /**
     * 到期时间
     */
    private Date expireDate;



    /**
     * 状态
     */
    private int state;

}
