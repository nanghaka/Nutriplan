package com.app.cryptotunnel.nutriplan.dailyplan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cryptotunnel.nutriplan.R;
import com.google.android.gms.wallet.MaskedWallet;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.OkClient;

public class Mealplan extends Fragment implements  View.OnClickListener{


    ImageButton next, previous;
    TextView dayoftheweek, breakfastFood, lunchFood, dinnerFood;
    String days[] ={
      "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    int counter = 0;
    //final int n=days.length;
    private ProgressDialog pDialog;
    String[] breakfastArray;
    String[] lunchArray;
    String[] dinnerArray;
    private int n ;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.mealplan, container, false);

        RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
        retrieveFeedTask.execute();

        dayoftheweek= (TextView) rootView.findViewById(R.id.weekDay);
        breakfastFood = (TextView) rootView.findViewById(R.id.breakfastFood);
        lunchFood = (TextView) rootView.findViewById(R.id.lunchFood);
        dinnerFood = (TextView) rootView.findViewById(R.id.dinnerFood);
        next = (ImageButton) rootView.findViewById(R.id.next);
        previous = (ImageButton) rootView.findViewById(R.id.previous);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);

		return rootView;
	}


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.next:
                    counter++;

                    if (counter < n && counter >= 0) {
                        updatetext();
                    } else {
                        counter = 0;
                        updatetext();
                    }

                    break;
                case R.id.previous:
                    counter--;


                    if (counter <= n && counter >= 0) {
                        updatetext();
                    } else counter = n;


