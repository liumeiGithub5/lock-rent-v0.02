package com.yifushidai.annotation;

/**
 * 登录用户信息
 * Created by hai on 2017/3/20.
 */
public class UserInfo /* extends LinkedHashMap<String, Object>*/ {
    private static final long serialVersionUID = 1L;

    private String mobile;
    private String token;
    private String appType;

    public UserInfo(){

    }

    public UserInfo(String mobile, String token,String appType) {
        this.mobile = mobile;
        this.token = token;
        this.appType = appType;
    }

    public UserInfo(String mobile, String token) {
        this.mobile = mobile;
        this.token = token;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!mobile.equals(userInfo.mobile)) return false;
        return token.equals(userInfo.token);
    }

    @Override
    public int hashCode() {
        int result = mobile.hashCode();
        result = 31 * result + token.hashCode();
        return result;
    }
}
