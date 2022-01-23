package com.example.cs496_week4.Retrofit.Data.appt;

import androidx.annotation.NonNull;

public class apptInfo_members {

    private String[] invited;
    private String[] accepted;
    private String[] rejected;

    public String[] getInvited() {
        return invited.clone();
    }

    public void setInvited(@NonNull String[] invited) {
        this.invited = invited.clone();
    }

    public String[] getAccepted() {
        return accepted.clone();
    }

    public void setAccepted(@NonNull String[] accepted) {
        this.accepted = accepted.clone();
    }

    public String[] getRejected() {
        return rejected.clone();
    }

    public void setRejected(@NonNull String[] rejected) {
        this.rejected = rejected.clone();
    }
}
