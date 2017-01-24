package com.example.abdullahkucuk.fruittuin.Services;

import android.content.Context;
import android.content.res.Resources;

import com.example.abdullahkucuk.fruittuin.Enumerations.WindDirection;
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
    float windDegree;

    public Weather(Context context, String location) {
        this.context = context;

        String data = getWeatherData(location);

        if (data != null) {
            try {
                JSONObject json = new JSONObject(data);
                JSONObject weather = getObject("main", json);
                //return getFloat("temp", weather);
                temperature = getFloat("temp", weather);

                JSONObject wind = getObject("wind", json);
                windDegree = getFloat("deg", wind);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    public WindDirection getWindDirection()
    {
        WindDirection directions[] = {
                WindDirection.NORTH,
                WindDirection.NORTH_EAST,
                WindDirection.EAST,
                WindDirection.SOUTH_EAST,
                WindDirection.SOUTH,
                WindDirection.SOUTH_WEST,
                WindDirection.WEST,
                WindDirection.NORTH_WEST,
                WindDirection.NORTH
        };
        return directions[ (int)Math.round((  ((double)windDegree % 360) / 45)) ];
    }

    public WindDirection getRoughWindDirection()
    {
        WindDirection directions[] = {
                WindDirection.NORTH,
                WindDirection.EAST,
                WindDirection.EAST,
                WindDirection.SOUTH,
                WindDirection.SOUTH,
                WindDirection.WEST,
                WindDirection.WEST,
                WindDirection.NORTH,
                WindDirection.NORTH
        };
        return directions[ (int)Math.round((  ((double)windDegree % 360) / 45)) ];

    }

    public float getKelvin(){
        return temperature;
    }

    public float getCelcius() {
        return temperature - 273.15f;
    }

    public float getFahreinheit() {
        return 9 * temperature / 5 + 32;
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

            while(con.getResponseCode() != 200) {
                con = (HttpURLConnection) ( new URL(BASE_URL + location + "&appid=" + appId)).openConnection();
                con.connect();
            }

            // Let's read the response
            StringBuffer buffer = new StringBuffer();

            while (con.getResponseCode() != 200) {
                con = (HttpURLConnection) (new URL(BASE_URL + location + "&appid=" + appId)).openConnection();
                con.connect();
            }

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

    public String getFormattedOutput(Float f) {
        if(f == f.longValue()) {
            return String.format("%d",f.longValue());
        }
        return String.format("%f", f);
    }
}
