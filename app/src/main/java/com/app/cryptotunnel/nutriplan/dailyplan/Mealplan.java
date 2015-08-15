package com.app.cryptotunnel.nutriplan.dailyplan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cryptotunnel.nutriplan.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mealplan extends Fragment implements  View.OnClickListener{


    private ImageButton next;
    private ImageButton previous;
    private TextView dayoftheweek;
    private TextView breakfastFood;
    private TextView lunchFood;
    private TextView dinnerFood;
    private final String[] days ={
      "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    private int counter = 0;
    private ProgressDialog pDialog;
    private String[] breakfastArray;
    private String[] lunchArray;
    private String[] dinnerArray;
    private int n ;
    private View rootView;
    private RetrieveFeedTask retrieveFeedTask;

    @Override
    public void onResume() {
        super.onResume();
        //startInternetConnection();
    }

    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		 rootView = inflater.inflate(R.layout.meal_plan, container, false);

        startInternetConnection();

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
                    break;

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            Log.d("Meal plan", "ARRAYBUG" + e.toString());
        } catch (NullPointerException e){
            e.printStackTrace();
            Log.d("Meal plan", "ARRAYBUG" + e.toString());
           // Toast.makeText(getActivity().getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
            snackBar("Check Internet Connection");
        }
    }

    private void updatetext () {
        breakfastFood.setText(breakfastArray[counter]);
        lunchFood.setText(lunchArray[counter]);
        dinnerFood.setText(dinnerArray[counter]);
        dayoftheweek.setText(days[counter]);
//        if (pDialog.isShowing()){
//            pDialog.dismiss();
//        }
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, Integer> {

        protected Integer doInBackground(String... urls) {
            try {
                Request request = new Request.Builder().url(urls[0]).build();
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

        protected void onPostExecute(final Integer feed) {

            try {
                n = feed;
                updatetext();

//                if (pDialog.isShowing()) {
//                    pDialog.dismiss();
//                }


                if (pDialog.isShowing()){
                    Thread timer = new Thread(){
                        public void run(){
                            try {
                                sleep(1000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "Connection was interrupted", Toast.LENGTH_SHORT).show();
//                        }catch (NullPointerException e){
//                            e.printStackTrace();
//                            //Snackbar.make(rootView, "Check Internet Connection", Snackbar.LENGTH_SHORT).show();
//                            pDialog.dismiss();
////                            Toast.makeText(getActivity(), "Connection was interrupted", Toast.LENGTH_SHORT).show();
                            }finally {
                                pDialog.dismiss();
                            }
                        }
                    };
                    timer.start();
                }

            }catch (NullPointerException e){
                e.printStackTrace();
                snackBar("Check Internet Connection");
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
        }
        return breakfastArray.length;//return the array of data to the doInBackGround method
    }

    private boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
        snackbar.show();
    }

    private void startInternetConnection(){

        //getting users input from settings screen
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String input = prefs.getString(getString(R.string.pref_age_key), getString(R.string.pref_default_age_key));
        String genderSettings = prefs.getString("gender", "1");
        String syncpref = prefs.getString("sync_frequency", "180");
        String physical_activity = prefs.getString("pref_physical_activity", "1");

        Log.d("PREFERENCE_DATA" ,"list value " + "#" + genderSettings + "#" + syncpref+ "#" + physical_activity+ "#" + input);

        // Toast.makeText(getActivity(), "list value "+"#"+gender+"#"+ syncpref, Toast.LENGTH_SHORT).show();


        boolean connectionCheck = isConnectedToInternet();
        retrieveFeedTask = new RetrieveFeedTask();

        if (connectionCheck==true){

            //testing what the user inserted with what information is appropriate for her age
            if (genderSettings.equals("1") && physical_activity.equals("1")) {
                retrieveFeedTask.execute("http://192.168.56.1/lynda-php/api1.php");
               // retrieveFeedTask.execute("http://192.168.56.1/lynda-php/jsontest4.php");
            } else if (genderSettings.equals("1") && physical_activity.equals("0")){
                //retrieveFeedTask.execute("http://192.168.56.1/lynda-php/api2.php");
                retrieveFeedTask.execute("http://codephillip.webatu.com/api2.php");
            }else if (genderSettings.equals("0") && physical_activity.equals("1")){
                retrieveFeedTask.execute("http://192.168.56.1/lynda-php/api3.php");
            }else if (genderSettings.equals("0") && physical_activity.equals("0")){
                retrieveFeedTask.execute("http://192.168.56.1/lynda-php/api4.php");
            }


        }else {
            //Toast.makeText(getActivity().getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
            snackBar("Check Internet Connection");
        }


    }
}
