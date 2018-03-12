package com.yifushidai.entity;

import com.yifushidai.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class RemarkEntity {
    private Long id;

    private String mobile;

    private String remark;

    private Byte rmStatus;

    private Date createtime ;

    public RemarkEntity( String mobile, String remark) throws ParseException {
        this.id = null;
        this.mobile = mobile;
        this.remark = remark;
        this.rmStatus = 0;
        this.createtime = DateUtils.getCurrent();
    }

    public RemarkEntity() {
    }

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
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getRmStatus() {
        return rmStatus;
    }

    public void setRmStatus(Byte rmStatus) {
        this.rmStatus = rmStatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}