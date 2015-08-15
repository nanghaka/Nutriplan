package com.app.cryptotunnel.nutriplan.healthtips;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.SettingsActivity;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.database.WeightTrackerContract;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HealthTips extends AppCompatActivity {


    private static final String APP_SHARE_HASHTAG = "#Nutriplan app";
    private TextView words;
    private ImageView food;
    private static int counter;
    private static int icount;
    private String[] resultStrs;
    private ProgressDialog pDialog;

    private final ArrayList<String> al = new ArrayList<String>();
    private final DatabaseHandler db = new DatabaseHandler(this);
    private String[] weightArray;

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
    private int n ;
    private View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        parentLayout = findViewById(R.id.root_view);



            boolean connectionCheck = isConnectedToInternet();

        if (connectionCheck==true){
            RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
            retrieveFeedTask.execute();
        }else {
            snackBar("Check Internet Connection");
        }


        ImageButton next = (ImageButton) findViewById(R.id.next);
        ImageButton previous = (ImageButton) findViewById(R.id.previous);
        words = (TextView) findViewById(R.id.words);
        food = (ImageView) findViewById(R.id.food);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                icount++;

                try {
                    if (counter < n && counter >= 0) {
                        updatetext();
                        Log.d("SHARE", words.getText().toString());
                    } else {
                        counter = 0;
                        updatetext();
                        Log.d("SHARE", words.getText().toString());
                    }

                    if (icount < m && counter >= 0) updateImage();
                    else {
                        icount = 0;
                        updateImage();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    Log.d("array", String.valueOf(e.toString()));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.d("array", String.valueOf(e.toString()));
                    snackBar("Check Internet Connection");
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
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.d("array", String.valueOf(e.toString()));
                    snackBar("Check Internet Connection");
                }
            }
        });
    }

    private Intent createShareTips(int position) {

        final List<WeightTrackerContract> wtc = db.getAllWeights();

        for (WeightTrackerContract cn : wtc) {
            String log = " Time: " + cn.get_weight_time()+"\n"+"Weights: " + cn.get_weight();
            // Writing Contacts to log
            Log.d("History", log);
            al.add(log);
        }

        weightArray = new String[al.size()];
        weightArray = al.toArray(weightArray);
        Intent shareIntent = null;

        try {
            Log.d("SHARE", weightArray[position]);
            String sendText = weightArray[position];
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    sendText + APP_SHARE_HASHTAG);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.e("ARRAY_SHARE_BUT", e.toString());
            snackBar("Check Internet Connection");
        }


        return shareIntent;
    }

    private void updatetext() {
        words.setText(resultStrs[counter]);
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
            mShareActionProvider.setShareIntent(createShareTips(counter));
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
            startActivity(new Intent(HealthTips.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class RetrieveFeedTask extends AsyncTask<Void, Void, String[]> {

        protected String[] doInBackground(Void... urls) {
            try {
                Request request = new Request.Builder()
                        .url("http://192.168.56.1/lynda-php/nutritips.php")
                        .build();
                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();

                String jsonData = response.body().string();
                Log.d("JSON STRING_DATA", jsonData);
                return getNutritionDataFromJson(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("URL BUG", e.toString());
                snackBar("Check Internet Connection");
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(HealthTips.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected void onPostExecute(final String[] feed) {

            if (pDialog.isShowing()){
                Thread timer = new Thread(){
                    public void run(){
                        try {
                            n = feed.length;
                            updatetext();
                            sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            pDialog.dismiss();
                            //Toast.makeText(getApplicationContext(), "Connection was interrupted", Toast.LENGTH_SHORT).show();
                            snackBar("Check Internet Connection");
                        }catch (NullPointerException e){
                            e.printStackTrace();
                            pDialog.dismiss();
                            snackBar("Check Internet Connection");
//                            Toast.makeText(getApplicationContext(), "Connection was interrupted", Toast.LENGTH_SHORT).show();
                        }finally {
                            pDialog.dismiss();
                        }
                    }
                };
                timer.start();
            }
        }
    }

    private String[] getNutritionDataFromJson(String forecastJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_COMMENT = "comment";
        final String TAG_TITLE = "title";


        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
        int jsonLength = nutriArray.length();//get lenght of the jsonArray


        resultStrs = new String[jsonLength];//make string array that will temporarily store the data
        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = nutriArray.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
            String comment = c.getString(TAG_COMMENT);
            String id = c.getString(TAG_ID);

            Log.d("FEEDBA", id +" -- "+comment);

            resultStrs[i] = comment;
        }

        for (String s : resultStrs) {//testing to see if all the data was stored into the array
            Log.v("GNDFJ", "NutriData entry: " + s);
        }
        return resultStrs;//return the array of data to the doInBackGround method

    }

    private boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    private void snackBar(String message){
        Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
        snackbar.show();
    }
}


