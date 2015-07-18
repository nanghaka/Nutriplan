package com.app.cryptotunnel.nutriplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mainListView;
    ListView listView;
    Button mdailyplan, mnutricalc, mhealthtips, mmealdiary, mabout;


    private ArrayAdapter<String> listAdapter;

//    String[] nutriArray = {
//            "Daily plan",
//            "Nutri Calculator",
//            "Health tips",
//            "Meal diary",
//            "About"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdailyplan = (Button) findViewById(R.id.dailyplan);
        mnutricalc = (Button) findViewById(R.id.calc);
        mhealthtips = (Button) findViewById(R.id.tips);
        mmealdiary = (Button) findViewById(R.id.diary);
       // mabout = (Button) findViewById(R.id.about);

        mdailyplan.setOnClickListener(this);
        mnutricalc.setOnClickListener(this);
        mhealthtips.setOnClickListener(this);
        mmealdiary.setOnClickListener(this);
      //  mabout.setOnClickListener(this);


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
                startActivity(new Intent(MainActivity.this,MealDairy.class));
                break;
//            case R.id.about:
//                Toast.makeText(getApplicationContext(),"In progress",Toast.LENGTH_SHORT).show();
//                break;


        }

    }
}
//        // Get ListView object from xml
//        listView = (ListView) findViewById(R.id.list);
//
//        // Defined Array values to show in ListView
//        String[] values = new String[] { "Android List View",
//                "Adapter implementation",
//                "Simple List View In Android",
//                "Create List View Android",
//                "Android Example",
//                "List View Source Code",
//                "List View Array Adapter",
//                "Android Example List View"
//        };
//
//        // Define a new Adapter
//        // First parameter - Context
//        // Second parameter - Layout for the row
//        // Third parameter - ID of the TextView to which the data is written
//        // Forth - the Array of data
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, nutriArray);
//
//
//        // Assign adapter to ListView
//        listView.setAdapter(adapter);
//
//        // ListView Item Click Listener
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // ListView Clicked item index
//                int itemPosition     = position;
//
//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//
//                switch (itemPosition){
//                    case 0:
//                        Toast.makeText(getApplicationContext(),
//                                "clicked daily plan" + itemValue, Toast.LENGTH_LONG)
//                                .show();
//                        break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(),
//                                "clicked nutri calculator", Toast.LENGTH_LONG)
//                                .show();
//                        break;
//                    case 2:
//                        Toast.makeText(getApplicationContext(),
//                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                                .show();
//                        break;
//                    case 3:
//                        Toast.makeText(getApplicationContext(),
//                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                                .show();
//                        break;
//                    case 4:
//                        Toast.makeText(getApplicationContext(),
//                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                                .show();
//                        break;
//
//



                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();





/*
//        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, nutriArray));
        mainListView = (ListView) findViewById( R.id.list_item );

         List<String> nutriList =new ArrayList<String>(Arrays.asList(nutriArray));
        listAdapter = new ArrayAdapter<String>(this, R.layout.list_item_nutrition, nutriList);

        mainListView.setAdapter( listAdapter );
//        ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<String>(
//                this,
//                R.layout.list_item,
//                R.id.list_item_textview,
//                nutriArray);

       // View rootView = inflater.inflate(R.layout.fragment_main, container, false);


    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        Toast.makeText(this,
//                "You have selected" + nutriArray[position],
//                Toast.LENGTH_SHORT).show();
//    }*/

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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
//}
