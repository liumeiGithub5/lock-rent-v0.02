package com.yifushidai.entity;

public class QuartersDetailEntity {
    private Long id;

    private String mac;

    private String years;

    private String months;

    private String quarters;

    private Double savetimes;

    public QuartersDetailEntity() {
    }

    public QuartersDetailEntity(Double savetimes, String mac) {
        this.savetimes =savetimes;
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

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public Double getSavetimes() {
        return savetimes;
    }

    public void setSavetimes(Double savetimes) {
        this.savetimes = savetimes;
    }
}