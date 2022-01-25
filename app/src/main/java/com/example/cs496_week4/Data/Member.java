package com.example.cs496_week4.Data;

public class Member {
    private String memName;
    private String memEmail;

    public Member(String name, String email) {
        this.memName = name;
        this.memEmail = email;
    }

    public String getMemName() {
        return memName;
    }

    public String getMemEmail() {
        return memEmail;
    }
}
