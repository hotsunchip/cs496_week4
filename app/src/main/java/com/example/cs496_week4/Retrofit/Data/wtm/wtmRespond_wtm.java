package com.example.cs496_week4.Retrofit.Data.wtm;

public class wtmRespond_wtm {

    private wtmRespond_wtm_times[] times;
    private String responder;
    private String _id;

    public wtmRespond_wtm_times[] getTimes() {
        return times.clone();
    }

    public void setTimes(wtmRespond_wtm_times[] times) {
        if(times != null)
            this.times = times.clone();
        else this.times = new wtmRespond_wtm_times[]{};
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
