package com.erp.service.personnel.impl;

import com.erp.dao.personnel.GroupingDao;
import com.erp.entity.personnel.Grouping;
import com.erp.service.personnel.GroupingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupingServiceImpl implements GroupingService {


    @Resource
    private GroupingDao groupingDao;

    /**
     * 查询当前部门下的职位
     * @param
     * @return
     */
    @Override
    public List<Grouping> selectByListGroupingBypid(Grouping grouping) {
        return groupingDao.selectByListGroupingBypid(grouping);
    }


    /**
     * 查询职位
     * @param
     * @return
     */
    @Override
    public Grouping selectByGrouping(Grouping grouping) {

        return groupingDao.selectByGrouping(grouping);
    }

    /**
     * 添加职位
     * @param
     * @return
     */
    @Override
    public int addGrouping(Grouping grouping) {
        return groupingDao.addGrouping(grouping);
    }


    /**
     * 删除此职位
     * @param
     * @return
     */
    @Override
    public int groupingdelete(Long id) {
        return groupingDao. groupingdelete(id);
    }


    /**
     * 更新职位信息
     * @param
     * @return
     */
    @Override
    public int uptGrouping(Grouping grouping) {
        return groupingDao.uptGrouping(grouping);
    }


}
