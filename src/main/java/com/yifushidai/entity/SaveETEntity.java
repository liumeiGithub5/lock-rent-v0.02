package com.yifushidai.entity;

import java.util.Date;

public class SaveETEntity {
    private Long id;

    private String mac;

    private Double saveetime;

    private Date createtime;

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

    public Double getSaveetime() {
        return saveetime;
    }

    public void setSaveetime(Double saveetime) {
        this.saveetime = saveetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}