package com.example.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class Current {

    //TAKE DA NAME 'temp_c' TO DO THE NICE NAME 'temperatureC'
    @SerializedName("temp_c")
    private float temperature;

    @SerializedName("feelslike_c")
    private float feelsLikeTemp;

    private Condition condition;

    private float wind_kph;

    private String wind_dir;

    public Current(float temperature, Condition condition, float feelsLikeTemp, float wind_kph, String wind_dir){
        this.temperature = temperature;
        this.condition = condition;
        this.feelsLikeTemp = feelsLikeTemp;
        this.wind_kph = wind_kph;
        this.wind_dir = wind_dir;
    }

    public float getTemperature(){return temperature;}

    public Condition getCondition() {return condition;}

    public float getFeelsLikeTemp() {return feelsLikeTemp;}

    public float getWind_kph() {return wind_kph;}

    public String getWind_dir() {return wind_dir;}
}