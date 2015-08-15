package com.app.cryptotunnel.nutriplan;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.app.cryptotunnel.nutriplan.dailyplan.DailyPlan;
import com.app.cryptotunnel.nutriplan.healthtips.HealthTips;
import com.app.cryptotunnel.nutriplan.nutricalculator.NutriCalculator;
import com.app.cryptotunnel.nutriplan.nutridiary.NutriDairy;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mdailyplan;
    private Button mnutricalc;
    private Button mhealthtips;
    private Button mmealdiary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        viewConnections();

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);


    }

    private void viewConnections() {
        mdailyplan = (Button) findViewById(R.id.dailyplan);
        mnutricalc = (Button) findViewById(R.id.calc);
        mhealthtips = (Button) findViewById(R.id.tips);
        mmealdiary = (Button) findViewById(R.id.diary);

        mdailyplan.setOnClickListener(this);
        mnutricalc.setOnClickListener(this);
        mhealthtips.setOnClickListener(this);
        mmealdiary.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dailyplan:
                startActivity(new Intent(MainActivity.this,DailyPlan.class));
                break;
            case R.id.calc:
                startActivity(new Intent(MainActivity.this,NutriCalculator.class));
                break;
            case R.id.tips:
                startActivity(new Intent(MainActivity.this,HealthTips.class));
                break;
            case R.id.diary:
                startActivity(new Intent(MainActivity.this,NutriDairy.class));
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
