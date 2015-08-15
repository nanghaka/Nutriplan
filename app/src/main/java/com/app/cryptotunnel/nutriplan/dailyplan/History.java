package com.app.cryptotunnel.nutriplan.dailyplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.cryptotunnel.nutriplan.SettingsActivity;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.database.WeightTrackerContract;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private final String TAG = "History activity";
    private ListView listView;
    private final ArrayList<String> al = new ArrayList<String>();
    private final DatabaseHandler db = new DatabaseHandler(this);

    private String[] weightArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_android_example);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        final List<WeightTrackerContract> wtc = db.getAllWeights();

        for (WeightTrackerContract cn : wtc) {
            String log = " Time: " + cn.get_weight_time()+"\n"+"Weights: " + cn.get_weight();
            // Writing Contacts to log
            Log.d(TAG, log);
            al.add(log);
        }

        weightArray = new String[al.size()];
        weightArray = al.toArray(weightArray);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, weightArray);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(History.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
