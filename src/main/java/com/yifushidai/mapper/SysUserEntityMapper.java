package com.yifushidai.mapper;

import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserEntityMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUserEntity record);

    int insertSelective(SysUserEntity record);

    SysUserEntity selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updateByPrimaryKey(SysUserEntity record);




    /*通过用户名密码查找*/
    SysUserEntity queryByusernameAndPwd(@Param("username") String username, @Param("password") String password);
    /*批量删除*/
    int delete(String[] userIds);
    /*查询*/
    List<SysUserEntity> queryAllList(Query query);

    int queryAllTotal(Query query);
    /*解禁*/
    int markNormal(String[] userIds);
    /*禁用*/
    int markDisable(String[] userIds);
}