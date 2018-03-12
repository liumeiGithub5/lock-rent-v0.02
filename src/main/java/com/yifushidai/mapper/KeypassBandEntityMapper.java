package com.yifushidai.mapper;

import com.yifushidai.entity.KeypassBandEntity;

public interface KeypassBandEntityMapper {
 /*   int deleteByPrimaryKey(Long id);

    int insert(KeypassBandEntity record);

  int updateByPrimaryKey(KeypassBandEntity record);

    KeypassBandEntity selectByPrimaryKey(Long id);*/




    int updateByPrimaryKeySelective(KeypassBandEntity record);

    KeypassBandEntity queryByMac(String mac);

    int insertSelective(KeypassBandEntity record);

    int deleteByMac(String mac);
}