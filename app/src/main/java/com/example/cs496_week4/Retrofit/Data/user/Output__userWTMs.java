package com.example.cs496_week4.Retrofit.Data.user;

import com.example.cs496_week4.Data.TimeTableItem;

import java.util.Arrays;

public class Output__userWTMs {

    private TimeTableItem[] owned;
    private TimeTableItem[] invited;
    private TimeTableItem[] accepted;

    public TimeTableItem[] getOwned() {
        return owned;
    }

    public TimeTableItem[] getInvited() {
        return invited;
    }

    public TimeTableItem[] getAccepted() {
        return accepted;
    }

    public void setOwned(TimeTableItem[] owned) {
        this.owned = owned;
    }

    public void setInvited(TimeTableItem[] invited) {
        this.invited = invited;
    }

    public void setAccepted(TimeTableItem[] accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Output__userWTMs{" +
                "owned=" + Arrays.toString(owned) +
                ", invited=" + Arrays.toString(invited) +
                ", accepted=" + Arrays.toString(accepted) +
                '}';
    }
}
