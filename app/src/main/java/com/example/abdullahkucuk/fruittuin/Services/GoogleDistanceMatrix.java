package com.example.abdullahkucuk.fruittuin.Services;

import com.example.abdullahkucuk.fruittuin.Helpers.UrlHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by abdullah.kucuk on 6-11-2016.
 */

public class GoogleDistanceMatrix {

    public enum Status {
        OK, NOT_FOUND
    };
    public final String API_KEY = "AIzaSyDyREecCPIGn_Xg32u9T-2wFvttFHxX2oA";
    public final String BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics";

    private String origin = "Tom+Schreursweg+48,+1067+Amsterdam";
    private String destination;
    private Status status;
    private String response;
    private String distanceText;
    private long distance;
    private String durationText;
    private long duration;

    private String getFullUrl() {
        return String.format("%s&origins=%s&destinations=%s&key=%s", BASE_URL, origin, destination, API_KEY);
    }

    public Status getStatus() {
        return status;
    }
    public String getDistanceText() {
        return distanceText;
    }
    public String getDurationText() {
        return durationText;
    }
    public long getDistance() {
        return distance;
    }
    public long getDuration() {
        return duration;
    }
    public String getDestination() {
        return destination;
    }

    public GoogleDistanceMatrix(String destination) {
        this.destination = destination;

        try {
            response = UrlHelper.getUrlContent(getFullUrl());
            processResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processResponse() {
        try {
            JSONObject json = new JSONObject(response);
            JSONArray rows = json.getJSONArray("rows");
            if(rows.length() == 0) {
                return;
            }

            JSONArray elements = rows.getJSONObject(0).getJSONArray("elements");
            if(elements.length() == 0) {
                return;
            }

            JSONObject element = elements.getJSONObject(0);
            switch(element.getString("status")) {
                case "OK":
                    status = Status.OK;
                    JSONObject distance = element.getJSONObject("distance");
                    this.distance = distance.getLong("value");
                    this.distanceText = distance.getString("text");

                    JSONObject duration = element.getJSONObject("duration");
                    this.duration = duration.getLong("value");
                    this.durationText = duration.getString("text");
                    break;
                case "NOT_FOUND":
                    status = Status.NOT_FOUND;
                    break;
                default:
                    throw new Exception(String.format("Unknown status received: [%s]", element.getString("status")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
