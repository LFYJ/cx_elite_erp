package com.erp.service.impl;

import com.erp.dao.UserDao;
import com.erp.entity.User;
import com.erp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户service
 */
@Service
public class UserServiceImpl implements UserService {



    @Resource
    private UserDao userDao;

    /**
     * 查询的登录
     * @param user
     * @return
     */
    @Override
    public User selectByUser(User user) {


        return userDao.selectByUser(user);
    }


}
