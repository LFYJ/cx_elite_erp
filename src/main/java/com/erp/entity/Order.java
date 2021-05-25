package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order {
    //订单号
    private  Long id;

    //商品id
    private  Long commodityId;
    //商品名
    private  String commodityName;
    //商品价格
    private  int price;

    //用户id
    private  Long userId;
    //姓名称呼
    private  String compellation;
    //联系电话
    private String phone;
    //配送地址
    private String deliveryAddress;
    //订单数量
    private  int  quantity;
    //完成状态
    private  int state;
    //创建时间
    private  String creationdata;
    //商品
    private  Commodity commodity;



}
