package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText user_field;
    private Button main_button;
    private TextView result_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        user_field = findViewById(R.id.user_field);
        main_button = findViewById(R.id.main_button);
        result_info = findViewById(R.id.result_info);

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_input, Toast.LENGTH_LONG).show();
                else {
                    String city = user_field.getText().toString();
                    String key = "haOPIqXJ6c4bSikWbSyOjg==hakFEvpmOXw9TLsP";
                    String url = "https://api.api-ninjas.com/v1/city?name=" + city;

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .addHeader("X-Api-Key", key)
                            .build();

                    client.newCall(request).enqueue(new okhttp3.Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                            e.printStackTrace();
                            // Обработка ошибки
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String responseData = response.body().string();

                                // Обработка успешного ответа
                                System.out.println(responseData);

                                try {
                                    JSONArray jsonArray = new JSONArray(responseData);
                                    if (jsonArray.length() > 0) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        double latitude = jsonObject.getDouble("latitude");
                                        double longitude = jsonObject.getDouble("longitude");

                                        // Теперь вы можете использовать latitude и longitude
                                        System.out.println("Latitude: " + latitude);
                                        System.out.println("Longitude: " + longitude);

                                        String url2 = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m&forecast_hours=1";

                                        // Второй запрос для получения температуры
                                        Request weatherRequest = new Request.Builder()
                                                .url(url2)
                                                .get()
                                                .build();

                                        client.newCall(weatherRequest).enqueue(new okhttp3.Callback() {
                                            @Override
                                            public void onFailure(okhttp3.Call call, IOException e) {
                                                e.printStackTrace();
                                                // Обработка ошибки
                                            }

                                            @Override
                                            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                                                if (response.isSuccessful()) {
                                                    String weatherResponseData = response.body().string();

                                                    // Обработка успешного ответа
                                                    System.out.println(weatherResponseData);

                                                    try {
                                                        JSONObject weatherJson = new JSONObject(weatherResponseData);
                                                        JSONArray temperatureArray = weatherJson.getJSONObject("hourly").getJSONArray("temperature_2m");
                                                        if (temperatureArray.length() > 0) {
                                                            double temperature = temperatureArray.getDouble(0);
                                                            System.out.println("Temperature: " + temperature);

                                                            // Пример: обновление UI
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    result_info.setText("Temperature: " + temperature + "°C");
                                                                }
                                                            });
                                                        } else {
                                                            System.out.println("No temperature data found");
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                        // Обработка ошибки парсинга JSON
                                                    }
                                                } else {
                                                    // Обработка ошибки ответа
                                                    System.out.println("Error: " + response.code());
                                                }
                                            }
                                        });
                                    } else {
                                        System.out.println("No data found");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    // Обработка ошибки парсинга JSON
                                }
                            } else {
                                // Обработка ошибки ответа
                                System.out.println("Error: " + response.code());
                            }
                        }
                    });
                }
            }
        });
    }
}
