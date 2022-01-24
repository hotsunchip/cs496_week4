package com.example.cs496_week4.Retrofit.Data.user;

public class Model__userEmailExists {

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RepoUserEmailExists{" +
                "result='" + result + '\'' +
                '}';
    }
}
