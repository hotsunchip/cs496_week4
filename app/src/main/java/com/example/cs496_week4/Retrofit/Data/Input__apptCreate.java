package com.example.cs496_week4.Retrofit.Data;

import androidx.annotation.NonNull;

public class Input__apptCreate {

    private String apptName;
    private String apptStartTime;
    private float[] apptDest;

    public Input__apptCreate(String apptName, String apptStartTime, @NonNull float[] apptDest) {
        this.apptName = apptName;
        this.apptStartTime = apptStartTime;
        this.apptDest = apptDest.clone();
    }

    public String getApptName() {
        return apptName;
    }

    public void setApptName(String apptName) {
        this.apptName = apptName;
    }

    public String getApptStartTime() {
        return apptStartTime;
    }

    public void setApptStartTime(String apptStartTime) {
        this.apptStartTime = apptStartTime;
    }

    public float[] getApptDest() {
        return apptDest.clone();
    }

    public void setApptDest(@NonNull float[] apptDest) {
        this.apptDest = apptDest.clone();
    }
}
