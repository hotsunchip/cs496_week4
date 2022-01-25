package com.example.cs496_week4.Retrofit.Data.user;

public class Output__Coordinate {
    private float latitude;
    private float longitutde;

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitutde(float longitutde) {
        this.longitutde = longitutde;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitutde() {
        return longitutde;
    }

    @Override
    public String toString() {
        return "RepoOutputCoordinate{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitutde + '\'' +
                '}';
    }
}