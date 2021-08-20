package com.erp.service.personnel.impl;

import com.erp.dao.personnel.ReceiveDao;
import com.erp.entity.personnel.Receive;
import com.erp.service.personnel.ReceiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReceiveServiceImpl  implements ReceiveService {

    @Resource
    private ReceiveDao receiveDao;

    @Override
    public List<Receive>  selectByReceive( Map<String, Object> map) {

        return receiveDao.selectByReceive(map);
    }

    @Override
    public Receive selectByReceivebyid(Map<String, Object> map) {
        return receiveDao.selectByReceivebyid(map);
    }

    /**
     * 添加员工领取记录
     * @param receive
     * @return
     */
    @Override
    public int addReceive(Receive receive) {


        return receiveDao.addReceive(receive);
    }


    /**
     * 删除领取记录
     * @param id
     * @return
     */
    @Override
    public boolean deletereceive(Long id) {

        if(receiveDao.deletereceive(id)>0){
            return true;
        }

        return false;
    }

    /**
     * 更新
     * @param receive
     * @return
     */
    @Override
    public int uptReceive(Receive receive) {
        return receiveDao.uptReceive(receive);
    }


}
