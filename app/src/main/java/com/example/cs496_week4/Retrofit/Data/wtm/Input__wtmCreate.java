package com.example.cs496_week4.Retrofit.Data.wtm;

public class Input__wtmCreate {

    private String wtmName;
    private String[] dateRange;
    private String startTime;
    private String endTime;
    private String[] invitedUsers;

    public Input__wtmCreate(String wtmName, String[] dateRange, String startTime, String endTime, String[] invitedUsers) {
        this.wtmName = wtmName;
        if(dateRange != null)
            this.dateRange = dateRange.clone();
        else this.dateRange = new String[]{};
        this.startTime = startTime;
        this.endTime = endTime;
        if(invitedUsers != null)
            this.invitedUsers = invitedUsers.clone();
        else this.invitedUsers = new String[]{};
    }

    public String getWtmName() {
        return wtmName;
    }

    public void setWtmName(String wtmName) {
        this.wtmName = wtmName;
    }

    public String[] getDateRange() {
        return dateRange.clone();
    }

    public void setDateRange(String[] dateRange) {
        if(dateRange != null)
            this.dateRange = dateRange.clone();
        else this.dateRange = new String[]{};
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getInvitedUsers() {
        return invitedUsers.clone();
    }

    public void setInvitedUsers(String[] invitedUsers) {
        if(invitedUsers != null)
            this.invitedUsers = invitedUsers.clone();
        else this.invitedUsers = new String[]{};
    }
}
