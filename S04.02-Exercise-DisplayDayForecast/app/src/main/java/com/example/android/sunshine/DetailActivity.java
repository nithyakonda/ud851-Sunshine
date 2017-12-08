package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private static final String EXTRA_WEATHER_FOR_DAY = "EXTRA_WEATHER_FOR_DAY";

    private TextView mTvWeatherDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mTvWeatherDetail = (TextView) findViewById(R.id.tv_weather_detail);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_WEATHER_FOR_DAY)) {
            String weatherDetail = intent.getStringExtra(EXTRA_WEATHER_FOR_DAY);
            mTvWeatherDetail.setText(weatherDetail);
        }
    }
}