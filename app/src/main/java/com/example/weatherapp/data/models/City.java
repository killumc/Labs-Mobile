package com.example.weatherapp.data.models;

public class City {
    private final double latitude;
    private final double longitude;

    public City(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}