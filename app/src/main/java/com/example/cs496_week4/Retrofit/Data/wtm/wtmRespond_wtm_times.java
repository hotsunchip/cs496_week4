package com.example.cs496_week4.Retrofit.Data.wtm;

public class wtmRespond_wtm_times {

    private String day;
    private String[] timeRange;
    private String _id;

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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
