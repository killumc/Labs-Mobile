package com.example.weatherapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.data.api.WeatherApiService;
import com.example.weatherapp.data.api.WeatherCallback;

public class MainActivity extends AppCompatActivity implements WeatherCallback {

    private EditText userField;
    private Button mainButton;
    private TextView resultInfo;
    private WeatherApiService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        weatherService = new WeatherApiService(this);

        mainButton.setOnClickListener(v -> {
            String city = userField.getText().toString().trim();
            if (city.isEmpty()) {
                Toast.makeText(this, R.string.no_input, Toast.LENGTH_LONG).show();
            } else {
                weatherService.getWeatherForCity(city);
            }
        });
    }

    private void initViews() {
        userField = findViewById(R.id.user_field);
        mainButton = findViewById(R.id.main_button);
        resultInfo = findViewById(R.id.result_info);
    }

    @Override
    public void onWeatherReceived(double temperature) {
        runOnUiThread(() -> resultInfo.setText(String.format("Temperature: %.1f°C", temperature)));
    }

    @Override
    public void onFailure(String errorMessage) {
        runOnUiThread(() -> Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show());
    }
}