package com.erp.dao.personnel;

import com.erp.entity.personnel.Contract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractDao {

   //查询合同记录
   List<Contract> contractlist(Contract contract);
   //查询合同记录
   Contract contractbyid(Contract contract);


   //删除合同信息
   int  contractdelete(Long id);

   //添加合同信息
   int   contractadd(Contract contract);

    //更新合同
    int  contractupt(Contract contract);

    //查询未到期合同记录
    List<Contract> contractlistBynoexpire(Contract contract);

    //查询到期合同记录
    List<Contract> contractlistByexpire(Contract contract);

}
