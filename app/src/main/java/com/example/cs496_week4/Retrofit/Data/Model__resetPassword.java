package com.example.cs496_week4.Retrofit.Data;

public class Model__resetPassword {

    private boolean result;

    public Model__resetPassword() {
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
        return "RepoResetPassword{" +
                "result='" + result + '\'' +
                '}';
    }
}
