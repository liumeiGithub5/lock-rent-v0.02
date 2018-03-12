package com.yifushidai.mapper;

import com.yifushidai.entity.SysMenuEntity;

public interface SysMenuEntityMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenuEntity record);

    int insertSelective(SysMenuEntity record);

    SysMenuEntity selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int updateByPrimaryKey(SysMenuEntity record);
}