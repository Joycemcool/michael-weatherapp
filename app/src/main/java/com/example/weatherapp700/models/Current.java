package com.example.weatherapp700.models;

import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("temp_c")
    private float temperature;

    @SerializedName("feelslike_c")
    private float feelsLike;

    private Condition condition;

    public Current(float temperature, float feelsLike, Condition condition){
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.condition = condition;
    }

    public float getTemperature() { return temperature; }

    public float getFeelsLike() { return feelsLike; }

    public Condition getCondition() { return condition; }
}
