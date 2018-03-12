package com.yifushidai.mapper;

import com.yifushidai.entity.BandLockEntity;

import java.util.List;

public interface BandLockEntityMapper {
 /*   int deleteByPrimaryKey(Long id);

    int insert(BandLockEntity record);

    int updateByPrimaryKey(BandLockEntity record);*/



    int insertSelective(BandLockEntity record);

    BandLockEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BandLockEntity record);
    
    List<BandLockEntity> getMainLocks(String mb);

    BandLockEntity queryByMac(String mac);

    String queryByLockId(Long lockId);

    int deleteByLockId(Long lockId);

    String getBlueTeeth(Long lockId);

    List<BandLockEntity> queryByMobile(String mb);
}