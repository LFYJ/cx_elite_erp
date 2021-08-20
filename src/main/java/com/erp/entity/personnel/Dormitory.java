package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 宿舍管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dormitory {

    /**
     * 编号
     */
    private Long id;
    /**
     * 宿舍号
     */
    private int dormitoryno;
    /**
     * 宿舍地点
     */
    private String place;
    /**
     * 宿舍可住人数
     */
    private int resideNnt;
    /**
     * 宿舍现住人数
     */
    private int presentNnt;
    /**
     * 状态
     */
    private int state;
    /**
     * 创建日期
     */
    private Date createDate;


    /**
     * 入住详情
     */
    private String particulars ;

}
