package com.app.cryptotunnel.nutriplan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NoteEditorActivity extends AppCompatActivity {
    EditText noteText;
    Button save,cancel;
    String[] nutriArray;

    DatabaseHandler db = new DatabaseHandler(this);
    Contact contact = new Contact();
    WeightTrackerContract wtc = new WeightTrackerContract();
    ArrayList<String> al = new ArrayList<String>();
    //DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        noteText = (EditText) findViewById(R.id.noteText);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
//
//        final List<WeightTrackerContract> wtc = db.getAllWeights();
//
//        for (WeightTrackerContract cn : wtc) {
//            String log = "Weights: :-)" + cn.get_weight();
//            // Writing Contacts to log
//            Log.d("Weights: ", log);
//            al.add(log);
//        }

        nutriArray = new String[al.size()];
        nutriArray = al.toArray(nutriArray);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Insert: ", "Inserting ..");
                db.addContact(new Contact(noteText.getText().toString(), getTime()));
//                String k= nutriArray[2];
//                Log.d("SQL", "results"+k);
                Toast.makeText(getApplicationContext(),"Note has been saved", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  db.deleteContact(new Contact(20));
//                Log.d("SQL Insert: ", "Inserting ..");
//                db.addWeight(new WeightTrackerContract("36",getTime()));
//                Log.d("SQL Insert: ", "Inserting 36"+getTime());
//                db.addWeight(new WeightTrackerContract("56",getTime()));
//                Log.d("SQL Insert: ", "Inserting 56"+getTime());
//
//                Toast.makeText(getBaseContext(), "Inserting...", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });


    }
    @SuppressLint("SimpleDateFormat")
    public static String getTime() {

//        Locale locale = new Locale("en_US");
//        Locale.setDefault(locale);

        String pattern = "yyyy-MM-dd HH:mm:ss ";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());
        return key;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_editor, menu);
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