//                        Toast.makeText(getActivity(), "In progress..", Toast.LENGTH_SHORT).show();
//                        counter--;
//                        dayoftheweek.setText(days[counter]);
                    break;


            }

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            Log.d("Meal plan", "ARRAYBUG" + e.toString());
        }
    }

    private void updatetext () {
           // Toast.makeText(getActivity(), "In progress..", Toast.LENGTH_SHORT).show();
       // HealthTips tips = new HealthTips();

        breakfastFood.setText(breakfastArray[counter]);
        lunchFood.setText(lunchArray[counter]);
        dinnerFood.setText(dinnerArray[counter]);
        dayoftheweek.setText(days[counter]);
    }


    public class RetrieveFeedTask extends AsyncTask<Void, Void, Integer> {

        protected Integer doInBackground(Void... urls) {
            try {
                Request request = new Request.Builder()
                        .url("http://10.42.0.1/lynda-php/jsontest2.php")
                        .build();
                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();

                String jsonData = response.body().string();
                Log.d("JSON STRING_DATA", jsonData);
                return getNutritionDataFromJson(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("URL BUG", e.toString());
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected void onPostExecute(Integer feed) {


            n = feed;

            updatetext();

            if (pDialog.isShowing()){
                Thread timer = new Thread(){
                    public void run(){
                        try {
                            sleep(2000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }finally {
                            pDialog.dismiss();
                        }
                    }
                };
                timer.start();
            }
        }
    }

    private int getNutritionDataFromJson(String forecastJsonStr)
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

            //resultStrs[i] = id + " - " + day + " - " + breakfast+ " - " + lunch+ " - " + dinner;
        }


        return breakfastArray.length;//return the array of data to the doInBackGround method

    }
}








//public class HealthTips extends AppCompatActivity {
//
//
//    private static final String APP_SHARE_HASHTAG = "#Nutriplan app";
//    ImageButton next, previous;
//    private TextView words;
//    private ImageView food;
//    private static int counter;
//    private static int icount;
//    String[] resultStrs;
//    private ProgressDialog pDialog;
//    ImageButton d;
//
//    ArrayList<String> al = new ArrayList<String>();
//    DatabaseHandler db = new DatabaseHandler(this);
//    String[] weightArray;
//
//
//    public int[] array = {
//            R.string.share0,
//            R.string.share1,
//            R.string.share2,
//            R.string.share3,
//            R.string.share4,
//            R.string.share5,
//            R.string.share6,
//            R.string.share7,
//            R.string.share8,
//            R.string.share9,
//            R.string.share10,
//            R.string.share11,
//            R.string.share12,
//            R.string.share13,
//            R.string.share14,
//            R.string.share15,
//            R.string.share16,
//            R.string.share17,
//            R.string.share18,
//            R.string.share19,
//            R.string.share20,
//            R.string.share21,
//            R.string.share23,
//            R.string.share22,
//            R.string.share24,
//            R.string.share25,
//            R.string.share26,
//            R.string.share27,
//            R.string.share28,
//            R.string.share29,
//            R.string.share30
//
//    };
//
//    private String sendText;
//    private final int[] image = {
//            R.drawable.food1,
//            R.drawable.food3,
//            R.drawable.food4,
//            R.drawable.food5,
//            R.drawable.food6,
//            R.drawable.food7,
//            R.drawable.food8,
//            R.drawable.food9,
//            R.drawable.food10,
//            R.drawable.food11,
//            R.drawable.food13,
//            R.drawable.food14,
//            R.drawable.food15,
//            R.drawable.food18,
//            R.drawable.food20,
//            R.drawable.food21,
//            R.drawable.food22,
//            R.drawable.food23,
//            R.drawable.food24,
//            R.drawable.food25
//    };
//    private final int m = image.length;
//    //private final int n = array.length;
//    private int n ;
//
//
//
//    //ArrayList<array> hand = new ArrayList<Card>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_health_tips);
//
//        RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
//        retrieveFeedTask.execute();
//
////        if (android.os.Build.VERSION.SDK_INT > 9) {
////            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
////            StrictMode.setThreadPolicy(policy);
////        }
//        next = (ImageButton) findViewById(R.id.next);
//        previous = (ImageButton) findViewById(R.id.previous);
//        words = (TextView) findViewById(R.id.words);
//        food = (ImageView) findViewById(R.id.food);
//
//
//        // updatetext();
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                counter++;
//                icount++;
//
////                // issue the Get request
////                TestMain2 example = new TestMain2();
////                String getResponse = null;
////                try {
////                    getResponse = example.doGetRequest("http://localhost/lynda-php/jsontest2.php");
////                } catch (IOException e) {
////                    e.printStackTrace();
////                    Log.d("URL BUG", e.toString());
////                }
////                System.out.println(getResponse);
////                Log.d("URL RESULTS", getResponse);
//
////                RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
////                retrieveFeedTask.execute();
//
//
//                try {
//                    if (counter < n && counter >= 0) {
//                        updatetext();
//                        Log.d("SHARE", words.getText().toString());
//                    } else {
//                        counter = 0;
//                        updatetext();
//                        Log.d("SHARE", words.getText().toString());
//                    }
//
//                    if (icount < m && counter >= 0) updateImage();
//                    else {
//                        icount = 0;
//                        updateImage();
//                    }
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    e.printStackTrace();
//                    Log.d("array", String.valueOf(e.toString()));
//                }
//            }
//        });
//
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                counter--;
//                icount--;
//
////                RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
////                retrieveFeedTask.execute();
//
//
//
//                try {
//                    if (counter <= n && counter >= 0) {
//                        updatetext();
//                    } else counter = n;
//
//                    if (icount <= m && icount >= 0) {
//                        updateImage();
//                    } else icount = m;
//
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    e.printStackTrace();
//                    Log.d("array", String.valueOf(e.toString()));
//                }
//
//            }
//        });
//    }
//
//    private Intent createShareTips() {
//
//        final List<WeightTrackerContract> wtc = db.getAllWeights();
//
//        for (WeightTrackerContract cn : wtc) {
//            String log = " Time: " + cn.get_weight_time()+"\n"+"Weights: " + cn.get_weight();
//            // Writing Contacts to log
//            Log.d("History", log);
//            al.add(log);
//        }
//
//        weightArray = new String[al.size()];
//        weightArray = al.toArray(weightArray);
//        // wordList = Arrays.asList(array);
//        Intent shareIntent = null;
//
//        Log.d("SHARE", weightArray[2]);
//        sendText =  weightArray[2];
//        shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT,
//                sendText + APP_SHARE_HASHTAG);
//        return shareIntent;
//    }
//
//    private void updatetext() {
//        words.setText(resultStrs[counter]);
//    }
//
//    private void updateImage() {
//        food.setImageResource(image[icount]);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_health_tips, menu);
//
//
//        MenuItem menuItem = menu.findItem(R.id.share);
//        ShareActionProvider mShareActionProvider =
//                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
//
//        // Attach an intent to this ShareActionProvider.  You can update this at any time,
//        // like when the user selects a new piece of data they might like to share.
//        if (mShareActionProvider != null) {
//            mShareActionProvider.setShareIntent(createShareTips());
//        } else {
//            Log.d("shareIntent", "Share Action Provider is null?");
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    public class RetrieveFeedTask extends AsyncTask<Void, Void, String[]> {
//
//        protected String[] doInBackground(Void... urls) {
//            try {
//                Request request = new Request.Builder()
//                        .url("http://10.42.0.1/lynda-php/jsontest2.php")
//                        .build();
//                OkHttpClient client = new OkHttpClient();
//                Response response = client.newCall(request).execute();
//
//                String jsonData = response.body().string();
//                Log.d("JSON STRING_DATA", jsonData);
//                return getNutritionDataFromJson(jsonData);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.d("URL BUG", e.toString());
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Showing progress dialog
//            pDialog = new ProgressDialog(HealthTips.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
//        }
//
//        protected void onPostExecute(String[] feed) {
//
//            for (String output: feed){
//                Log.d("OPEO", output);
//            }
//            n = feed.length;
//
//            updatetext();
//
//            if (pDialog.isShowing()){
//                Thread timer = new Thread(){
//                    public void run(){
//                        try {
//                            sleep(2000);
//                        }catch (InterruptedException e){
//                            e.printStackTrace();
//                        }finally {
//                            pDialog.dismiss();
//                        }
//                    }
//                };
//                timer.start();
//            }
//        }
//    }
//
//    private String[] getNutritionDataFromJson(String forecastJsonStr)
//            throws JSONException {
//
//        // These are the names of the JSON objects that need to be extracted.
//        final String TAG_ID = "id";
//        final String TAG_DAY = "day";
//        final String TAG_BREAKFAST = "breakfast";
//        final String TAG_TITLE = "title";
//        final String TAG_LUNCH = "lunch";
//        final String TAG_DINNER = "dinner";
//
//        JSONObject forecastJson = new JSONObject(forecastJsonStr);
//        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
//        int jsonLength = nutriArray.length();//get lenght of the jsonArray
//
//
//        resultStrs = new String[jsonLength];//make string array that will temporarily store the data
//        for(int i = 0; i < jsonLength; i++) {
//
//            // Get the JSON object representing the day
//            JSONObject c = nutriArray.getJSONObject(i);//point to a single row in the jsonArray
//            //extract individual items from the json object
//            String id = c.getString(TAG_ID);
//            String day = c.getString(TAG_DAY);
//            String breakfast = c.getString(TAG_BREAKFAST);
//            String lunch = c.getString(TAG_LUNCH);
//            String dinner = c.getString(TAG_DINNER);
//
//            Log.d("FEEDBA", id+ " "+day + " "+breakfast+ " "+lunch+ " "+dinner);
//
//            resultStrs[i] = id + " - " + day + " - " + breakfast+ " - " + lunch+ " - " + dinner;
//        }
//
//        for (String s : resultStrs) {//testing to see if all the data was stored into the array
//            Log.v("GNDFJ", "NutriData entry: " + s);
//        }
//        return resultStrs;//return the array of data to the doInBackGround method
//
//    }
//}


