package com.example.cs496_week4.Retrofit.Data;

public class Output__apptInvite {

    private String[] names;
    private String error;

    public String[] getNames() {
        return names.clone();
    }

    public void setNames(String[] names) {
        if(names != null)
            this.names = names.clone();
        else this.names = new String[]{};
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RepoOutputApptInvite{" +
                "names0='" + names[0] + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
