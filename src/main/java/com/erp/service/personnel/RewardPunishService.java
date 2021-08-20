package com.erp.service.personnel;

import com.erp.entity.personnel.RewardPunish;

import java.util.List;

public interface RewardPunishService {


    //查询奖罚记录
    List<RewardPunish> rewardpunishlist(RewardPunish rewardPunish);


    //删除奖罚记录
    int  rewardpunishdelete( Long id );


    //添加奖罚记录
    boolean  rewardpunishadd( RewardPunish rewardPunish );



}
