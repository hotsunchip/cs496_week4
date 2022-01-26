package com.example.cs496_week4.Retrofit.Data.user;

import com.example.cs496_week4.Retrofit.Data.wtm.wtmRespond_times;

import java.util.List;

public class Output__userApptsDate {

    public void setOwned(List<userApptsDate_owned> owned) {
        this.owned = owned;
    }

    public void setInvited(List<userApptsDate_owned> invited) {
        this.invited = invited;
    }

    public void setAccepted(List<userApptsDate_owned> accepted) {
        this.accepted = accepted;
    }

    private List<userApptsDate_owned> owned;
    private List<userApptsDate_owned> invited;
    private List<userApptsDate_owned> accepted;

    public List<userApptsDate_owned> getOwned() {
        return owned;
    }

    public List<userApptsDate_owned> getInvited() {
        return invited;
    }

    public List<userApptsDate_owned> getAccepted() {
        return accepted;
    }
}
