package com.erp.dao.personnel;

import com.erp.entity.personnel.RewardPunish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RewardPunishDao {

    //查询奖罚记录
    List<RewardPunish> rewardpunishlist(RewardPunish rewardPunish);

    //删除奖罚记录
    int  rewardpunishdelete(Long id );

    //删除奖罚记录
    int  rewardpunishadd(RewardPunish rewardPunish);


}
