package com.example.cs496_week4.Retrofit.Data.wtm;

public class Output__wtmCreate {

    private String wtmName;
    private String wtmOwner;
    private int wtmId;

    public String getWtmName() {
        return wtmName;
    }

    public void setWtmName(String wtmName) {
        this.wtmName = wtmName;
    }

    public String getWtmOwner() {
        return wtmOwner;
    }

    public void setWtmOwner(String wtmOwner) {
        this.wtmOwner = wtmOwner;
    }

    public int getWtmId() {
        return wtmId;
    }

    public void setWtmId(int wtmId) {
        this.wtmId = wtmId;
    }

    @Override
    public String toString() {
        return "RepoOutputWtmCreate{" +
                "wtmName='" + wtmName + '\'' +
                ", wtmOwner='" + wtmOwner + '\'' +
                ", wtmId='" + wtmId + '\'' +
                '}';
    }
}
