package com.app.cryptotunnel.nutriplan.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.database.BbnContract;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.database.DiaryContract;
import com.app.cryptotunnel.nutriplan.database.MealPlanContract;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ApiIntentService extends IntentService {

    private String Url;
    private DatabaseHandler db;

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
        db = new DatabaseHandler(this);

        //getting users input from settings screen
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String input = prefs.getString(getString(R.string.pref_age_key), getString(R.string.pref_default_age_key));
        String genderSettings = prefs.getString("gender", "1");
        String syncpref = prefs.getString("sync_frequency", "180");
        String physical_activity = prefs.getString("pref_physical_activity", "1");

        Log.d("PREFERENCE_DATA" ,"list value " + "#" + genderSettings + "#" + syncpref+ "#" + physical_activity+ "#" + input);

        //testing what the user inserted with what information is appropriate for her age
        if (genderSettings.equals("1") && physical_activity.equals("1")) {
            Url="http://192.168.56.1/lynda-php/api1.php";
            // retrieveFeedTask.execute("http://192.168.56.1/lynda-php/jsontest4.php");
        } else if (genderSettings.equals("1") && physical_activity.equals("0")){
            //retrieveFeedTask.execute("http://192.168.56.1/lynda-php/api2.php");
            Url="http://codephillip.webatu.com/api2.php";
        }else if (genderSettings.equals("0") && physical_activity.equals("1")){
            Url="http://192.168.56.1/lynda-php/api3.php";
        }else if (genderSettings.equals("0") && physical_activity.equals("0")){
            Url="http://192.168.56.1/lynda-php/api4.php";
        }

        try {
            int k;
            Log.d("DELETING_FROM_TABLE","started deleting$$$$$$$$$$$$$$$$$$$$");
            db.deleteAll(new BbnContract(0));
            db.deleteContact(new DiaryContract(0));
            for (k=0; k<2 ; k++){
               if (k == 0){
                   getNutritionDataFromJson(connectToServer(Url));
               } else if (k == 1){
                   getBbnFromJson(connectToServer("http://192.168.56.1/lynda-php/apibbn.php"));
               }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("URL BUG", e.toString());
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

            storeInMealPlanTable(day, breakfast, lunch, dinner);
        }

    }

    private void storeInMealPlanTable(String day, String breakfast, String lunch, String dinner){
        db = new DatabaseHandler(this);
        db.addMealPlan(new MealPlanContract(day, breakfast, lunch, dinner));
    }

    private void storeInBbnTable(String tips, String bc, String barcode, String url){
        db = new DatabaseHandler(this);
        db.addBbnData(new BbnContract(barcode,bc,tips,url));

    }

    private String connectToServer(String urlConnection) throws Exception{
        Request request = new Request.Builder().url(urlConnection).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();
        Log.d("JSON STRING_DATA", jsonData);
        return jsonData;
    }

    private void getBbnFromJson(String BbnJsonString)  throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_TIPS = "tips";
        final String TAG_BARCODE = "barcode";
        final String TAG_TITLE = "title";
        final String TAG_BC = "bc";
        final String TAG_URL = "url";

        JSONObject forecastJson = new JSONObject(BbnJsonString);
        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
        int jsonLength = nutriArray.length();//get length of the jsonArray

        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = nutriArray.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
            String id = c.getString(TAG_ID);
            String tips = c.getString(TAG_TIPS);
            String barcode = c.getString(TAG_BARCODE);
            String bc = c.getString(TAG_BC);
            String url = c.getString(TAG_URL);

            Log.d("FEEDBA", id+ " "+tips + " "+barcode+ " "+bc+ " "+url);

            storeInBbnTable(tips, bc, barcode, url);
        }

    }


}
