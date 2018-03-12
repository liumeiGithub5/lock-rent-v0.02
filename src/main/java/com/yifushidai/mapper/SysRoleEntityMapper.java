package com.yifushidai.mapper;

import com.yifushidai.entity.SysRoleEntity;

public interface SysRoleEntityMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRoleEntity record);

    int insertSelective(SysRoleEntity record);

    SysRoleEntity selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int updateByPrimaryKey(SysRoleEntity record);




}