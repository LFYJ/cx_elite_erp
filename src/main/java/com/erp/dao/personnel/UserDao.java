package com.erp.dao.personnel;


import com.erp.entity.personnel.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface UserDao {

    //查询用户信息
    public User selectByUser(User user);

    //查询所有用户信息
    public List<User> selectByListUser(User user);

   //查询用户信息
    public List<User> selectByListUseByDepartmentr(User user);
    //查询用户信息
    public User selectByUserByDepartmentr(User user);


    //添加用户信息
    int  addUser(User  user);


    //删除用户信息
    int  deleteuser(Long id);

    //更新用户信息
    int  updateuser(User user);


    List<User> usermain();

}
