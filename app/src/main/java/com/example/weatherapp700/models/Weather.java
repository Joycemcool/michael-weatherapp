package com.example.weatherapp700.models;

public class Weather {
    private Location location;
    private Current current;

    public Weather(Location location, Current current){
        this.location = location;
        this.current = current;
    }

    public Location getLocation() { return location; }

    public Current getCurrent() { return current; }
}
