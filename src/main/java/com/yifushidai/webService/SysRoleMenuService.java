package com.yifushidai.webService;

import com.yifushidai.mapper.SysRoleMenuEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liumei on 2017/6/28 10:36.
 * desc:角色菜单对应service
 */
@Service
public class SysRoleMenuService {
    @Autowired
    private SysRoleMenuEntityMapper sysRoleMenuMapper;


    public List<Long> queryMenuIdListByRoleId(Long roleId) {
        return sysRoleMenuMapper.queryMenuIdListByRoleId(roleId);
    }
}
