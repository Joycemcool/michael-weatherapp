package com.example.weatherapp700.models;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    @SerializedName("forecastday")
    private ForecastDay[] forecastDays;

    public Forecast(ForecastDay[] forecastDays){
        this.forecastDays = forecastDays;
    }

    public ForecastDay[] getForecastDays() { return forecastDays; }
}
