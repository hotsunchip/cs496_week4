package com.example.cs496_week4.Data;

public class ScheduleItem {
    String scheduleName;
    SchedulePlace schedulePlace; // Name
    String scheduleDate;
    String ScheduleTime;

    public String getScheduleOwner() {
        return ScheduleOwner;
    }

    public void setScheduleOwner(String scheduleOwner) {
        ScheduleOwner = scheduleOwner;
    }

    String ScheduleOwner;


    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public SchedulePlace getSchedulePlace() {
        return schedulePlace;
    }

    public void setSchedulePlace(SchedulePlace schedulePlace) {
        this.schedulePlace = schedulePlace;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return ScheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        ScheduleTime = scheduleTime;
    }
}
