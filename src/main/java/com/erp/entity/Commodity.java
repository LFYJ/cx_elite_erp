package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商品表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Commodity {

    private  Long    id ;              //id
    private  String  name;             //商品名
    private  String  img;              //预览图
    private  String  imgs;             //详情图
    private  String  originalPrice;    //商品原价
    private  String  discountPrice;    //商品折扣价
    private  String  phone;            //联系电话
    private  int  salesVolume;      //销量
    private  int   repertory;        //库存
    private  String  introduce;        //产品介绍
    private  String  requirements;     //配送要求 如10箱起送
    private  String  storeAddress;     //店铺地址
    private  int     state;            //状态
    private  int     type;            //分类
    private  String  createdate;       //创建时间



}
