package com.app.cryptotunnel.nutriplan.nutridiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.cryptotunnel.nutriplan.database.Contact;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.R;

import java.util.ArrayList;
import java.util.List;


public class NutriDairy extends AppCompatActivity {


    private ListView mainListView;
    ListView listView;
    ArrayList<String> al = new ArrayList<String>();
    DatabaseHandler db = new DatabaseHandler(this);
    Contact contact = new Contact();


    private ArrayAdapter<String> listAdapter;

//    String[] nutriArray={
//            "Daily plan",
//            "Nutri Calculator",
//            "Health tips",
//            "Meal dairy",
//            "About"
//    };

    String[] nutriArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_android_example);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[]{"Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        final List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
           // String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            String log = " Time: " + cn.getPhoneNumber()+"\n"+" Note: " + cn.getName();
            // Writing Contacts to log
            Log.d("Name: ", log);
            al.add(log);
        }

        nutriArray = new String[al.size()];
        nutriArray = al.toArray(nutriArray);

//        nutriArray =new String[]{
//          al.get(0),al.get(1),al.get(2),al.get(3)
//        };


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, nutriArray);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);



                switch (itemPosition) {
                    case 0:
                       // if (contact.getID() == position) db.deleteContact(new Contact(itemPosition));
                        db.deleteContact(new Contact(0));
                        Toast.makeText(getApplicationContext(),
                                "clicked daily plan" + itemValue, Toast.LENGTH_LONG)
                                .show();
                        break;
                    case 1:
                      //  if (contact.getID() == position)
                            db.deleteContact(new Contact(1));
                       // db.deleteContact(new Contact(itemPosition));
                        Toast.makeText(getApplicationContext(),
                                "clicked nutri calculator", Toast.LENGTH_LONG)
                                .show();
                        break;
                    case 2:
                        //if (contact.getID() == position) db.deleteContact(new Contact(itemPosition));
                        //db.deleteContact(new Contact(contact.getID()));

                         db.deleteContact(new Contact(2));
                        Toast.makeText(getApplicationContext(),
                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                                .show();
                        break;
                    case 3:
                        //db.deleteContact(new Contact(itemPosition));
                        db.deleteContact(new Contact(3));
                        Toast.makeText(getApplicationContext(),
                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                                .show();
                        break;
                    case 4:
                        db.deleteContact(new Contact(4));
                        Toast.makeText(getApplicationContext(),
                                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                                .show();
                        break;


                }

                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();

            }

        });
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

