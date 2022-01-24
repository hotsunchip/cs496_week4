package com.example.cs496_week4.Retrofit.Data.user;

import androidx.annotation.NonNull;

public class GET__userDeparture {

    private float[] departure;

    public float[] getDeparture() {
        return departure.clone();
    }

    public void setDeparture(@NonNull float[] departure) {
        this.departure = departure.clone();
    }
}
