package com.erp.service.personnel.impl;

import com.erp.dao.personnel.RewardPunishDao;
import com.erp.entity.personnel.RewardPunish;
import com.erp.service.personnel.RewardPunishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 奖罚记录
 */
@Service
public class RewardPunishServiceImpl implements RewardPunishService {


    @Resource
    private  RewardPunishDao rewardPunishDao;

    /**
     * 查询奖罚记录
     * @param rewardPunish
     * @return
     */
    @Override
    public List<RewardPunish> rewardpunishlist(RewardPunish rewardPunish) {
        return rewardPunishDao.rewardpunishlist(rewardPunish);
    }

    /**
     * 删除奖罚记录
     * @param receive
     * @return
     */
    @Override
    public int rewardpunishdelete(Long id ) {
        return rewardPunishDao.rewardpunishdelete(id);
    }


    /**
     * 添加奖罚记录
     * @param rewardPunish
     * @return
     */
    @Override
    public boolean rewardpunishadd(RewardPunish rewardPunish) {

        if(rewardPunishDao.rewardpunishadd(rewardPunish)>0){
         return  true;
        }
        return false;
    }
}
