package com.example.weatherapp700.models;

import com.google.gson.annotations.SerializedName;

public class Hour {
    @SerializedName("time_epoch")
    private Long epoch;

    public Hour(Long epoch) {
        this.epoch = epoch;
    }

    public Long getEpoch() { return epoch; }

}
