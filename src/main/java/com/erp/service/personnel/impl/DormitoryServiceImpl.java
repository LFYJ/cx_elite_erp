package com.erp.service.personnel.impl;

import com.erp.dao.personnel.DormitoryDao;
import com.erp.entity.personnel.Dormitory;
import com.erp.service.personnel.DormitoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {


    @Resource
   private DormitoryDao dormitoryDao;


    @Override
    public List<Dormitory> dormitorylist(Dormitory dormitory) {
        return dormitoryDao.dormitorylist(dormitory);
    }


    @Override
    public Dormitory dormitory(Dormitory dormitory) {
        return dormitoryDao.dormitory(dormitory);
    }

    @Override
    public int dormitorydelete(Long id) {
        return  dormitoryDao.dormitorydelete(id);
    }

    @Override
    public boolean dormitoryadd(Dormitory dormitory) {
        if(dormitoryDao.dormitoryadd(dormitory)>0){
            return true;
        }

        return false;
    }

    @Override
    public int dormitoryupt(Dormitory dormitory) {
        return  dormitoryDao.dormitoryupt(dormitory);
    }
}
