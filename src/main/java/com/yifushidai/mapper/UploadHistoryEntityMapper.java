package com.yifushidai.mapper;

import com.yifushidai.entity.UploadHistoryEntity;
import com.yifushidai.utils.Query;

import java.util.List;

public interface UploadHistoryEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadHistoryEntity record);

    int insertSelective(UploadHistoryEntity record);

    UploadHistoryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UploadHistoryEntity record);

    int updateByPrimaryKey(UploadHistoryEntity record);

    List<UploadHistoryEntity> query(Query query);

    int queryTotal(Query query);
}