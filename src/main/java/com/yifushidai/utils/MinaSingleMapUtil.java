package com.yifushidai.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liumei on 2017/6/17 20:52.
 * desc:mina服务器端  使用单例模式 map工具类
 */
public class MinaSingleMapUtil {
    private static Map<Object,Object> map = new LinkedHashMap();

    private MinaSingleMapUtil(){

    }
    private static class SingletonHolder {
        private static final MinaSingleMapUtil INSTANCE = new MinaSingleMapUtil();
    }

    public static MinaSingleMapUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //存取信息
    public void put(String lockMac, Map<Object,Object> subMap){
        map.put(lockMac,subMap);
    }
    public Map<Object,Object> get(){
        return map;
    }

}
