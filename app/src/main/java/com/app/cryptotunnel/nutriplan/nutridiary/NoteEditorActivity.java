package com.app.cryptotunnel.nutriplan.nutridiary;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NoteEditorActivity extends AppCompatActivity {
    EditText noteText;
    Button save,cancel;
    String[] nutriArray;

    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<String> al = new ArrayList<String>();

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
                //db.addContact(new Contact(noteText.getText().toString(), getTime()));
                db.addBbnData(new BbnContract(5,"4859", "78859","drink plenty of water"));
                db.addBbnData(new BbnContract(5,"8927", "12378","eat fruits daily"));
                Toast.makeText(getApplicationContext(),"Note has been saved", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    @SuppressLint("SimpleDateFormat")
    public static String getTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss ";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());
        return key;
    }
}
