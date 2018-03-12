//package com.yifushidai.shiro;
//
//import com.yifushidai.entity.SysMenuEntity;
//import com.yifushidai.entity.SysUserEntity;
//import com.yifushidai.webService.SysMenuService;
//import com.yifushidai.webService.SysRoleMenuService;
//import com.yifushidai.webService.SysUserEntityService;
//import com.yifushidai.webService.SysUserRoleService;
//import org.apache.commons.lang.StringUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
///**
// * 认证
// *
// * @author chenshun
// * @email sunlightcs@gmail.com
// * @date 2016年11月10日 上午11:55:49
// */
//public class UserRealm extends AuthorizingRealm {
//	private Logger logger = LoggerFactory.getLogger(UserRealm.class);
//
//    @Autowired
//    private SysUserEntityService sysUserService;
//    @Autowired
//    private SysMenuService sysMenuService;
//	@Autowired
//	private SysRoleMenuService sysRoleMenuService;
//	@Autowired
//	private SysUserRoleService sysUserRoleService;
//
//	//shiro的权限配置方法
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		logger.info("正在权限设置。。。。");
//		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
//		String userId = user.getUserId();
//
//		System.out.println("---------doGetAuthorizationInfo----user.toString()---------------"+user.toString());
//		System.out.println("---------doGetAuthorizationInfo(PrincipalCollection principals)---userId--------------"+userId);
//
//		List<String> permsList = null;
//
//		//系统管理员，拥有最高权限
//		if(userId.equals("1")){
//
//			List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());
//			permsList = new ArrayList<>(menuList.size());
//			for(SysMenuEntity menu : menuList){
//				permsList.add(menu.getPerms());
//			}
//			System.out.println("---------if---------------");
//		}else{
//			System.out.println("---------else---------------");
//			permsList = sysUserService.queryAllPerms(userId);
//		}
//
//		//用户权限列表
//		Set<String> permsSet = new HashSet<String>();
//		for(String perms : permsList){
//			if(StringUtils.isBlank(perms)){
//				continue;
//			}
//			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
//		}
//
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.setStringPermissions(permsSet);
//
//		logger.info("用户"+user.getUsername()+"具有的角色:"+info.getRoles());
//		logger.info("用户"+user.getUsername()+"具有的权限："+info.getStringPermissions());
//		return info;
//	}
//
//	//shiro的身份验证方法
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		logger.info("正在验证身份...");
//		String username = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
//
//        //查询用户信息
//        SysUserEntity user = sysUserService.queryByUserName(username);
//
//        //账号不存在
//        if(user == null) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//
//        //密码错误
//        if(!password.equals(user.getPassword())) {
//            throw new IncorrectCredentialsException("账号或密码不正确");
//        }
//
//        //账号锁定
//        if(user.getStatus() == 0){
//        	throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
//
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
//		logger.info("身份验证已通过...");
//        return info;
//	}
//
//}
