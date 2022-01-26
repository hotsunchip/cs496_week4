package com.example.cs496_week4.Retrofit.Data.user;

public class userApptsDate_owned {

    private int identifier;
    private String name;
    private String place;
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "userApptsDate_owned{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
