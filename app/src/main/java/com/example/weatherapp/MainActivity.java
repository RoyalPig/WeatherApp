package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.fragments.CurrentFragment;
import com.example.weatherapp.fragments.ForcastFragment;
import com.example.weatherapp.models.Weather;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    CurrentFragment currentFragment;
    // forcastFramework
    ForcastFragment forcastFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setup binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String json = getJsonFromFile();

        Gson gson = new Gson();
        Weather weather = gson.fromJson(json, Weather.class);

        //Display the location
        TextView textViewLocation = binding.textViewLocation;
        textViewLocation.setText(weather.getLocation().getName());


        currentFragment = new CurrentFragment();
        forcastFragment = new ForcastFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("weather", weather);

        currentFragment = new CurrentFragment();
        currentFragment.setArguments(bundle);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, currentFragment)
                .commit();

    }

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
    private String getFloatAsString(float num){
        return BigDecimal.valueOf(num).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }
}