package com.example.weatherapp.data.api;

public interface WeatherCallback {
    void onWeatherReceived(double temperature);
    void onFailure(String errorMessage);
}