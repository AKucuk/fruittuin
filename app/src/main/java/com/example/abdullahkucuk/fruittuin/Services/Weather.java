package com.example.abdullahkucuk.fruittuin.Services;

import android.content.Context;
import android.content.res.Resources;

import com.example.abdullahkucuk.fruittuin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by abdullah.kucuk on 12-11-2016.
 */

public class Weather {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    Context context;
    float temperature;

    public float getTemperature(){
        return temperature;
    }
    public float getCelcius() {
        return temperature - 273.15f;
    }

    public Weather(Context context, String location) {
        this.context = context;

        String data = getWeatherData(location);
        try {
            JSONObject json = new JSONObject(data);
            JSONObject weather = getObject("main", json);
            //return getFloat("temp", weather);
            temperature = getFloat("temp", weather);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            //Context context = promptTemperatureFragment.getContext();
            String appId = context.getString(R.string.OpenWeatherAppId);
            //String appId = Resources.getSystem().getString(R.string.OpenWeatherAppId);
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

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }
}
