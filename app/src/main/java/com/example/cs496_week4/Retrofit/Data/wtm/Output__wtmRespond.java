package com.example.cs496_week4.Retrofit.Data.wtm;

public class Output__wtmRespond {

    private wtmRespond_wtm[] wtm;
    private String error;

    public wtmRespond_wtm[] getWtm() {
        return wtm.clone();
    }

    public void setWtm(wtmRespond_wtm[] wtm) {
        if(wtm != null)
            this.wtm = wtm.clone();
        else this.wtm = new wtmRespond_wtm[]{};
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
