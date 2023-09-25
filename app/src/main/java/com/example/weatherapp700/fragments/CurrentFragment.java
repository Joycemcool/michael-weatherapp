package com.example.weatherapp700.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp700.R;
import com.example.weatherapp700.models.Weather;

import java.math.BigDecimal;
import java.util.HashMap;

public class CurrentFragment extends Fragment {
    View view;
    Weather weather;

    public CurrentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_current, container, false);

        // Code goes here...

        weather = (Weather)getArguments().getSerializable("weather");

        if(weather != null)
        {
            // Display the temperature
            TextView textViewTemperature = view.findViewById(R.id.textViewTemperature);
            String temperature = getFloatRoundedAsString(weather.getCurrent().getTemperature()) + "°C";
            textViewTemperature.setText(temperature);

            //Display the condition description
            TextView textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewDescription.setText(weather.getCurrent().getCondition().getText());

            // Display the weather icon (remember to add Internet permissions in manifest)
            ImageView imageView = view.findViewById(R.id.imageViewIcon);
            String imageUrl = "https:" + weather.getCurrent().getCondition().getIcon();
            imageUrl = imageUrl.replace("64x64","128x128");
            Glide.with(view).load(imageUrl).into(imageView);

            // Display the Feels Like
            TextView textViewFeelsLike = view.findViewById(R.id.textViewFeelsLike);
            String feelsLike = "Feel like " + getFloatRoundedAsString(weather.getCurrent().getFeelsLike()) + "°C";
            textViewFeelsLike.setText(feelsLike);
        }

        return view;
    }



    private String getFloatRoundedAsString(float num){
        //return String.valueOf(Math.round(num));
        return BigDecimal.valueOf(num).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

}