package com.yifushidai.mapper;

import com.yifushidai.entity.RemarkEntity;
import com.yifushidai.utils.Query;

import java.util.List;

public interface RemarkEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RemarkEntity record);

    int insertSelective(RemarkEntity record);

    RemarkEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RemarkEntity record);

    int updateByPrimaryKey(RemarkEntity record);



    List<RemarkEntity> queryRemark(Query query);

    int queryRemarkTotal(Query query);

    int handleRemark(Long[] ids);

    List<RemarkEntity> queryByMobile(String mb);
}