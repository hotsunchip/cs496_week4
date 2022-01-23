package com.example.cs496_week4.Retrofit.Data.appt;

public class Model__apptAccept {

    private String error;

    public Model__apptAccept() {
        this.error = null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RepoApptAccept{" +
                "error='" + error + '\'' +
                '}';
    }
}
