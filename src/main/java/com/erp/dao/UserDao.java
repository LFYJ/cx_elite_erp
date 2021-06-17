package com.erp.dao;


import com.erp.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 反馈内容
 */
@Mapper
public interface UserDao {

    //查询用户信息
    public User selectByUser(User user);



}
