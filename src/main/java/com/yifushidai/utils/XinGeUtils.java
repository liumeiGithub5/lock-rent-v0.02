package com.yifushidai.utils;

import com.tencent.xinge.XingeApp;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liumei on 2017/4/20 10:39.
 * desc: 信鸽 推送 工具类
 */
public class XinGeUtils {
    protected static long iosAccessId = 2200271771L;
    protected static String iosSecrectKey = "516817a0c7ed3c2da08fc2b4d1913d19";
    protected static long androidAccessId = 2100267911;
    protected static String androidSecrectKey = "7fb36bcba157fff678ea6c2656be0ed9";

    public XinGeUtils(){

    }

   /* 快捷推送  只支持通知，不支持透传  */
   public static Map<String ,Object> pushAndroidByToken(String title,String content,String token){
       Map<String ,Object> map = new LinkedHashMap<>();
       JSONObject jsonObject = XingeApp.pushTokenAndroid(androidAccessId,androidSecrectKey, title, content, token);
       Integer ret_code = Integer.parseInt(jsonObject.get("ret_code").toString());
       map.put("ret_code",ret_code);
      // System.out.println("ret_code  android"+ret_code);
       return map;
   }
    public static Map<String ,Object> pushIosByToken(String content, String token, int environment){
        Map<String ,Object> map = new LinkedHashMap<>();
        JSONObject jsonObject = XingeApp.pushTokenIos(iosAccessId,iosSecrectKey,content,token,environment);
        Integer ret_code = Integer.parseInt(jsonObject.get("ret_code").toString());
        map.put("ret_code",ret_code);
       // System.out.println("ret_code  ios"+ret_code);
        return map;
    }
    /*单用户推送通知*/
    public static boolean pushByTokenAndApptype(String appType, String alias,String xingeToken, String content, String title, int iosenvProd) {
        Map<String, Object> RMap = new LinkedHashMap<>();
        if(appType.toLowerCase().contains("ios")){
            RMap = pushIosByToken(alias + "," + content, xingeToken, iosenvProd);
            // System.out.println("ios  ret_code" + RMap.get("ret_code") );
            return (Integer) RMap.get("ret_code") == 0;
        }
        else if(appType.toLowerCase().contains("android")) {
            RMap = pushAndroidByToken(title, content, xingeToken);
           // System.out.println(" android ret_code" + RMap.get("ret_code") );
            return (Integer) RMap.get("ret_code") == 0;
        }else{
            return false;
        }
    }

}
