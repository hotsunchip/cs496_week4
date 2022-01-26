package com.example.cs496_week4.Retrofit.Data.user;

public class Output__allUsers {

    private allUsersElement[] infos;

    public Output__allUsers(allUsersElement[] infos) {
        this.infos = infos.clone();
    }

    public allUsersElement[] getInfos() {
        return infos.clone();
    }

    public void setInfos(allUsersElement[] infos) {
        this.infos = infos.clone();
    }
}
