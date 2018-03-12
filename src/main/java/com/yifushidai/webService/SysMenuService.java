package com.yifushidai.webService;

import com.yifushidai.entity.SysMenuEntity;
import com.yifushidai.mapper.SysMenuEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liumei on 2017/6/28 10:46.
 * desc: 菜单相关
 */
@Service
public class SysMenuService {
    @Autowired
    private SysMenuEntityMapper sysMenuMapper;

    /**
     * 通过id查找
     */
    public SysMenuEntity queryByMenuId(Long menuId) {
        return sysMenuMapper.selectByPrimaryKey(menuId);
    }
}
