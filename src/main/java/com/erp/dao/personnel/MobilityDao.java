package com.erp.dao.personnel;

import com.erp.entity.personnel.Mobility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MobilityDao {

    //查询所有调动记录
    List<Mobility> personnelTransferList(Mobility mobility);

    //查询调动记录
    Mobility personnelTransfer(Mobility mobility);

    //删除调动信息
    int  personneltransferdelete(Long id);

    //添加岗位调动记录
    int personneltransferadd(Mobility mobility);

}
