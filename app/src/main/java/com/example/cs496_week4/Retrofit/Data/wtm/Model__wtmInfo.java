package com.example.cs496_week4.Retrofit.Data.wtm;

import androidx.annotation.NonNull;

import com.example.cs496_week4.Retrofit.Data.wtm.wtmInfo_owner;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmInfo_personalResponses;

public class Model__wtmInfo {

    private wtmInfo_owner owner;
    private String wtmName;
    private String[] dateRange;
    private String startTime;
    private String endTime;
    private wtmInfo_personalResponses[] responses;
    private String[] invited;
    private wtmInfo_personalResponses personalResponses;
    private boolean isOwner;
    private String error;

    public wtmInfo_owner getOwner() {
        return owner;
    }

    public void setOwner(wtmInfo_owner owner) {
        this.owner = owner;
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

    public void setDateRange(@NonNull String[] dateRange) {
        this.dateRange = dateRange.clone();
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

    public wtmInfo_personalResponses[] getResponses() {
        return responses.clone();
    }

    public void setResponses(wtmInfo_personalResponses[] responses) {
        if(responses != null)
            this.responses = responses.clone();
        else this.responses = new wtmInfo_personalResponses[]{};
    }

    public String[] getInvited() {
        return invited.clone();
    }

    public void setInvited(String[] invited) {
        if(invited != null)
            this.invited = invited.clone();
        else this.invited = new String[]{};
    }

    public wtmInfo_personalResponses getPersonalResponses() {
        return personalResponses;
    }

    public void setPersonalResponses(wtmInfo_personalResponses personalResponses) {
        this.personalResponses = personalResponses;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
