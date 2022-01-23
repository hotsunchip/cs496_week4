package com.example.cs496_week4.Retrofit.Data.appt;

import androidx.annotation.NonNull;

public class Model__apptInfo {

    private String name;
    private String owner;
    private String startTime;
    private int identifier;
    private float[] destination;
    private apptInfo_members members;
    private String error;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public float[] getDestination() {
        return destination.clone();
    }

    public void setDestination(@NonNull float[] destination) {
        this.destination = destination.clone();
    }

    public apptInfo_members getMembers() {
        return members;
    }

    public void setMembers(apptInfo_members members) {
        this.members = members;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
