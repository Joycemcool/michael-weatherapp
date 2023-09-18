package com.example.weatherapp700.models;

import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("temp_c")
    private float temperature;

    private Condition condition;

    public Current(float temperature, Condition condition){
        this.temperature = temperature;
        this.condition = condition;
    }

    public float getTemperature() { return temperature; }

    public Condition getCondition() { return condition; }
}
