package com.yifushidai.entity;

import java.util.Date;

public class ICEntity {
    private Long icId;

    private Long lockId;

    private String mac;

    private String inno;

    private String outno;

    private String icName;

    private Byte icStatus;

    private Date updatetime;

    public Long getIcId() {
        return icId;
    }

    public void setIcId(Long icId) {
        this.icId = icId;
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

    public String getInno() {
        return inno;
    }

    public void setInno(String inno) {
        this.inno = inno;
    }

    public String getOutno() {
        return outno;
    }

    public void setOutno(String outno) {
        this.outno = outno;
    }

    public String getIcName() {
        return icName;
    }

    public void setIcName(String icName) {
        this.icName = icName;
    }

    public Byte getIcStatus() {
        return icStatus;
    }

    public void setIcStatus(Byte icStatus) {
        this.icStatus = icStatus;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}