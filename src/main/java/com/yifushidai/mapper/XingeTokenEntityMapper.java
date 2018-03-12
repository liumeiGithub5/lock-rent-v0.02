package com.yifushidai.mapper;

import com.yifushidai.entity.XingeTokenEntity;

public interface XingeTokenEntityMapper {
    /*int deleteByPrimaryKey(String mobile);

    int insert(XingeTokenEntity record);

    int updateByPrimaryKey(XingeTokenEntity record);*/

    int insertSelective(XingeTokenEntity record);

    XingeTokenEntity selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(XingeTokenEntity record);
}