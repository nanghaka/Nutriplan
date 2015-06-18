package com.app.cryptotunnel.nutriplan;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.app.cryptotunnel.nutriplan.data.NoteItem;
import com.app.cryptotunnel.nutriplan.data.NotesDataSource;

import java.util.List;


public class MealDairy extends ListActivity {
    private NotesDataSource datasource;
    List<NoteItem> notesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_dairy);

        datasource = new NotesDataSource(this);

        refreshDisplay();

    }
    private void refreshDisplay() {
        notesList = datasource.findAll();
        ArrayAdapter<NoteItem> adapter =
                new ArrayAdapter<NoteItem>(this, R.layout.activity_list_layout, notesList);
        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

        return super.onOptionsItemSelected(item);
    }
}
