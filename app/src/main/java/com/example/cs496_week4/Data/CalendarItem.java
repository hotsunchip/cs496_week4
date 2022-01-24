package com.example.cs496_week4.Data;

public class CalendarItem {
    private String cd_date = ""; // 날짜
    private String cd_day = ""; // 요일

    public CalendarItem(String date, String day) {
        this.cd_date = date;
        this.cd_day = day;
    }

    public String getCd_date() {
        return cd_date;
    }
    public String getCd_day() {
        return cd_day;
    }
}
