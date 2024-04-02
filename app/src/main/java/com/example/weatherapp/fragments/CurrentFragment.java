package com.example.weatherapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.models.Current;
import com.example.weatherapp.models.Location;
import com.example.weatherapp.models.Weather;


public class CurrentFragment extends Fragment {

    View view;

    Weather weather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_current,container,false);

        weather = (Weather)getArguments().getSerializable("weather");

        if(weather != null) {

            //Display the current temperature
            TextView textViewTemperature = view.findViewById(R.id.textViewTemperature);
            String temperatureVal = String.valueOf(Math.round(weather.getCurrent().getTemperature()));
            String tempText = temperatureVal + "°C";
            textViewTemperature.setText(tempText);


            // Display the weather description
            TextView textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewDescription.setText(weather.getCurrent().getCondition().getText());


            //Display the current weather icon
            ImageView imageViewIcon = view.findViewById(R.id.imageViewCurrenticon);
            String weatherIconUrl = weather.getCurrent().getCondition().getIcon();
            weatherIconUrl = "https:" + weatherIconUrl.replace("64x64", "128x128");

            // use glide to load image from API
            Glide.with(view).load(weatherIconUrl).into(imageViewIcon);

            //Display the current 'feels like'
            TextView textViewFeelsLike = view.findViewById(R.id.textViewFeelsLike);
            String feelsLikeTempVal = String.valueOf(Math.round(weather.getCurrent().getFeelsLikeTemp()));
            String FeelsLikeText = "Feels like: " + feelsLikeTempVal + "°C";
            textViewFeelsLike.setText(FeelsLikeText);

            //Display the current wind speed
            TextView textViewWindSpeed = view.findViewById(R.id.textViewWindSpeed);
            String windSpeedTempVal = String.valueOf(Math.round(weather.getCurrent().getWind_kph()));
            String WindSpeedText = "Wind Speed: " + windSpeedTempVal;
            textViewWindSpeed.setText(WindSpeedText);


            //Display the current wind direction
            TextView textViewWindDirection = view.findViewById(R.id.textViewWindDirection);
            String windDirectionText = "Wind Direction: " + weather.getCurrent().getWind_dir();
            textViewWindDirection.setText(windDirectionText);
        }

    // Inflate the layout for this fragment
        return view;
    }
}