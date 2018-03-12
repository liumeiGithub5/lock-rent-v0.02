package com.yifushidai.mapper;

import com.yifushidai.entity.QuartersDetailEntity;
import com.yifushidai.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuartersDetailEntityMapper {
   /* int deleteByPrimaryKey(Long id);

    int insert(QuartersDetailEntity record);



    QuartersDetailEntity selectByPrimaryKey(Long id);



    int updateByPrimaryKey(QuartersDetailEntity record);*/








    int deleteByMac(String mac);

    List<QuartersDetailEntity> queryByMac(Query query);

    int queryByMacTotal(Query query);

    QuartersDetailEntity queryCur(@Param("mac") String mac, @Param("years") String curYears, @Param("quarters") String curQuarters);

    int insertSelective(QuartersDetailEntity record);

    int updateByPrimaryKeySelective(QuartersDetailEntity record);
}