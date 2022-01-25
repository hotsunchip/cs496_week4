package com.example.cs496_week4.Retrofit.Data.map;

import androidx.annotation.NonNull;

public class Input__setAlarm {

    private double[] location;
    private String deviceToken;
    private String time;

    public Input__setAlarm(double[] location, String deviceToken, String time) {
        this.location = location.clone();
        this.deviceToken = deviceToken;
        this.time = time;
    }

    public double[] getLocation() {
        return location.clone();
    }

    public void setLocation(@NonNull double[] location) {
        this.location = location.clone();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
