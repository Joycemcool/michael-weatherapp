package com.example.weatherapp700.retrofit;

import com.example.weatherapp700.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    // This is the hostname for the API
    String BASE_URL = "https://api.weatherapi.com";

    // API call to get the forecast from the Api
    @GET("v1/forecast.json")
    Call<Weather> getWeather(@Query("key") String key,
                             @Query("q") String q,
                             @Query("days") String days,
                             @Query("aqi") String aqi,
                             @Query("alerts") String alerts
    );

}
