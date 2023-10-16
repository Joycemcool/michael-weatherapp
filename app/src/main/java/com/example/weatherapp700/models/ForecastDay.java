package com.example.weatherapp700.models;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {

    private String date;
    private Day day;

    @SerializedName("hour")
    private Hour[] hours;

    public ForecastDay(String date, Day day, Hour[] hours) {
        this.date = date;
        this.day = day;
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public Day getDay() {
        return day;
    }

    public Hour[] getHours() {
        return hours;
    }
}
