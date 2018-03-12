package com.yifushidai.webService;

import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.entity.SysUserRoleEntity;
import com.yifushidai.mapper.SysUserEntityMapper;
import com.yifushidai.mapper.SysUserRoleEntityMapper;
import com.yifushidai.utils.MD5;
import com.yifushidai.utils.Query;
import com.yifushidai.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liumei on 2017/6/27 17:36.
 * desc:系统用户相关
 */
@Service
public class SysUserEntityService {
    @Autowired
    private SysUserEntityMapper sysUserMapper;
    @Autowired
    private SysUserRoleEntityMapper sysUserRoleMapper;


    /*通过用户名密码查找用户*/
    public SysUserEntity queryByusernameAndPwd(String username,String password){
        return sysUserMapper.queryByusernameAndPwd(username,password);
    }

//    /*注册*/
//    public Boolean register(String username, String password, String mobile) {//,String picked
//        SysUserEntity user = new SysUserEntity();
//        String uid = UUIDUtils.creatUUID();
//        user.setUserId(uid);
//        user.setPassword(MD5.getMd5(password));
//        user.setUsername(username);
//        user.setMobile(mobile);
//        user.setStatus((byte) 1);
//        user.setCreateTime(null);
//        int i= sysUserMapper.insertSelective(user);
//        SysUserRoleEntity userRole = new SysUserRoleEntity();
//        userRole.setId(null);
//        userRole.setUserId(uid);
////        if(picked.equals("factory")){
////            userRole.setRoleId((long) 2);
////        }
////        if(picked.equals("property")){
////            userRole.setRoleId((long) 1);
////        }
//        userRole.setRoleId((long) 1);//注册时 默认是注册为物业 数据库保留一个厂家账号 （liumei 123456）
//        int j = sysUserRoleMapper.insertSelective(userRole);
//
//        if(i>0 && j>0){
//            return true;
//        }else{
//            return false;
//        }
//    }

    /*新增*/
    public Boolean add(String username,String phone,String password) {
        SysUserEntity user = new SysUserEntity();
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));//
        user.setStatus((byte)1);
        user.setMobile(phone);
        user.setUsername(username);
        String uid = UUIDUtils.creatUUID();
        user.setUserId(uid);
        user.setPassword(MD5.getMd5(password));
        int i= sysUserMapper.insertSelective(user);
        SysUserRoleEntity userRole = new SysUserRoleEntity();
        userRole.setId(null);
        userRole.setUserId(uid);
//        if(picked.equals("factory")){
//            userRole.setRoleId((long) 2);
//        }
//        if(picked.equals("property")){
//            userRole.setRoleId((long) 1);
//        }
        userRole.setRoleId((long) 1);//注册时 默认是注册为物业 数据库保留一个厂家账号 （liumei 123456）
        int j = sysUserRoleMapper.insertSelective(userRole);
        if(i>0 && j>0){
            return true;
        }else{
            return false;
        }
    }
    /*批量删除*/
    public boolean delete(String[] userIds) {
        return sysUserMapper.delete(userIds)>0;
    }

    /*查询*/
    public List<SysUserEntity> queryAllList(Query query) {
        return sysUserMapper.queryAllList(query) ;
    }

    public int queryAllTotal(Query query) {
        return sysUserMapper.queryAllTotal(query);
    }

    /*解禁*/
    public boolean markNormal(String[] userIds) {
        return sysUserMapper.markNormal(userIds)>0;
    }
    /*禁用*/
    public boolean markDisable(String[] userIds) {
        return sysUserMapper.markDisable(userIds)>0;
    }
}
