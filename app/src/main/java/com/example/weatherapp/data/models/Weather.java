package com.example.weatherapp.data.models;

public class Weather {
    private final double temperature;

    public Weather(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}