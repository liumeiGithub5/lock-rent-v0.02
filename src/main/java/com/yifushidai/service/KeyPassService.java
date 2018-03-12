package com.yifushidai.service;

import com.yifushidai.entity.KeypassBandEntity;
import com.yifushidai.mapper.BandLockEntityMapper;
import com.yifushidai.mapper.KeypassBandEntityMapper;
import com.yifushidai.utils.DateUtils;
import com.yifushidai.utils.ErrorCodeConstant;
import com.yifushidai.utils.MinaPushUtils;
import com.yifushidai.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liumei on 2017/8/25 14:35.
 * desc:
 */
@Service
public class KeyPassService {
    @Autowired
    private KeypassBandEntityMapper keyPassMapper;
    @Autowired
    private BandLockEntityMapper lockMapper;

    public int modKey(String mac, String newKey) throws ParseException {
        if(lockMapper.queryByMac(mac).getLs() == 1){//门锁掉线时，异常处理
            return ErrorCodeConstant.ONLINE_ERROR;
        }
        KeypassBandEntity keypassEntity = keyPassMapper.queryByMac(mac);
        keypassEntity.setKeypass(newKey);
        keypassEntity.setUpdateTime(DateUtils.getCurrent());
        //推送给硬件
        Map<Object,Object> subMap = new LinkedHashMap<>();
        subMap.put("key",newKey);
        MinaPushUtils.push(mac,subMap);
        Boolean flag = keyPassMapper.updateByPrimaryKeySelective(keypassEntity)>0;
        if(flag){
           return ErrorCodeConstant.SUCCESS;
        }else{
            return ErrorCodeConstant.SYSTEM_ERROR;
        }
    }

    public String getTempKey(String mac) {
        if(lockMapper.queryByMac(mac).getLs() == 1){//门锁掉线时，异常处理
            return "513";
        }
        KeypassBandEntity keypassEntity= keyPassMapper.queryByMac(mac);
        Byte tempstatus = keypassEntity.getTempstatus();
        String temp ;
        if(tempstatus == null || tempstatus ==1 ){ //初始无临时密码 需要创建
            temp = UUIDUtils.creatRandomNums(4);//4位 随机数
            keypassEntity.setTempkey(temp);
            keypassEntity.setTempstatus((byte) 0);
            keyPassMapper.updateByPrimaryKeySelective(keypassEntity);
        }
        else{ //未使用时
            temp = keypassEntity.getTempkey();
        }
        //推送给 硬件
        Map<Object,Object> subMap = new LinkedHashMap<>();
        subMap.put("tempKey",temp);
        MinaPushUtils.push(mac,subMap);
        return temp;
    }

   /* public boolean keypassUpdate(String mac, String keypass) throws ParseException {
        KeypassBandEntity keypassEntity = keyPassMapper.queryByMac(mac);
        keypassEntity.setKeypass(keypass);
        keypassEntity.setUpdateTime(DateUtils.getCurrent());
        return  keyPassMapper.updateByPrimaryKeySelective(keypassEntity)>0;
    }

    public String getTempKey( String mac) {
        KeypassBandEntity keypassEntity= keyPassMapper.queryByMac(mac);
        return keypassEntity.getTempkey();
    }*/



}
