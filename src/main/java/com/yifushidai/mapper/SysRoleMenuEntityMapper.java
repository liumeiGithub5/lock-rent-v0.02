package com.yifushidai.mapper;

import com.yifushidai.entity.SysRoleMenuEntity;

import java.util.List;

public interface SysRoleMenuEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenuEntity record);

    int insertSelective(SysRoleMenuEntity record);

    SysRoleMenuEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenuEntity record);

    int updateByPrimaryKey(SysRoleMenuEntity record);

    /*通过rolroleId 找MenuId*/
    List<Long> queryMenuIdListByRoleId(Long roleId);
}