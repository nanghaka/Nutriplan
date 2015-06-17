package com.app.cryptotunnel.nutriplan;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    String[] nutriArray={
            "Daily plan",
            "Nutri Calculator",
            "Health tips",
            "Meal dairy",
            "About"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);



        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nutriArray));

//         List<String> weekForecast =new ArrayList<String>(Arrays.asList(nutriArray));
//        ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<String>(
//                this,
//                R.layout.list_item,
//                R.id.list_item_textview,
//                nutriArray);

       // View rootView = inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this,
                "You have selected" + nutriArray[position],
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
