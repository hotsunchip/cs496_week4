package com.example.cs496_week4.Retrofit.Data.wtm;

public class wtmRespond_times {

    private String day;
    private String[] timeRange;

    public wtmRespond_times(String day, String[] timeRange) {
        this.day = day;
        this.timeRange = timeRange;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String[] getTimeRange() {
        return timeRange.clone();
    }

    public void setTimeRange(String[] timeRange) {
        if(timeRange != null)
            this.timeRange = timeRange.clone();
        else this.timeRange = new String[]{};
    }
}
