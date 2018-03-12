package com.yifushidai.annotation;

import java.util.Map;

/**
 * 当前用户信息Holder
 * Created by hai on 2017/3/20.
 */
public class UserHolder {
    private static ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static void put(UserInfo userInfo) {
        userThreadLocal.set(userInfo);
    }

    public static UserInfo get() {
        return userThreadLocal.get();
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
