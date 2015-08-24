package com.app.cryptotunnel.nutriplan.dailyplan;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.customexception.InvalidValueException;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.database.WeightTrackerContract;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightInput extends AppCompatActivity {

    private Button calculate;
    private Button reset;
    private double w;
    private double h;
    private double value;
    private EditText weight;
    private TextInputLayout textInputLayoutWeight;
    DatabaseHandler db = new DatabaseHandler(this);
    private View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_input);
        parentLayout = findViewById(R.id.root_view);

        weight = (EditText) findViewById(R.id.weight);

        textInputLayoutWeight = (TextInputLayout) findViewById(R.id.text_input_layout_weight);

        calculate = (Button) findViewById(R.id.calculate);
        reset = (Button) findViewById(R.id.reset);

        textInputLayoutWeight.setErrorEnabled(true);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("SQL Insert: ", "Inserting ..");
                String storedWeight = weight.getText().toString();

                if (storedWeight.equals("")){
                    try {
                        throw new InvalidValueException(storedWeight);
                    } catch (InvalidValueException e) {
                        e.printStringError(storedWeight);
                        Log.e("SQL BUG", e.toString());
                        editTextError();
                        //Snackbar.make(rootView, "Please enter your current weight", Snackbar.LENGTH_SHORT).show();
                        //snackBar("Please enter your current weight");
                    }
                }else {
                    db.addWeight(new WeightTrackerContract(storedWeight, getTime()));
                    snackBar("Your weight has been saved");
                }


//                try {
//                    w = Double.parseDouble(String.valueOf(weight.getText()));
//
//                    if (w > 0 && h > 0) {
//                        value = (w / (h * h));
//                        Toast.makeText(getApplicationContext(), "Clicked button " + value, Toast.LENGTH_SHORT).show();
//                    } else {
//                        throw new InvalidValueException(value);
//                    }
//
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                    Log.e("ONCHECKCLICKED_BUG", e.toString());
//                    editTextError();
//                } catch (InvalidValueException e) {
//                    e.printStackTrace();
//                    Log.e("ONCHECKCLICKED_BUG", e.toString());
//                    editTextError();
//                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight.setText("");
            }
        });
    }


    private void editTextError(){
        Resources res = getResources();
        textInputLayoutWeight.setError(res.getString(R.string.weight_required));
    }

    private void snackBar(String message){
        Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.black));
        snackbar.show();
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss ";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());
        return key;
    }

}

