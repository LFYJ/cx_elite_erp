package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *活动列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Classify {

    //编号
    public Long id;
    //商品ID
    public Long commodity_id;
    //活动预览图片
    private  String  img;
    //活动标题
    public String activity;
    //活动详情
    public String particulars;
    //开始时间
    public String createdate;
    //截止时间
    private  String  deadline;
    //状态
    private  int     state;




}
