package com.example.weatherapp.data.parser;

import com.example.weatherapp.data.models.City;
import com.example.weatherapp.data.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static City parseCityData(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray.length() > 0) {
                JSONObject cityJson = jsonArray.getJSONObject(0);
                return new City(
                        cityJson.getDouble("latitude"),
                        cityJson.getDouble("longitude")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Weather parseWeatherData(String json) {
        try {
            JSONObject weatherJson = new JSONObject(json);
            JSONArray temperatureArray = weatherJson.getJSONObject("hourly")
                    .getJSONArray("temperature_2m");
            if (temperatureArray.length() > 0) {
                return new Weather(temperatureArray.getDouble(0));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}