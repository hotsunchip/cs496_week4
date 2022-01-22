package com.example.cs496_week4.Retrofit.Data;

public class Model__userNameExists {

    private boolean result;

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
