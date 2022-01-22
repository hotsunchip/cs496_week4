package com.example.cs496_week4.Retrofit.Data;

public class Output__signUp {

    private String message;
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RepoOutputSignUp{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
