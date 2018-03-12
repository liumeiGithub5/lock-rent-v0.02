package com.yifushidai.entity;

public class WeeksDetailEntity {
    private Long id;

    private String mac;

    private String years;

    private String weeks;

    private Double savetimes;

    public WeeksDetailEntity() {
    }

    public WeeksDetailEntity(Double savetimes, String mac) {
        this.savetimes = savetimes;
        this.mac = mac;
    }

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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public Double getSavetimes() {
        return savetimes;
    }

    public void setSavetimes(Double savetimes) {
        this.savetimes = savetimes;
    }
}