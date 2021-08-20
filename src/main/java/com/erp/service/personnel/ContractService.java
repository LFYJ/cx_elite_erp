package com.erp.service.personnel;

import com.erp.entity.personnel.Contract;

import java.util.List;

public interface ContractService {


   //查询所有合同
   List<Contract> contractlist(Contract contract);
   //查询所有合同
   Contract contractbyid(Contract contract);



   //删除合同
   int contractdelete(Long id );
   //添加合同
    boolean contractadd(Contract contract);


   //更新合同
   boolean contractupt(Contract contract);
}
