package com.yifushidai.entity;

import java.util.Date;

public class BandLockEntity {
    private Long id;

    private String mobile;

    private Long lockId;

    private String mac;

    private String alias;

    private String addr;

    private String img;

    private String bluetooth;

    private Byte lbs; //门锁维修状态

    private Byte ls; //门锁在线状态

    private Byte les; //门锁电量状态

    private Byte cales; //卡取电设备电量状态

    private Double eprice;

    private Double pow;

    private Double savetotal;

    private Double savetoday;

    private Date bandtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        this.lockId = lockId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Byte getLbs() {
        return lbs;
    }

    public void setLbs(Byte lbs) {
        this.lbs = lbs;
    }

    public Byte getLs() {
        return ls;
    }

    public void setLs(Byte ls) {
        this.ls = ls;
    }

    public Byte getLes() {
        return les;
    }

    public void setLes(Byte les) {
        this.les = les;
    }

    public Byte getCales() {
        return cales;
    }

    public void setCales(Byte cales) {
        this.cales = cales;
    }

    public Double getEprice() {
        return eprice;
    }

    public void setEprice(Double eprice) {
        this.eprice = eprice;
    }

    public Double getPow() {
        return pow;
    }

    public void setPow(Double pow) {
        this.pow = pow;
    }

    public Double getSavetotal() {
        return savetotal;
    }

    public void setSavetotal(Double savetotal) {
        this.savetotal = savetotal;
    }

    public Double getSavetoday() {
        return savetoday;
    }

    public void setSavetoday(Double savetoday) {
        this.savetoday = savetoday;
    }

    public Date getBandtime() {
        return bandtime;
    }

    public void setBandtime(Date bandtime) {
        this.bandtime = bandtime;
    }
}