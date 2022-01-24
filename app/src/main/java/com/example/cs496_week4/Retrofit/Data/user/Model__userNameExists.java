package com.example.cs496_week4.Retrofit.Data.user;

public class Model__userNameExists {

    private boolean result;

    public Model__userNameExists() {
        this.result = false;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RepoUserNameExists{" +
                "result='" + result + '\'' +
                '}';
    }
}
