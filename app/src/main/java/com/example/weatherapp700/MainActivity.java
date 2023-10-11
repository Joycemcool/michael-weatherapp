package com.example.weatherapp700;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp700.databinding.ActivityMainBinding;
import com.example.weatherapp700.fragments.CurrentFragment;
import com.example.weatherapp700.fragments.ForecastFragment;
import com.example.weatherapp700.models.Location;
import com.example.weatherapp700.models.Weather;
import com.example.weatherapp700.retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    View view;

    private CurrentFragment currentFragment;
    private ForecastFragment forecastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);

        NavigationBarView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //
        // Retrofit client to get weather
        //
        String currentLocation = "44.6671142,-63.6075769";

        // Create and initialize the Api client
        Call<Weather> call = RetrofitClient.getInstance().getApi().getWeather(
                "62c7fc37d6b04ecaa6d225851231108",
                currentLocation,
                "3",
                "no",
                "no");

        // Make the Api call
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();

                //
                // Setup Fragments
                //

                if(weather != null){

                    // Update the location in Activity Layout
                    DisplayLocation(weather.getLocation());

                    // Add weather object to Bundle
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("weather", weather);

                    // Create fragment and pass bundle as an argument
                    currentFragment = new CurrentFragment();
                    currentFragment.setArguments(bundle);

                    forecastFragment = new ForecastFragment();

                    bottomNavigationView.setSelectedItemId(R.id.navigation_current);
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.i("TESTING", "Error: " + t.toString());
            }
        });


        /*String json = getJsonFromFile();
        // Use GSON to parse the json string
        Gson gson = new Gson();
        Weather weather = gson.fromJson(json, Weather.class);*/



        //
        // Setup Bottom Navigation View
        //

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if(itemId == R.id.navigation_current){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, currentFragment)
                            .commit();
                    return true;
                }

                if(itemId == R.id.navigation_forecast){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, forecastFragment)
                            .commit();
                    return true;
                }

                return false;
            }
        });


    }

    private void DisplayLocation(Location location){
        // Display the Location
        String[] locationArray = getResources().getStringArray(R.array.provinces);
        HashMap<String, String> locationHash = getHashFromStringArray(locationArray);
        String region = location.getRegion();
        String abbrev = locationHash.get(region);

        TextView textViewLocation = view.findViewById(R.id.textViewLocation);
        String fullLocation = location.getName() + ", " + abbrev;
        textViewLocation.setText(fullLocation);
    }

    // Convert province string array into map<k,v>.
    private HashMap<String, String> getHashFromStringArray(String[] array) {
        HashMap<String, String> result = new HashMap<>();
        for (String str : array) {
            // e.g. ON,Ontario
            String[] splitItem = str.split(",");
            result.put(splitItem[1], splitItem[0]);
        }
        return result;
    }


    // Get JSON string from .json file
    private String getJsonFromFile() {
        String json = "";

        InputStream inputStream = this.getResources().openRawResource(R.raw.weather_api);

        // Create InputStreamReader object
        InputStreamReader isReader = new InputStreamReader(inputStream);

        // Create a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);

        // Read the buffer and save to string
        json = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        return json;
    }
}