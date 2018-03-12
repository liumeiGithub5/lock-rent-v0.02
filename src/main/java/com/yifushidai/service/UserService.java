package com.yifushidai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yifushidai.entity.BandLockEntity;
import com.yifushidai.entity.RemarkEntity;
import com.yifushidai.entity.TokenEntity;
import com.yifushidai.entity.UserEntity;
import com.yifushidai.mapper.BandLockEntityMapper;
import com.yifushidai.mapper.RemarkEntityMapper;
import com.yifushidai.mapper.TokenEntityMapper;
import com.yifushidai.mapper.UserEntityMapper;
import com.yifushidai.utils.ErrorCodeConstant;
import com.yifushidai.utils.MobCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liumei on 2017/8/23 11:46.
 * desc:
 */
@Service
public class UserService {
    @Autowired
    private UserEntityMapper userMapper;
    @Autowired
    private BandLockEntityMapper lockMapper;
    @Autowired
    private TokenEntityMapper tokenMapper;
    @Autowired
    private RemarkEntityMapper remarkMapper;

    public int login(String mobile, String checkCode,String appType) throws Exception {
       Boolean mflag = false;
       Boolean cflag = false;
        switch (mobile){
            case("18628107232"):mflag = true;break;
           // case("17602831583"):mflag = true;break;
            default:mflag = false;break;
        }
       if(checkCode.equalsIgnoreCase("1234")){
           cflag = true;
       }
        //万能测试账号
        if(mflag && cflag){
            UserEntity user = userMapper.selectByMobile(mobile);
            if(user != null){
                user.setApptype(appType);
                userMapper.updateByPrimaryKeySelective(user);
                return ErrorCodeConstant.SUCCESS;
            }
            else{
                user = new UserEntity();
                user.setMobile(mobile);
                user.setApptype(appType);
                userMapper.insertSelective(user);
                return ErrorCodeConstant.SUCCESS;
            }

        }
        /*较验验证码*/
        if(!mflag){
            String result = MobCheck.status(mobile,checkCode);
            JSONObject parse = (JSONObject) JSON.parse(result);
            Integer resultCode = (Integer) parse.get("status");
            if (resultCode ==200) {
                UserEntity user = userMapper.selectByMobile(mobile);
                if(user != null){
                    user.setApptype(appType);
                    userMapper.updateByPrimaryKeySelective(user);
                }
                if(user == null){
                    user = new UserEntity();
                    user.setMobile(mobile);
                    user.setApptype(appType);
                    userMapper.insertSelective(user);
                }
                return ErrorCodeConstant.SUCCESS;
            }
            return ErrorCodeConstant.CHECK_CODE_WRONG;
        }
        return ErrorCodeConstant.TESTACCOUNTWRONG;
    }

    public int modMobile(String mb,String newMobile) {
        UserEntity user = userMapper.selectByMobile(mb);
        user.setMobile(newMobile);
        int i = userMapper.updateByPrimaryKeySelective(user);
        //所有包含mobile 的表格 都需要换手机号。。。user bandlock token remark
        List<BandLockEntity> locks = lockMapper.queryByMobile(mb);
        for(BandLockEntity lock:locks){
            lock.setMobile(newMobile);
             lockMapper.updateByPrimaryKeySelective(lock);
        }

        TokenEntity token = tokenMapper.selectByPrimaryKey(mb);
        int h = tokenMapper.deleteByPrimaryKey(mb);
        token.setMobile(newMobile);
        int w = tokenMapper.insertSelective(token);
        List<RemarkEntity> remarks = remarkMapper.queryByMobile(mb);
        for(RemarkEntity remark:remarks){
            remark.setMobile(newMobile);
            remarkMapper.updateByPrimaryKeySelective(remark);
        }
        if((i>0) && (w>0) ){
            return ErrorCodeConstant.SUCCESS;
        }else{
            return ErrorCodeConstant.SYSTEM_ERROR;
        }
    }

}
