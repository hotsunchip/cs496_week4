package com.example.cs496_week4.Retrofit.Data;

public class Input__apptInvite {

    private int apptId;
    private String[] invitingUserNames;

    public Input__apptInvite(int apptId, String[] invitingUserNames) {
        this.apptId = apptId;
        if(invitingUserNames != null)
            this.invitingUserNames = invitingUserNames.clone();
        else this.invitingUserNames = new String[]{};
    }

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String[] getInvitingUserNames() {
        return invitingUserNames.clone();
    }

    public void setInvitingUserNames(String[] invitingUserNames) {
        if(invitingUserNames != null)
            this.invitingUserNames = invitingUserNames.clone();
        else this.invitingUserNames = new String[]{};
    }
}
