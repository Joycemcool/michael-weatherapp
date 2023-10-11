package com.example.weatherapp700.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private RetrofitApi api;

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RetrofitApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null){
            instance = new RetrofitClient();
        }

        return instance;
    }

    public RetrofitApi getApi() {
        return api;
    }
}
