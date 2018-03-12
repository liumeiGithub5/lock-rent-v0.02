package com.yifushidai.webService;

import com.yifushidai.mapper.SysUserRoleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liumei on 2017/6/28 10:36.
 * desc:用户角色对应service
 */
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleEntityMapper sysUserRoleMapper;


    /*通过uid 查找RoleIdlist*/
    public List<Long> queryRoleIdListByUid(String uid) {
        return sysUserRoleMapper.queryRoleIdListByUid(uid);
    }
}
