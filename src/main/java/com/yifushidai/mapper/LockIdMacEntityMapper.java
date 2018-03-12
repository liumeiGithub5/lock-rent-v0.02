package com.yifushidai.mapper;

import com.yifushidai.entity.LockIdMacEntity;

import java.util.List;

public interface LockIdMacEntityMapper {
  /*  int deleteByPrimaryKey(Long id);

    int insert(LockIdMacEntity record);

    int insertSelective(LockIdMacEntity record);

    LockIdMacEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LockIdMacEntity record);

    int updateByPrimaryKey(LockIdMacEntity record);*/



    String queryByLockId(Long lockId);

    int insertlist(List<LockIdMacEntity> list);
}