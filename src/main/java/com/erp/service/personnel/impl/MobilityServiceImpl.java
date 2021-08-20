package com.erp.service.personnel.impl;

import com.erp.dao.personnel.MobilityDao;

import com.erp.dao.personnel.UserDao;
import com.erp.entity.personnel.Mobility;

import com.erp.entity.personnel.User;
import com.erp.service.personnel.MobilityService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service
public class MobilityServiceImpl implements MobilityService {


    @Resource
    private MobilityDao mobilityDao;

    @Resource
    private UserDao userDao;

    /**
     * 查询位置调动记录
     * @param mobility
     * @return
     */
    @Override
    public List<Mobility> personnelTransferList(Mobility mobility) {
        return mobilityDao.personnelTransferList(mobility);
    }

    @Override
    public Mobility personnelTransfer(Mobility mobility) {
        return mobilityDao.personnelTransfer(mobility);
    }

    /**
     * 删除调动信息
     * @param id
     * @return
     */
    @Override
    public int personneltransferdelete(Long id) {
        return mobilityDao.personneltransferdelete(id);
    }

    /**
     * 添加调动信息
     * @param mobility
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean personneltransferadd(Mobility mobility, User user) throws Exception {

        if( mobilityDao.personneltransferadd(mobility)>0){

            if( userDao.updateuser(user) >0){
                return true;
            }
            throw new Exception("回滚");
        }
        return false;


    }
}
