package com.clover.constant;

/**
 * Created by lkl on 2017/3/22.
 */
public enum TokenConstant {

    HEAD_ATTRIBUTE("token"),
    UID("uid"),
    ERROR_ATTRIBUTE("errMsg");

    private String v;

    TokenConstant(String v) {
        this.v = v;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
