package com.yifushidai.mapper;

import com.yifushidai.entity.WeeksDetailEntity;
import com.yifushidai.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeeksDetailEntityMapper {
    /*int deleteByPrimaryKey(Long id);

    int insert(WeeksDetailEntity record);



    WeeksDetailEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeeksDetailEntity record);

    int updateByPrimaryKey(WeeksDetailEntity record);*/




    int deleteByMac(String mac);

    List<WeeksDetailEntity> queryByMac(Query query);

    int queryByMacTotal(Query query);

    WeeksDetailEntity queryCur(@Param("mac") String mac, @Param("weeks") String curWeeks, @Param("years") String curYears);

    int insertSelective(WeeksDetailEntity record);

    int updateByPrimaryKeySelective(WeeksDetailEntity record);
}