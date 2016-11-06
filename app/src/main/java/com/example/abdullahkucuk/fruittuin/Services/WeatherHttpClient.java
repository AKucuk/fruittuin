package com.example.abdullahkucuk.fruittuin.Services;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptTemperatureFragment;
import com.example.abdullahkucuk.fruittuin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TAKEN FROM: http://www.survivingwithandroid.com/2013/05/build-weather-app-json-http-android.html
 * Modified by Abdullah Kucuk & Pieter Plomp
 */
public class WeatherHttpClient extends AsyncTask<String, Void, Float> {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    PromptTemperatureFragment promptTemperatureFragment;

    public WeatherHttpClient(PromptTemperatureFragment promptTemperatureFragment) {
        this.promptTemperatureFragment = promptTemperatureFragment;
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    public float getTemperature(String location) {
        String data = getWeatherData(location);
        try {
            JSONObject json = new JSONObject(data);
            JSONObject weather = getObject("main", json);
            return getFloat("temp", weather);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            Context context = promptTemperatureFragment.getContext();
            String appId = context.getString(R.string.OpenWeatherAppId);
            con = (HttpURLConnection) ( new URL(BASE_URL + location + "&appid=" + appId)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    @Override
    protected Float doInBackground(String... params) {
        String location = params[0];
        return getTemperature(location) - 273.15f;
    }

    @Override
    protected void onPostExecute(Float f) {
        Context context = promptTemperatureFragment.getContext();
        int currentTemperatuur = Math.round(f);
        int guessedTemperatuur = Math.round(promptTemperatureFragment.guessedTemperatuur);

        if (guessedTemperatuur < currentTemperatuur) {
            Toast.makeText(context, guessedTemperatuur + "?? Het is toch niet zo koud...", Toast.LENGTH_LONG)
                    .show();
            return;
        } else if (guessedTemperatuur > currentTemperatuur) {
            Toast.makeText(context, guessedTemperatuur + "?? Het is toch niet zo warm?", Toast.LENGTH_LONG)
                    .show();
            return;
        } else {
            Toast.makeText(context, "goed", Toast.LENGTH_LONG)
                    .show();
        }
    }
}