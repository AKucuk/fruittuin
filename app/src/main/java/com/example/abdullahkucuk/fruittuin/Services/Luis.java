package com.example.abdullahkucuk.fruittuin.Services;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.example.abdullahkucuk.fruittuin.Helpers.UrlHelper;
import com.example.abdullahkucuk.fruittuin.Models.IntentModel;
import com.example.abdullahkucuk.fruittuin.Models.LuisEntityModel;
import com.example.abdullahkucuk.fruittuin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by abdullah.kucuk on 16-10-2016.
 */
public class Luis {
    Context context;
    String result;

    public String getResult(){
        return result;
    }

    public List<LuisEntityModel> getEntities() {
        List<LuisEntityModel> list = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(result);
            JSONArray entities = json.getJSONArray("entities");
            for (int i = 0; i < entities.length(); i++) {
                JSONObject entity = entities.getJSONObject(i);

                String entityName = entity.getString("entity");
                String type = entity.getString("type");
                int startIndex = entity.getInt("startIndex");
                int endIndex = entity.getInt("endIndex");
                double score = entity.getDouble("score");

                list.add(new LuisEntityModel(entityName, type, startIndex, endIndex, score));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return list;
    }

    public List<IntentModel> getIntents() {
        List<IntentModel> list = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(result);
            JSONArray intents = json.getJSONArray("intents");
            for (int i = 0; i < intents.length(); i++) {
                String intent = intents.getJSONObject(i).getString("intent");
                double score = intents.getJSONObject(i).getDouble("score");
                list.add(new IntentModel(intent, score));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
    public IntentModel getBestIntent() {
        List<IntentModel> intents = getIntents();
        if(intents.isEmpty())
            return null;
        IntentModel bestIntent = intents.stream().max(new Comparator<IntentModel>() {
            @Override
            public int compare(IntentModel o1, IntentModel o2) {
                return (int)(o1.getScore() - o2.getScore());
            }
        }).get();

        return bestIntent;
    }

    public Luis(Context context, String query) {
        this.context = context;

        String url = null;
        try {
            url = CreateUrl(query);
            result = UrlHelper.getUrlContent(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String CreateUrl(String query) throws UnsupportedEncodingException {
        String baseUrl = context.getString(R.string.LuisBaseUrl);
        String applicationId = context.getString(R.string.LuisApplicationId);
        String subscriptionKey = context.getString(R.string.LuisSubscriptionKey);

        String luisUrl = baseUrl + "?id=" + applicationId + "&subscription-key=" + subscriptionKey;
        String url = luisUrl + "&q=" + URLEncoder.encode(query, "UTF-8");
        return url;
    }
}
