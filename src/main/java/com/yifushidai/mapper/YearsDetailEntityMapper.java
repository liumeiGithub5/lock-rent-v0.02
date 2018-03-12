package com.yifushidai.mapper;

import com.yifushidai.entity.YearsDetailEntity;
import com.yifushidai.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YearsDetailEntityMapper {
  /*  int deleteByPrimaryKey(Long id);

    int insert(YearsDetailEntity record);



    YearsDetailEntity selectByPrimaryKey(Long id);



    int updateByPrimaryKey(YearsDetailEntity record);*/


    int deleteByMac(String mac);

    List<YearsDetailEntity> queryByMac(Query query);

    int queryByMacTotal(Query query);

    YearsDetailEntity queryCur(@Param("mac") String mac, @Param("years") String curYears);

    int insertSelective(YearsDetailEntity record);

    int updateByPrimaryKeySelective(YearsDetailEntity record);
}