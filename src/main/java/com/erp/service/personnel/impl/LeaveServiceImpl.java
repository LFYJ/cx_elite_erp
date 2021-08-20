package com.erp.service.personnel.impl;

import com.erp.dao.personnel.LeaveDao;
import com.erp.entity.personnel.Leave;
import com.erp.service.personnel.LeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 请假Service
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Resource
    private LeaveDao leaveDao;


    /**
     * 查询所有请假信息
     * @param leave
     * @return
     */
    @Override
    public List<Leave> selectByListLeave(Map map) {
        return leaveDao.selectByListLeave(map);
    }

    /**
     * 查询请假详情
     * @param leave
     * @return
     */
    @Override
    public Leave selectByLeave(Leave leave) {
        return leaveDao.selectByLeave(leave);
    }
}
