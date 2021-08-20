package com.erp.entity.personnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Grouping {

    /**
     * 编号
     */
    private Long groupingId;
    /**
     * 所属部门
     */
    private Long pid;
    /**
     * 职位名称
     */
    private String groupingName;



    /**
     * 创建人姓名
     */
    private String positionCreatorName;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 状态 1.使用2.关闭
     */
    private int positionState;
}
