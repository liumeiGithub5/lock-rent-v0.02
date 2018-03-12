package com.yifushidai.utils;

import com.yifushidai.config.TimeServerHandler;
import org.apache.mina.core.session.IoSession;

import java.util.Map;

/**
 * Created by liumei on 2017/8/31 16:47.
 * desc:2.主动 推送 给锁端
 */
public class MinaPushUtils {
    //主动 push
    public static void push (String mac,Map<Object,Object> subMap) {
        for(Map.Entry<String, IoSession> ioSessionMap: TimeServerHandler.sessionsConcurrentHashMap.entrySet()){
            //System.out.println("ioSessionMap.getKey()========"+ioSessionMap.getKey());
            //System.out.println("ioSessionMap.getValue============="+ioSessionMap.getValue());
           if(ioSessionMap.getKey().equalsIgnoreCase(mac)){
               IoSession ioSession = ioSessionMap.getValue();
               ioSession.write(subMap);
           }
           //门锁掉线，异常处理

           else{

           }
        }
    }
}
