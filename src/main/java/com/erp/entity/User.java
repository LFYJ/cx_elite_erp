package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private Long id;                   //id
    private String name;               //微信名或昵称
    private String password;           //密码
    private String openId;             //openid
    private String  creationytime;        //创建时间
    private String phone;              //电话
    private int role;                  //角色



}
