package com.yifushidai.webApi;

import com.yifushidai.entity.SysMenuEntity;
import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.service.LockService;
import com.yifushidai.utils.PageUtils;
import com.yifushidai.utils.Query;
import com.yifushidai.utils.R;
import com.yifushidai.webService.SysMenuService;
import com.yifushidai.webService.SysRoleMenuService;
import com.yifushidai.webService.SysUserEntityService;
import com.yifushidai.webService.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by liumei on 2017/6/28 10:30.
 * desc: 首页菜单栏 + 用户相关
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    private Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private SysUserEntityService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private LockService lockService;

    /**
     *获取当前用户菜单
     */
    @ResponseBody
    @GetMapping("/getMenuList")
    public R getMenuList(HttpSession session){
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        String uid = loginUser.getUserId();
        List<SysMenuEntity> menuList = new ArrayList<>();
        List<Long> menuIdList = new ArrayList<>();
        List<Long> roleIdList = sysUserRoleService.queryRoleIdListByUid(uid);
        for(Long roleId : roleIdList){
            List<Long> subMenuIdList = sysRoleMenuService.queryMenuIdListByRoleId(roleId);
            menuIdList.addAll(subMenuIdList);
        }
        for(Long menuId:menuIdList){
            SysMenuEntity menu = sysMenuService.queryByMenuId(menuId);
            menuList.add(menu);
        }
        return R.ok().put("menuList",menuList);
    }

    /**
     * 获取当前用户信息
     */
    @ResponseBody
    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpSession session){
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        return R.ok().put("loginUser",loginUser);
    }



   /*添加用户  分配权限 分配角色*/

    //禁用
    @PostMapping("/markDisable")
    @ResponseBody
    public R markDisable(@RequestBody String[] userIds, HttpSession session){
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        boolean flag = sysUserService.markDisable(userIds);
        if(flag){
            logger.info("管理员："+loginUser.getUsername()+",禁用用户："+Arrays.toString(userIds));
            return R.ok("禁用成功");
        }
        return R.error("禁用失败");
    }
    //解禁
    @PostMapping("/markNormal")
    @ResponseBody
    public R markNormal(@RequestBody String[] userIds, HttpSession session){
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        boolean flag = sysUserService.markNormal(userIds);
        if(flag){
            logger.info("管理员："+loginUser.getUsername()+",解禁用户："+Arrays.toString(userIds));
            return R.ok("解禁成功");
        }
        return R.error("解禁失败");
    }
    //查询
    @ResponseBody
    @GetMapping("/getAllUser")
    public R getAllUser(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        List<SysUserEntity> allUsers  = sysUserService.queryAllList(query);
        int total = sysUserService.queryAllTotal(query);
        PageUtils pageUtil = new PageUtils(allUsers, total, query.getLimit(), query.getPage());
        if(pageUtil != null){
            return R.ok().put("page", pageUtil);
        }
        return R.error();
    }
    //新增
    @ResponseBody
    @PostMapping("/add")
    public R add(HttpSession session, @RequestParam String username, @RequestParam String phone, @RequestParam String password){
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
       Boolean flag = sysUserService.add(username,phone,password);
       if(flag){
           logger.info("管理员："+loginUser.getUsername()+"，新增管理员用户："+username+"电话："+phone);
           return R.ok();
       }
      return R.error();
    }

    //批量删除
    @PostMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody String[] userIds , HttpSession session){
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        boolean flag = sysUserService.delete(userIds);
        if(flag){
            logger.info("管理员："+loginUser.getUsername()+",删除管理人员:"+Arrays.toString(userIds));
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }
}
