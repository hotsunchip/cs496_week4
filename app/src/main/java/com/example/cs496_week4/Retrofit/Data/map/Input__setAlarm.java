package com.example.cs496_week4.Retrofit.Data.map;

import androidx.annotation.NonNull;

public class Input__setAlarm {

    private float[] location;
    private String device;
    private String time;

    public Input__setAlarm(float[] location, String device, String time) {
        this.location = location;
        this.device = device;
        this.time = time;
    }

    public float[] getLocation() {
        return location.clone();
    }

    public void setLocation(@NonNull float[] location) {
        this.location = location.clone();
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
