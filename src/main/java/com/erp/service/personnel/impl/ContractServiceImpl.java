package com.erp.service.personnel.impl;

import com.erp.dao.personnel.ContractDao;
import com.erp.entity.personnel.Contract;
import com.erp.service.personnel.ContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {


    @Resource
    private ContractDao contractDao;


    @Override
    public List<Contract> contractlist(Contract contract) {
        //判断筛选
        if(contract.getState()==1){
            //未到期
            contract.setExpireDate(new Date());
            return contractDao.contractlistBynoexpire(contract);
        }else  if(contract.getState()==2){
            //到期
            contract.setExpireDate(new Date());
            return contractDao.contractlistByexpire(contract);
        }else {
            return contractDao.contractlist(contract);
        }


    }



    @Override
    public Contract contractbyid(Contract contract) {
          return contractDao.contractbyid(contract);
    }

    @Override
    public int contractdelete(Long id) {
        return contractDao.contractdelete(id);
    }

    @Override
    public boolean contractadd(Contract contract) {

        if(contractDao.contractadd(contract)>0){
            return  true;
        }

        return false;
    }



    @Override
    public boolean contractupt(Contract contract) {
        if(contractDao.contractupt(contract)>0){
            return  true;
        }

        return false;

    }


}
