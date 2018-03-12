package com.yifushidai.mapper;

import com.yifushidai.entity.UserEntity;

public interface UserEntityMapper {
   /* int deleteByPrimaryKey(Long userId);

    int insert(UserEntity record);



    UserEntity selectByPrimaryKey(Long userId);



    int updateByPrimaryKey(UserEntity record);*/



    int insertSelective(UserEntity record);

    int updateByPrimaryKeySelective(UserEntity record);
    
    UserEntity selectByMobile(String mobile);
}