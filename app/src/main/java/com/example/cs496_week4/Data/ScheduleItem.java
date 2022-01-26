package com.example.cs496_week4.Data;

public class ScheduleItem {
    String scheduleName;
    String schedulePlace; // Name
    String scheduleDate;
    String ScheduleTime;


    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getSchedulePlace() {
        return schedulePlace;
    }

    public void setSchedulePlace(String schedulePlace) {
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

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "scheduleName='" + scheduleName + '\'' +
                ", schedulePlace='" + schedulePlace + '\'' +
                ", scheduleDate='" + scheduleDate + '\'' +
                ", ScheduleTime='" + ScheduleTime + '\'' +
                '}';
    }
}
