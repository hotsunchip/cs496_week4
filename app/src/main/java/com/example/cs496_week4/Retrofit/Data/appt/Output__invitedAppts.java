package com.example.cs496_week4.Retrofit.Data.appt;

public class Output__invitedAppts {

    private int[] appts;

    public int[] getAppts() {
        return appts.clone();
    }

    public void setAppts(int[] appts) {
        this.appts = appts.clone();
    }
}
