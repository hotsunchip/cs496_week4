package com.example.cs496_week4.Data;

import androidx.annotation.NonNull;

public class TimeTableItem {

    String name;
    String startTime;
    String endTime;
    String dates;


    public String getName() {
        return name;
    }

    public String getStarTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDates() {
        return dates;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStarTime(String starTime) {
        this.startTime = starTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "TimeTableItem{" +
                ", name='" + name + '\'' +
                ", starTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", dates='" + dates + '\'' +
                '}';
    }
}
