package com.example.weatherapp700;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp700.databinding.ActivityMainBinding;
import com.example.weatherapp700.fragments.CurrentFragment;
import com.example.weatherapp700.models.Weather;
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

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private CurrentFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);

        String json = getJsonFromFile();

        // Use GSON to parse the json string
        Gson gson = new Gson();
        Weather weather = gson.fromJson(json, Weather.class);

        // Display the Location
        String[] locationArray = getResources().getStringArray(R.array.provinces);
        HashMap<String, String> locationHash = getHashFromStringArray(locationArray);
        String region = weather.getLocation().getRegion();
        String abbrev = locationHash.get(region);

        TextView textViewLocation = view.findViewById(R.id.textViewLocation);
        String fullLocation = weather.getLocation().getName() + ", " + abbrev;
        textViewLocation.setText(fullLocation);

        // Setup Fragments

        Bundle bundle = new Bundle();
        bundle.putSerializable("weather", weather);

        currentFragment = new CurrentFragment();
        currentFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, currentFragment)
                .commit();
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