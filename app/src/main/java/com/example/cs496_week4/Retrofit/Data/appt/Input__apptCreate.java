package com.example.cs496_week4.Retrofit.Data.appt;

import androidx.annotation.NonNull;

public class Input__apptCreate {

    private String apptName;
    private String apptStartTime;
    private String apptDest;

    public Input__apptCreate(String apptName, String apptStartTime, String apptDest) {
        this.apptName = apptName;
        this.apptStartTime = apptStartTime;
        this.apptDest = apptDest;
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

    public String getApptDest() {
        return apptDest;
    }

    public void setApptDest(String apptDest) {
        this.apptDest = apptDest;
    }
}
