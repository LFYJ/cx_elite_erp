package com.erp.service.personnel.impl;

import com.erp.config.IdGenerator;
import com.erp.dao.personnel.PayscaleDao;
import com.erp.dao.personnel.RightsRelationDao;
import com.erp.dao.personnel.UserDao;
import com.erp.entity.personnel.RightsRelation;
import com.erp.entity.personnel.User;
import com.erp.service.personnel.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户service
 */
@Service
public class UserServiceImpl implements UserService {



    @Resource
    private UserDao userDao;
    @Resource
    private RightsRelationDao rightsRelationDao;

    //id 生成策略
    @Resource
    private IdGenerator idGenerator;


    @Resource
    private PayscaleDao payscaleDao;
    /**
     * 查询的登录
     * @param user
     * @return
     */
    @Override
    public User selectByUser(User user) {


        return userDao.selectByUser(user);
    }


    /**
     * 查询用户列表
     * @param user
     * @return
     */
    @Override
    public List<User> selectByListUser(User user) {

        return userDao.selectByListUser(user);
    }


    /**
     * 查询用户档案带有部门信息列表
     * @param user
     * @return
     */
    @Override
    public List<User> selectByListUseByDepartmentr(User user) {


        return userDao.selectByListUseByDepartmentr(user);
    }

    /**
     * 询用户档案带有部门信息
     * @param user
     * @return
     */
    @Override
    public User selectByUserByDepartmentr(User user) {

        return userDao.selectByUserByDepartmentr(user);
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean addUser(User  user , String arrbox) throws Exception {

            if( userDao.addUser(user)>0){
                if(rightsRelationDao.addRightsRelation(
                        new RightsRelation().setId(idGenerator.nextId())
                                .setUserId(user.getUserId())
                                .setAuthorityName(arrbox)
                                .setCreatedate(new Date()) )>0){
                  /*  if(payscaleDao.addPayscale(payscale)>0){
                        return true;
                    }

                    throw new Exception("回滚");*/

                    return true;
                }
                throw new Exception("回滚");
            }
        return false;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean deleteuser(Long id) throws Exception {
        //删除用户信息
        if(  userDao.deleteuser(id)>0){

            return true;
        }

        return false;

    }


    /**
     * 更新用户信息
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean updUser(User  user,String arrbox)  {

        if( userDao.updateuser(user)>0||rightsRelationDao.uopdRightsRelation(
                         new RightsRelation()
                        .setUserId(user.getUserId())
                        .setAuthorityName(arrbox) )>0){
                return true;
        }

        return false;
    }

    /**
     * 查询当前用户权限
     * @return
     */
    @Override
    public RightsRelation selectByidrightsRelation(RightsRelation rightsRelation) {
        return rightsRelationDao.selectByidrightsRelation(rightsRelation);
    }

    @Override
    public List<User> usermain() {
        return userDao.usermain();
    }


}
