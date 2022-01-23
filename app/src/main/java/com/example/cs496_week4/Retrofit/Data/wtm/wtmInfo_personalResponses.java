package com.example.cs496_week4.Retrofit.Data.wtm;

public class wtmInfo_personalResponses {

    private wtmInfo_personalResponses_times[] times;
    private String responder;

    public wtmInfo_personalResponses_times[] getTimes() {
        return times.clone();
    }

    public void setTimes(wtmInfo_personalResponses_times[] times) {
        if(times != null)
            this.times = times.clone();
        else this.times = new wtmInfo_personalResponses_times[]{};
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }
}
