package com.yifushidai.utils;

import java.util.HashMap;

/**
 * Created by liumei on 2017/8/14 15:28.
 * desc:返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code",ErrorCodeConstant.SUCCESS);
        put("msg", "success");
    }

    /*默认code + 默认msg  简易error 和 OK*/
    public static R error() {
        return error(ErrorCodeConstant.SYSTEM_ERROR, "系统异常");
    }
    public static R ok() {
        return new R();
    }

    /*默认code + 自定义msg 简易error 和 OK*/
    public static R error(String msg) {
        return error(ErrorCodeConstant.SYSTEM_ERROR, msg);
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    /*自定义code + 自定义msg error*/
    public static R error(int code, String msg) {//不同类型错误 对应code不同
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
   /* public static R error(ErrorCodeEnum code, String msg) {//不同类型错误 对应code不同
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }*/


   /* public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }*/

    /*封装datas*/
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public R put(Object value) {
        super.put("datas", value);
        return this;
    }
}
