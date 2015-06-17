package com.app.cryptotunnel.nutriplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class HealthTips extends ActionBarActivity {


    private static final String APP_SHARE_HASHTAG = "#Nutriplan app";
    ImageButton next,previous;
    TextView words;
    ImageView food;
    int counter,icount;

   String array[]={
        "Avoid skipping meals",
        "Drink plenty of water",
           "Eat plenty of vegetables to increase your immunity ",
           "Dont drink sugar calories",
           "Avoid processed junk food",
           "Dont over cook or burn your meat"

   };
    int image[]={
            R.drawable.food1,
            R.drawable.back6,
            R.drawable.food3,
            R.drawable.food4
//            "R.drawable.food5",
//            "R.drawable.food6"

    };
    int m=image.length;
    int n=array.length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        next=(ImageButton)findViewById(R.id.next);
        previous=(ImageButton) findViewById(R.id.previous);
        words= (TextView) findViewById(R.id.words);
        food= (ImageView) findViewById(R.id.food);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                icount++;
                try {
                    if( counter<n && counter>=0) words.setText(array[counter]);
                    else {
                        counter = 0;
                        words.setText(array[counter]);
                    }

                    if( icount<m && counter>=0) food.setImageResource(image[icount]);
                    else {
                        icount = 0;
                        food.setImageResource(image[icount]);
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    Log.d("array",String.valueOf(e.toString()));
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter--;
                icount--;
                try {
                    if(counter<n && counter>=0) words.setText(array[counter]);
                    else counter=0;

                    if(icount<=m && icount>=0) food.setImageResource(image[icount]);
                    else icount=m;

                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    Log.d("array",String.valueOf(e.toString()));
                }

            }
        });
    }

    private Intent createShareTips() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                array[counter] + APP_SHARE_HASHTAG);
        return shareIntent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_tips, menu);


        MenuItem menuItem = menu.findItem(R.id.share);
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareTips());
        } else {
            Log.d("shareIntent", "Share Action Provider is null?");
        }
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
