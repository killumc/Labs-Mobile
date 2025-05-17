package com.example.weatherapp.data.api;

import com.example.weatherapp.data.models.City;
import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.data.parser.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.Call;

import java.io.IOException;

public class WeatherApiService {
    private static final String CITY_API_KEY = "haOPIqXJ6c4bSikWbSyOjg==hakFEvpmOXw9TLsP";
    private static final String CITY_API_URL = "https://api.api-ninjas.com/v1/city?name=";
    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast";

    private final OkHttpClient client;
    private WeatherCallback callback;

    public WeatherApiService(WeatherCallback callback) {
        this.client = new OkHttpClient();
        this.callback = callback;
    }


    public void setCallback(WeatherCallback callback) {
        this.callback = callback;
    }

    public void getWeatherForCity(String cityName) {
        Request cityRequest = new Request.Builder()
                .url(CITY_API_URL + cityName)
                .addHeader("X-Api-Key", CITY_API_KEY)
                .build();

        client.newCall(cityRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed to fetch city data: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure("City API error: " + response.code());
                    return;
                }

                String responseData = response.body().string();
                City city = JsonParser.parseCityData(responseData);
                if (city == null) {
                    callback.onFailure("City not found");
                    return;
                }

                fetchWeatherData(city);
            }
        });
    }

    private void fetchWeatherData(City city) {
        String weatherUrl = WEATHER_API_URL + "?latitude=" + city.getLatitude() +
                "&longitude=" + city.getLongitude() + "&hourly=temperature_2m&forecast_hours=1";

        Request weatherRequest = new Request.Builder()
                .url(weatherUrl)
                .build();

        client.newCall(weatherRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed to fetch weather data: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure("Weather API error: " + response.code());
                    return;
                }

                String responseData = response.body().string();
                Weather weather = JsonParser.parseWeatherData(responseData);
                if (weather != null) {
                    callback.onWeatherReceived(weather.getTemperature());
                } else {
                    callback.onFailure("Weather data not available");
                }
            }
        });
    }
}