package com.yifushidai.mapper;

import com.yifushidai.entity.ICEntity;

import java.util.List;
import java.util.Map;

public interface ICEntityMapper {


   /* int deleteByPrimaryKey(Long icId);

    int insert(ICEntity record);

    int insertSelective(ICEntity record);

    int updateByPrimaryKey(ICEntity record);*/


    ICEntity queryByMacAndName(String mac, String name);

    int updateByPrimaryKeySelective(ICEntity record);

    List<ICEntity> queryIc(Map<String, Object> map);

    int queryIcTotal(Map<String, Object> map);

    ICEntity selectByPrimaryKey(Long icId);

    List<ICEntity> queryNeedUp(Map<String, Object> map);

    int queryNeedUpTotal(Map<String, Object> map);

    int insertlist(List<ICEntity> list);
}