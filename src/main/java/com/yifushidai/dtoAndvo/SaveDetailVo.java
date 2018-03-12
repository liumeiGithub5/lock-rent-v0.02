package com.yifushidai.dtoAndvo;

import com.yifushidai.entity.*;

/**
 * Created by liumei on 2017/11/20 9:51.
 * desc:
 */
public class SaveDetailVo {

    private WeeksDetailEntity weeks;

    private MonthsDetailEntity months;

    private QuartersDetailEntity quarters;

    private YearsDetailEntity years;

    private Double persent;

    public SaveDetailVo() {
    }

    public SaveDetailVo(WeeksDetailEntity weeks, Double persent) {
        this.weeks = weeks;
        this.persent = persent;
    }

    public SaveDetailVo(MonthsDetailEntity months, Double persent) {
        this.months = months;
        this.persent = persent;
    }

    public SaveDetailVo(QuartersDetailEntity quarters, Double persent) {
        this.quarters = quarters;
        this.persent = persent;
    }

    public SaveDetailVo(YearsDetailEntity years, Double persent) {
        this.years = years;
        this.persent = persent;
    }

    public WeeksDetailEntity getWeeks() {
        return weeks;
    }

    public void setWeeks(WeeksDetailEntity weeks) {
        this.weeks = weeks;
    }

    public MonthsDetailEntity getMonths() {
        return months;
    }

    public void setMonths(MonthsDetailEntity months) {
        this.months = months;
    }

    public QuartersDetailEntity getQuarters() {
        return quarters;
    }

    public void setQuarters(QuartersDetailEntity quarters) {
        this.quarters = quarters;
    }

    public YearsDetailEntity getYears() {
        return years;
    }

    public void setYears(YearsDetailEntity years) {
        this.years = years;
    }

    public Double getPersent() {
        return persent;
    }

    public void setPersent(Double persent) {
        this.persent = persent;
    }
}
