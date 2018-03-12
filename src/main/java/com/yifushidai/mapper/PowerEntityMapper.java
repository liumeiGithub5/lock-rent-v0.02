package com.yifushidai.mapper;

import com.yifushidai.entity.PowerEntity;

import java.util.List;

public interface PowerEntityMapper {
/*    int deleteByPrimaryKey(Long id);

    int insert(PowerEntity record);



    PowerEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PowerEntity record);

    int updateByPrimaryKey(PowerEntity record);*/










    int deleteByMac(String mac);

    List<PowerEntity> queryLatestByMac(String mac);

    int insertSelective(PowerEntity record);
}