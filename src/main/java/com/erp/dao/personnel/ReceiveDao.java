package com.erp.dao.personnel;


import com.erp.entity.personnel.Receive;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReceiveDao {


    //查询领取记录信息
     List<Receive> selectByReceive( Map<String, Object> map);

    //查询领取记录信息
    Receive selectByReceivebyid( Map<String, Object> map);


    //添加领取记录
    int  addReceive( Receive receive);

    //删除领取记录
    int deletereceive(Long id);

    //更新
    int uptReceive(Receive receive);
}
