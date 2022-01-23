package com.example.cs496_week4.Retrofit.Data.appt;

import androidx.annotation.NonNull;

public class Output__apptCreate {

    private String apptName;
    private float[] coordinates;
    private int apptIdentifier;

    public String getApptName() {
        return apptName;
    }

    public void setApptName(String apptName) {
        this.apptName = apptName;
    }

    public float[] getCoordinates() {
        return coordinates.clone();
    }

    public void setCoordinates(@NonNull float[] coordinates) {
        this.coordinates = coordinates.clone();
    }

    public int getApptIdentifier() {
        return apptIdentifier;
    }

    public void setApptIdentifier(int apptIdentifier) {
        this.apptIdentifier = apptIdentifier;
    }

    @Override
    public String toString() {
        return "RepoOutputApptCreate{" +
                "apptName='" + apptName + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", apptIdentifier='" + apptIdentifier + '\'' +
                '}';
    }
}
