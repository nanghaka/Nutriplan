package com.app.cryptotunnel.nutriplan.nutricalculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.customexception.InvalidValueException;


public class Calories extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private Button calculate;
    private Button reset;
    private RadioGroup gender;
    private double w;
    private double h;
    private double a;
    private double calories;
    double doubleTest;
    private EditText weight;
    private EditText height;
    private EditText age;
    private int numberSentByRadioButton = 0;
    private TextInputLayout textInputLayoutHeight;
    private TextInputLayout textInputLayoutWeight;
    private TextInputLayout textInputLayoutAge;


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calories, container, false);

        gender = (RadioGroup) rootView.findViewById(R.id.gender);

        weight = (EditText) rootView.findViewById(R.id.weight);
        height = (EditText) rootView.findViewById(R.id.height);
        age = (EditText) rootView.findViewById(R.id.age);

        textInputLayoutHeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_height);
        textInputLayoutWeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_weight);
        textInputLayoutAge = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_age);

        calculate = (Button) rootView.findViewById(R.id.calculate);
        reset = (Button) rootView.findViewById(R.id.reset);

        textInputLayoutHeight.setErrorEnabled(true);
        textInputLayoutWeight.setErrorEnabled(true);
        textInputLayoutAge.setErrorEnabled(true);

        gender.setOnCheckedChangeListener(this);

        calculate.setOnClickListener(this);
        reset.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

            case R.id.male:
                resultFromRadioButtons(1);
                break;

            case R.id.female:
                resultFromRadioButtons(2);
                break;

            default:

        }
    }


    private void editTextError(){
        Resources res = getResources();
        textInputLayoutHeight.setError(res.getString(R.string.height_required));
        textInputLayoutWeight.setError(res.getString(R.string.weight_required));
        textInputLayoutAge.setError(res.getString(R.string.age_required));
    }

    private void changeStringToDouble() throws InvalidValueException {
        w = Double.parseDouble(String.valueOf(weight.getText()));
        h = Double.parseDouble(String.valueOf(height.getText()));
        a = Double.parseDouble(String.valueOf(age.getText()));

        if (w <= 0 && h <= 0 && a <= 0){
            weight.setText("");
            height.setText("");
            age.setText("");
            throw new InvalidValueException(w);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.calculate:

                switch (numberSentByRadioButton) {
                    case 1:
                        try {
                            changeStringToDouble();
                            calories = ((10 * w) + (6.25 * h) - (5 * a) + 5);
                            Toast.makeText(getActivity(), "Clicked button "+ calories, Toast.LENGTH_SHORT).show();

                        }catch (NumberFormatException e){
                            e.printStackTrace();
                            Log.e("ONCHECKCLICKED_BUG", e.toString());
                            editTextError();
                        } catch (InvalidValueException e) {
                            e.printStackTrace();
                            Log.e("ONCHECKCLICKED_BUG", e.toString());
                            editTextError();
                        }
                        break;

                    case 2:
                        try {
                            changeStringToDouble();
                            calories = ((10 * w) + (6.25 * h) - (5 * a) - 161);
                            Toast.makeText(getActivity(), "Clicked button "+ calories, Toast.LENGTH_SHORT).show();

                        }catch (NumberFormatException e){
                            e.printStackTrace();
                            Log.e("ONCHECKCLICKED_BUG", e.toString());
                            editTextError();
                        } catch (InvalidValueException e) {
                            e.printStackTrace();
                            Log.e("ONCHECKCLICKED_BUG", e.toString());
                            editTextError();
                        }
                        break;
                }
                break;


            case R.id.reset:
                weight.setText("");
                height.setText("");
                age.setText("");
                break;
        }

    }

    private void resultFromRadioButtons(int r){
       numberSentByRadioButton = r;
    }
}


