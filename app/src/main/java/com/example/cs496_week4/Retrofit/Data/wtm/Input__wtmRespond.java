package com.example.cs496_week4.Retrofit.Data.wtm;

public class Input__wtmRespond {

    private int wtmId;
    private wtmRespond_times[] times;

    public Input__wtmRespond(int wtmId, wtmRespond_times[] times) {
        this.wtmId = wtmId;
        this.times = times;
    }

    public int getWtmId() {
        return wtmId;
    }

    public void setWtmId(int wtmId) {
        this.wtmId = wtmId;
    }

    public wtmRespond_times[] getTimes() {
        return times.clone();
    }

    public void setTimes(wtmRespond_times[] times) {
        if(times != null)
            this.times = times.clone();
        else this.times = new wtmRespond_times[]{};
    }
}
