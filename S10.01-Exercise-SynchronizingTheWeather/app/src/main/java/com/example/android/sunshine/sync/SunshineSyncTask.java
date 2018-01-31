package com.example.android.sunshine.sync;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SunshineSyncTask{
    synchronized public static void syncWeather(Context context) {
        try {
            URL url = NetworkUtils.getUrl(context);
            String responseJson = NetworkUtils.getResponseFromHttpUrl(url);

            ContentValues[] weatherData = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, responseJson);

            if (weatherData != null && weatherData.length != 0) {
                ContentResolver contentResolver = context.getContentResolver();
                contentResolver.delete(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null);

                contentResolver.bulkInsert(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        weatherData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}