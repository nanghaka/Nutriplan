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
    int qholder,r1,r2;
    int counter,icount;

   int array[]={
        R.string.share0,
           R.string.share1,
           R.string.share2,
           R.string.share3,
           R.string.share4,
           R.string.share5,
           R.string.share6,
           R.string.share7,
           R.string.share8,
           R.string.share9,
           R.string.share10,
           R.string.share11,
           R.string.share12,
           R.string.share13,
           R.string.share14,
           R.string.share15,
           R.string.share16,
           R.string.share17,
           R.string.share18,
           R.string.share19,
           R.string.share20,
           R.string.share21,
           R.string.share23,
           R.string.share22,
           R.string.share24,
           R.string.share25,
           R.string.share26,
           R.string.share27,
           R.string.share28,
           R.string.share29,
           R.string.share30

   };
    int image[]={
            R.drawable.food1,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food5,
            R.drawable.food6,
            R.drawable.food7,
            R.drawable.food8,
            R.drawable.food9,
            R.drawable.food10,
            R.drawable.food11,
            R.drawable.food13,
            R.drawable.food14,
            R.drawable.food15,
            R.drawable.food18,
            R.drawable.food20,
            R.drawable.food21,
            R.drawable.food22,
            R.drawable.food23,
            R.drawable.food24,
            R.drawable.food25
    };
    final int m=image.length;
    final int n=array.length;

    String test[]={
            "this",
            "works",
            "very",
            "well"

    };


    //ArrayList<array> hand = new ArrayList<Card>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        next=(ImageButton)findViewById(R.id.next);
        previous=(ImageButton) findViewById(R.id.previous);
        words= (TextView) findViewById(R.id.words);
        food= (ImageView) findViewById(R.id.food);


       updatetext();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                icount++;


                try {
                    if( counter<n && counter>=0) {
                        updatetext();
                    }
                    else {
                        counter = 0;
                        updatetext();
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
                    if(counter<=n && counter>=0){
                        updatetext();
                    }
                    else counter=n;

                    if(icount<=m && icount>=0) {
                        food.setImageResource(image[icount]);
                    }
                    else icount=m;

                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    Log.d("array",String.valueOf(e.toString()));
                }

            }
        });
    }

    private Intent createShareTips() {
        String send =String.valueOf(array[counter]);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
               words.getText() + APP_SHARE_HASHTAG);
        return shareIntent;

    }

    public void updatetext() {
        words.setText(array[counter]);
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
