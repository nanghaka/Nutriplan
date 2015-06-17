package com.app.cryptotunnel.nutriplan;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class HealthTips extends ActionBarActivity {
    ImageButton next,previous;
    TextView words;
    int counter;
   String array[]={
        "Avoid skipping meals",
        "Drink plenty of water",
           "Eat plenty of vegetables to increase your immunity and increase your life span cozz and ndonfadfao"

   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        next=(ImageButton)findViewById(R.id.next);
        previous=(ImageButton) findViewById(R.id.previous);
        words= (TextView) findViewById(R.id.words);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                int k=array.length;
                if( counter<k && counter>=0)
                    words.setText(array[counter]);

                else {
                    counter=0;
                    words.setText(array[counter]);
                }

//                if(counter<=2){
//                    counter++;
//                    words.setText(array[counter]);
//                }else {
//                    counter=0;
//                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter--;
                int n=array.length;
                if(counter<n && counter>=0)
                    words.setText(array[counter]);

                else
                    counter=0;


            }
//                if(count<=0){
//                    count=0;
//                }else {
//                    count--;
//                }


              //  words.setText(array[count]);
           // }
        });

//        public void updatetext() {
//            qholder = questionBank[counter].getQuestion();
//            tquestion.setText(qholder);
//
//        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_tips, menu);
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
