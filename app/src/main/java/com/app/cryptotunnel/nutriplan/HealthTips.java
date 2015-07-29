package com.app.cryptotunnel.nutriplan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HealthTips extends AppCompatActivity {


    private static final String APP_SHARE_HASHTAG = "#Nutriplan app";
    ImageButton next, previous;
    private TextView words;
    private ImageView food;
    private static int counter;
    private static int icount;
    private final OkHttpClient client = new OkHttpClient();
    // contacts JSONArray
    JSONArray contacts = null;

//    private static final String TAG_ID = "id";
//    private static final String TAG_DAY = "day";
//    private static final String TAG_BREAKFAST = "breakfast";

    private final int[] array = {
            R.string.share0,
            R.string.share1,
            R.string.share2,
            R.string.share3,
            R.string.share4,
            R.string.share5,
            R.string.share6,
            R.string.share7,
            R.string.share8,
            R.string.share9,
            R.string.share10,
            R.string.share11,
            R.string.share12,
            R.string.share13,
            R.string.share14,
            R.string.share15,
            R.string.share16,
            R.string.share17,
            R.string.share18,
            R.string.share19,
            R.string.share20,
            R.string.share21,
            R.string.share23,
            R.string.share22,
            R.string.share24,
            R.string.share25,
            R.string.share26,
            R.string.share27,
            R.string.share28,
            R.string.share29,
            R.string.share30

    };

    private String sendText;
    private final int[] image = {
            R.drawable.food1,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food5,
            R.drawable.food6,
            R.drawable.food7,
            R.drawable.food8,
            R.drawable.food9,
            R.drawable.food10,
            R.drawable.food11,
            R.drawable.food13,
            R.drawable.food14,
            R.drawable.food15,
            R.drawable.food18,
            R.drawable.food20,
            R.drawable.food21,
            R.drawable.food22,
            R.drawable.food23,
            R.drawable.food24,
            R.drawable.food25
    };
    private final int m = image.length;
    private final int n = array.length;


    //ArrayList<array> hand = new ArrayList<Card>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        next = (ImageButton) findViewById(R.id.next);
        previous = (ImageButton) findViewById(R.id.previous);
        words = (TextView) findViewById(R.id.words);
        food = (ImageView) findViewById(R.id.food);


        updatetext();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                icount++;

//                // issue the Get request
//                TestMain2 example = new TestMain2();
//                String getResponse = null;
//                try {
//                    getResponse = example.doGetRequest("http://localhost/lynda-php/jsontest2.php");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.d("URL BUG", e.toString());
//                }
//                System.out.println(getResponse);
//                Log.d("URL RESULTS", getResponse);

                RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
                retrieveFeedTask.execute();


                try {
                    if (counter < n && counter >= 0) {
                        updatetext();
                    } else {
                        counter = 0;
                        updatetext();
                    }

                    if (icount < m && counter >= 0) updateImage();
                    else {
                        icount = 0;
                        updateImage();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    Log.d("array", String.valueOf(e.toString()));
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                icount--;


                try {
                    if (counter <= n && counter >= 0) {
                        updatetext();
                    } else counter = n;

                    if (icount <= m && icount >= 0) {
                        updateImage();
                    } else icount = m;

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    Log.d("array", String.valueOf(e.toString()));
                }

            }
        });
    }

    private Intent createShareTips() {
        // wordList = Arrays.asList(array);
        sendText = getString(array[counter]);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                sendText + APP_SHARE_HASHTAG);
        return shareIntent;

    }

    private void updatetext() {
        words.setText(array[counter]);
    }

    private void updateImage() {
        food.setImageResource(image[icount]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_tips, menu);


        MenuItem menuItem = menu.findItem(R.id.share);
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareTips());
        } else {
            Log.d("shareIntent", "Share Action Provider is null?");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    // code request code here
//    String doGetRequest(String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    public class RetrieveFeedTask extends AsyncTask<Void, Void, String[]> {

        protected String[] doInBackground(Void... urls) {
            try {
                Request request = new Request.Builder()
                        .url("http://10.10.8.155/lynda-php/jsontest2.php")
                        .build();
                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();
                Log.d("JSON STRING", response.body().string());
                //return response.body().string();
                String jsonData = response.body().string();
                return getNutritionDataFromJson(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("URL BUG", e.toString());
                return null;
            }
        }

        protected void onPostExecute(String[] feed) {
//            int i;
//            for (i=0;i<3;i++){
//                Log.d("JSON RESULT", feed[i]);
//            }



//            if (feed != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(feed);
//
//                    // Getting JSON Array node
//                    contacts = jsonObj.getJSONArray("");
//
//                    // looping through All Contacts
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//
//                        String id = c.getString(TAG_ID);
//                        String name = c.getString(TAG_DAY);
//                        String breakfast=c.getString(TAG_BREAKFAST);
//                        Log.d("FEED", id+ " "+name+ " "+breakfast);
////                        String email = c.getString(TAG_EMAIL);
////                        String address = c.getString(TAG_ADDRESS);
////                        String gender = c.getString(TAG_GENDER);
//
////                        // Phone node is JSON Object
////                        JSONObject phone = c.getJSONObject(TAG_PHONE);
////                        String mobile = phone.getString(TAG_PHONE_MOBILE);
////                        String home = phone.getString(TAG_PHONE_HOME);
////                        String office = phone.getString(TAG_PHONE_OFFICE);
//
////                        // tmp hashmap for single contact
////                        HashMap<String, String> contact = new HashMap<String, String>();
////
////                        // adding each child node to HashMap key => value
////                        contact.put(TAG_ID, id);
////                        contact.put(TAG_NAME, name);
////                        contact.put(TAG_EMAIL, email);
////                        contact.put(TAG_PHONE_MOBILE, mobile);
//
//                        // adding contact to contact list
//                       // contactList.add(contact);
//
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Log.e("ServiceHandler", "Couldn't get any data from the url");
//            }
//
//            return null;
//       }
//    }
        }
    }

    private String[] getNutritionDataFromJson(String forecastJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
//        final String OWM_LIST = "list";
//        final String OWM_WEATHER = "weather";
//        final String OWM_TEMPERATURE = "temp";
//        final String OWM_MAX = "max";
//        final String OWM_MIN = "min";
//        final String OWM_DESCRIPTION = "main";
         final String TAG_ID = "id";
         final String TAG_DAY = "day";
         final String TAG_BREAKFAST = "breakfast";
         final String TAG_TITLE = "title";

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);
        int jsonLength = nutriArray.length();

        // OWM returns daily forecasts based upon the local time of the city that is being
        // asked for, which means that we need to know the GMT offset to translate this data
        // properly.

        // Since this data is also sent in-order and the first day is always the
        // current day, we're going to take advantage of that to get a nice
        // normalized UTC date for all of our weather.
//
//        Time dayTime = new Time();
//        dayTime.setToNow();
//
//        // we start at the day returned by local time. Otherwise this is a mess.
//        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
//
//        // now we work exclusively in UTC
//        dayTime = new Time();

       // String[] resultStrs = new String[jsonLength];
        String[] resultStrs = new String[2];
        for(int i = 0; i < 2; i++) {
//            // For now, using the format "Day, description, hi/low"
//            String id;
//            String day;
//            String breakfast;

            // Get the JSON object representing the day
            JSONObject c = nutriArray.getJSONObject(i);

            String id = c.getString(TAG_ID);
            String day = c.getString(TAG_DAY);
            String breakfast=c.getString(TAG_BREAKFAST);

            Log.d("FEEDBA", id+ " "+day + " "+breakfast);

//            // The date/time is returned as a long.  We need to convert that
//            // into something human-readable, since most people won't read "1400356800" as
//            // "this saturday".
//            long dateTime;
//            // Cheating to convert this to UTC time, which is what we want anyhow
//            dateTime = dayTime.setJulianDay(julianStartDay+i);
//            day = getReadableDateString(dateTime);
//
//            // description is in a child array called "weather", which is 1 element long.
//            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
//            description = weatherObject.getString(OWM_DESCRIPTION);
//
//            // Temperatures are in a child object called "temp".  Try not to name variables
//            // "temp" when working with temperature.  It confuses everybody.
//            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
//            double high = temperatureObject.getDouble(OWM_MAX);
//            double low = temperatureObject.getDouble(OWM_MIN);

           // highAndLow = formatHighLows(high, low);
//            resultStrs[i] = id + " - " + day + " - " + breakfast;
//            Log.d("FEED", resultStrs[i]);
            String resultSt = id+ " "+day + " "+breakfast;
//            String[] resultStrs = new String[2];
//            resultStrs[i]=resultSt;
        }

        for (String s : resultStrs) {
            Log.v("GNDFJ", "Forecast entry: " + s);
        }
        return resultStrs;

    }
}


