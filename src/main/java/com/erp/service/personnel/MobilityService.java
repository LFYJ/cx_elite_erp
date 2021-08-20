package com.erp.service.personnel;

import com.erp.entity.personnel.Mobility;
import com.erp.entity.personnel.User;

import java.util.List;

public interface MobilityService {

    //查询位置调动记录
    List<Mobility> personnelTransferList( Mobility mobility);


    //查询位置调动记录
    Mobility personnelTransfer( Mobility mobility);


    //删除调动信息
    int   personneltransferdelete(Long  id );

    //添加调动信息
    boolean  personneltransferadd(Mobility mobility, User user) throws Exception;

}
