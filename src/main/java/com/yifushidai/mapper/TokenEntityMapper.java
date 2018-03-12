package com.yifushidai.mapper;

import com.yifushidai.entity.TokenEntity;

public interface TokenEntityMapper {
    /*

    int insert(TokenEntity record);

    int updateByPrimaryKey(TokenEntity record);*/

    TokenEntity queryByToken(String token);

    TokenEntity selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(TokenEntity record);

    int insertSelective(TokenEntity record);

    int deleteByPrimaryKey(String mobile);
}