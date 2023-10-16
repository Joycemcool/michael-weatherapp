package com.example.weatherapp700.models;

import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("maxtemp_c")
    private float maxTemp;
    @SerializedName("mintemp_c")
    private float minTemp;
    @SerializedName("maxwind_kph")
    private float maxWind;
    @SerializedName("avghumidity")
    private float humidity;
    @SerializedName("daily_chance_of_rain")
    private int chanceRain;
    @SerializedName("totalprecip_mm")
    private float amountRain;
    @SerializedName("daily_chance_of_snow")
    private int chanceSnow;
    @SerializedName("totalsnow_cm")
    private float amountSnow;
    private Condition condition;

    public Day(float maxTemp, float minTemp, float maxWind, float humidity, int chanceRain,
               float amountRain, int chanceSnow, float amountSnow, Condition condition){
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.maxWind = maxWind;
        this.humidity = humidity;
        this.chanceRain = chanceRain;
        this.amountRain = amountRain;
        this.chanceSnow = chanceSnow;
        this.amountSnow = amountSnow;
        this.condition = condition;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxWind() {
        return maxWind;
    }

    public float getHumidity() {
        return humidity;
    }

    public int getChanceRain() {
        return chanceRain;
    }

    public float getAmountRain() {
        return amountRain;
    }

    public int getChanceSnow() {
        return chanceSnow;
    }

    public float getAmountSnow() {
        return amountSnow;
    }

    public Condition getCondition() {
        return condition;
    }
}
