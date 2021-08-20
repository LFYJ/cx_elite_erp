package com.erp.service.personnel.impl;


import com.erp.dao.personnel.PositionDao;
import com.erp.entity.personnel.Position;
import com.erp.service.personnel.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {


    @Resource
    private PositionDao positionDao;

    /**
     * 查询当前部门下的职位
     * @param position
     * @return
     */
    @Override
    public List<Position> selectByListPositionBypid(Position position) {
        return positionDao.selectByListPositionBypid(position);
    }


    /**
     * 查询职位
     * @param position
     * @return
     */
    @Override
    public Position selectByPosition(Position position) {
        return positionDao.selectByPosition(position);
    }

    /**
     * 添加职位
     * @param position
     * @return
     */
    @Override
    public int addPosition(Position position) {
        return positionDao.addPosition(position);
    }


    /**
     * 删除此职位
     * @param id
     * @return
     */
    @Override
    public int positiondelete(Long id) {
        return positionDao.positiondelete(id);
    }


    /**
     * 更新职位信息
     * @param position
     * @return
     */
    @Override
    public int uptPosition(Position position) {
        return positionDao.uptPosition(position);
    }


}
