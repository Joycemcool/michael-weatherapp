package com.example.weatherapp700.models;

import java.io.Serializable;

public class Weather implements Serializable {
    private Location location;
    private Current current;
    private Forecast forecast;

    public Weather(Location location, Current current, Forecast forecast){
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public Location getLocation() { return location; }

    public Current getCurrent() { return current; }

    public Forecast getForecast() { return forecast; }
}
