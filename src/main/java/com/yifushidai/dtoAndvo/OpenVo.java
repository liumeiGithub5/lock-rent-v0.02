package com.yifushidai.dtoAndvo;

import com.yifushidai.entity.OpenRecordEntity;

import java.util.List;

/**
 * Created by liumei on 2017/12/12 10:09.
 * desc:每天开锁记录显示类
 */
public class OpenVo {
    private String todayStr;
    private List<OpenRecordEntity> todayOpenList;

    public OpenVo() {
    }

    public OpenVo(String todayStr, List<OpenRecordEntity> todayOpenList) {
        this.todayStr = todayStr;
        this.todayOpenList = todayOpenList;
    }

    public String getTodayStr() {
        return todayStr;
    }

    public void setTodayStr(String todayStr) {
        this.todayStr = todayStr;
    }

    public List<OpenRecordEntity> getTodayOpenList() {
        return todayOpenList;
    }

    public void setTodayOpenList(List<OpenRecordEntity> todayOpenList) {
        this.todayOpenList = todayOpenList;
    }

    @Override
    public String toString() {
        return "OpenVo{" +
                "todayStr='" + todayStr + '\'' +
                ", todayOpenList=" + todayOpenList +
                '}';
    }
}
