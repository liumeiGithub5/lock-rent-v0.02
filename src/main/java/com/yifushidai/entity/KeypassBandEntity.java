package com.yifushidai.entity;

import java.util.Date;

public class KeypassBandEntity {
    private Long id;

    private String mac;

    private String keypass;

    private String tempkey;

    private Byte tempstatus;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getKeypass() {
        return keypass;
    }

    public void setKeypass(String keypass) {
        this.keypass = keypass;
    }

    public String getTempkey() {
        return tempkey;
    }

    public void setTempkey(String tempkey) {
        this.tempkey = tempkey;
    }

    public Byte getTempstatus() {
        return tempstatus;
    }

    public void setTempstatus(Byte tempstatus) {
        this.tempstatus = tempstatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}