package com.yifushidai.mapper;

import com.yifushidai.entity.OpenRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OpenRecordEntityMapper {
    /*int deleteByPrimaryKey(Long id);

    int insert(OpenRecordEntity record);



    OpenRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpenRecordEntity record);

    int updateByPrimaryKey(OpenRecordEntity record);*/




    int deleteByMac(String mac);

    int[] queryEveDaycount(@Param("mac") String mac, @Param("startTime")String startTime, @Param("endTime")String endTime);

    List<OpenRecordEntity> queryByCurday(@Param("mac") String mac, @Param("todayStr")String todayStr);

    List<OpenRecordEntity> queryWarnList(Map<String, Object> map);

    int insertSelective(OpenRecordEntity record);

 }