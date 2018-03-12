package com.yifushidai.mapper;

import com.yifushidai.entity.SaveETEntity;

import java.util.List;

public interface SaveETEntityMapper {
    /*int deleteByPrimaryKey(Long id);

    int insert(SaveETEntity record);

    SaveETEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaveETEntity record);

    int updateByPrimaryKey(SaveETEntity record);*/








    int deleteByMac(String mac);

    List<SaveETEntity> queryByToday(String mac);

    int insertSelective(SaveETEntity record);
}