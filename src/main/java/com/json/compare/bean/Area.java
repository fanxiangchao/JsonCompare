package com.json.compare.bean;

/**
 * Created by Administrator on 2016/8/21.
 */
public class Area {

    private int areaCode;

    private String areaCountry;

    private String areaCity;

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCountry() {
        return areaCountry;
    }

    public void setAreaCountry(String areaCountry) {
        this.areaCountry = areaCountry;
    }

    public String getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaCode=" + areaCode +
                ", areaCountry='" + areaCountry + '\'' +
                ", areaCity='" + areaCity + '\'' +
                '}';
    }
}
