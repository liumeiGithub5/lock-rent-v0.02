package com.yifushidai.entity;

import java.util.Date;

public class UploadHistoryEntity {
    private Integer id;

    private String operator;

    private Integer count;

    private String numrange;

    private Date uptime;

    private Byte uptype;

    public UploadHistoryEntity() {
    }

    public UploadHistoryEntity(String operator, Integer count, String numrange, Byte uptype) {
        this.operator = operator;
        this.count = count;
        this.numrange = numrange;
        this.uptype = uptype;
        this.id = null;
        this.uptime = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNumrange() {
        return numrange;
    }

    public void setNumrange(String numrange) {
        this.numrange = numrange == null ? null : numrange.trim();
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Byte getUptype() {
        return uptype;
    }

    public void setUptype(Byte uptype) {
        this.uptype = uptype;
    }
}