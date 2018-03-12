package com.yifushidai.entity;

import java.util.Date;

public class PowerEntity {
    private Long id;

    private String mac;

    private Integer typ;

    private Date powerTime;

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

    public Integer getTyp() {
        return typ;
    }

    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    public Date getPowerTime() {
        return powerTime;
    }

    public void setPowerTime(Date powerTime) {
        this.powerTime = powerTime;
    }
}