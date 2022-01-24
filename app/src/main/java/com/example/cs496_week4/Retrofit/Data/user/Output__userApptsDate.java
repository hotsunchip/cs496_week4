package com.example.cs496_week4.Retrofit.Data.user;

import com.example.cs496_week4.Retrofit.Data.wtm.wtmRespond_times;

public class Output__userApptsDate {

    private userApptsDate_owned owned;
    private userApptsDate_owned[] invited;
    private userApptsDate_owned[] accepted;

    public userApptsDate_owned getOwned() {
        return owned;
    }

    public void setOwned(userApptsDate_owned owned) {
        this.owned = owned;
    }

    public userApptsDate_owned[] getInvited() {
        return invited.clone();
    }

    public void setInvited(userApptsDate_owned[] invited) {
        if(invited != null)
            this.invited = invited.clone();
        else this.invited = new userApptsDate_owned[]{};
    }

    public userApptsDate_owned[] getAccepted() {
        return accepted.clone();
    }

    public void setAccepted(userApptsDate_owned[] accepted) {
        if(accepted != null)
            this.accepted = accepted.clone();
        else this.accepted = new userApptsDate_owned[]{};
    }
}
