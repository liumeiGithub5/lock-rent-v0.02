package com.yifushidai.mapper;

import com.yifushidai.entity.MonthsDetailEntity;
import com.yifushidai.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonthsDetailEntityMapper {
    /*int deleteByPrimaryKey(Long id);

    int insert(MonthsDetailEntity record);



    MonthsDetailEntity selectByPrimaryKey(Long id);



    int updateByPrimaryKey(MonthsDetailEntity record);*/







    int deleteByMac(String mac);

    List<MonthsDetailEntity> queryByMac(Query query);

    int queryByMacTotal(Query query);

    MonthsDetailEntity queryCur(@Param("mac") String mac, @Param("months") String curMonths, @Param("years") String curYears);

    int insertSelective(MonthsDetailEntity record);

    int updateByPrimaryKeySelective(MonthsDetailEntity record);
}