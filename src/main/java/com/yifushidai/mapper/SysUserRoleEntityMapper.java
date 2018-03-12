package com.yifushidai.mapper;

import com.yifushidai.entity.SysUserRoleEntity;

import java.util.List;

public interface SysUserRoleEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRoleEntity record);

    int insertSelective(SysUserRoleEntity record);

    SysUserRoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRoleEntity record);

    int updateByPrimaryKey(SysUserRoleEntity record);


    /*通过uid 查找RoleId*/
    List<Long> queryRoleIdListByUid(String uid);
}