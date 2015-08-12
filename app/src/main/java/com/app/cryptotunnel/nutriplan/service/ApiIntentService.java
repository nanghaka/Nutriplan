package com.app.cryptotunnel.nutriplan.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ApiIntentService extends IntentService {

    String[] breakfastArray;
    String[] lunchArray;
    String[] dinnerArray;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE", "started service");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d("SERVICE", "created service");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("SERVICE", "started service in onstart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("SERVICE", "started destroyed");
        super.onDestroy();
    }


    public ApiIntentService() {
        super("ApiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            Request request = new Request.Builder().url("http://192.168.56.1/lynda-php/api1.php").build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            String jsonData = response.body().string();
            Log.d("JSON STRING_DATA", jsonData);
            getNutritionDataFromJson(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("URL BUG", e.toString());
            return;
        }

    }

    private void getNutritionDataFromJson(String forecastJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_DAY = "day";
        final String TAG_BREAKFAST = "breakfast";
        final String TAG_TITLE = "title";
        final String TAG_LUNCH = "lunch";
        final String TAG_DINNER = "dinner";

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
        int jsonLength = nutriArray.length();//get lenght of the jsonArray


        breakfastArray = new String[jsonLength];//make string array that will temporarily store the data
        lunchArray = new String[jsonLength];
        dinnerArray = new String[jsonLength];
        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = nutriArray.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
            String id = c.getString(TAG_ID);
            String day = c.getString(TAG_DAY);
            String breakfast = c.getString(TAG_BREAKFAST);
            String lunch = c.getString(TAG_LUNCH);
            String dinner = c.getString(TAG_DINNER);

            Log.d("FEEDBA", id+ " "+day + " "+breakfast+ " "+lunch+ " "+dinner);

            breakfastArray[i] = breakfast;
            lunchArray[i] = lunch;
            dinnerArray[i] = dinner;
        }

        for (String info: breakfastArray){
            Log.d("BREAKFAST", info);
        }
    }
}
