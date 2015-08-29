package com.app.cryptotunnel.nutriplan.nutridiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.database.DiaryContract;

import java.util.ArrayList;
import java.util.List;


public class NutriDairy extends AppCompatActivity {


    private final ArrayList<String> al = new ArrayList<String>();
    private final DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_android_example);

        // Get ListView object from xml
        ListView listView = (ListView) findViewById(R.id.list);



        final List<DiaryContract> diaryContracts = db.getAllContacts();

        for (DiaryContract cn : diaryContracts) {
            String log = " Time: " + cn.getTime()+"\n"+" Note: " + cn.getNote();
            // Writing Contacts to log
            Log.d("Name: ", log);
            al.add(log);
        }

        String[] nutriArray = new String[al.size()];
        nutriArray = al.toArray(nutriArray);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, nutriArray);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meal_dairy, menu);
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
        else if (id == R.id.action_create){
            startActivity(new Intent(this,NoteEditorActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

