package com.app.cryptotunnel.nutriplan.nutridiary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.database.BbnContract;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.database.DiaryContract;
import com.app.cryptotunnel.nutriplan.database.MealPlanContract;
import com.app.cryptotunnel.nutriplan.service.ApiIntentService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NoteEditorActivity extends AppCompatActivity {
    private EditText noteText;
    private Button save;
    private Button cancel;
    private String[] nutriArray;

    private final DatabaseHandler db = new DatabaseHandler(this);
    private final ArrayList<String> al = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        noteText = (EditText) findViewById(R.id.noteText);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

        nutriArray = new String[al.size()];
        nutriArray = al.toArray(nutriArray);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Insert: ", "Inserting ..");
                db.addContact(new DiaryContract(noteText.getText().toString(), getTime()));
                db.addBbnData(new BbnContract(5,"4859", "78859","drink plenty of water"));
                db.addBbnData(new BbnContract(5,"8927", "12378","eat fruits daily"));
                db.addMealPlan(new MealPlanContract("monday", "matooke", "rice", "posho"));
                db.addMealPlan(new MealPlanContract("tuesday", "rice", "matooke", "posho"));
              //  db.addMealPlan(new BbnContract(5, "8927", "12378", "eat fruits daily"));
                Toast.makeText(getApplicationContext(),"Note has been saved", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                startService(new Intent(NoteEditorActivity.this, ApiIntentService.class));
            }
        });


    }
    @SuppressLint("SimpleDateFormat")
    private static String getTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss ";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());
        return key;
    }
}
