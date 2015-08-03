package com.app.cryptotunnel.nutriplan.nutricalculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.app.cryptotunnel.nutriplan.R;


public class Calories extends Fragment {
// implements RadioGroup.OnCheckedChangeListener {

    //    NumberPicker np;
//    TextView tv1, tv2;
//    EditText weight, height, age;
    Button calculate;
    RadioGroup gender;
    double w, h, a, calories;
//    TextView show;
      EditText weight,height,age;
    TextInputLayout textInputLayoutHeight,textInputLayoutWeight,textInputLayoutAge;


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calories, container, false);

        weight = (EditText) rootView.findViewById(R.id.weight);
        height = (EditText) rootView.findViewById(R.id.height);
        age = (EditText) rootView.findViewById(R.id.age);
        textInputLayoutHeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_height);
        textInputLayoutWeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_weight);
        textInputLayoutAge = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_age);



        textInputLayoutHeight.setErrorEnabled(true);
        Resources res = getResources();
        textInputLayoutHeight.setError(res.getString(R.string.weight_required));

        calculate = (Button) rootView.findViewById(R.id.calculate);
//        show = (TextView) rootView.findViewById(R.id.show);
        gender = (RadioGroup) rootView.findViewById(R.id.gender);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText(String.valueOf(calories));
            }
        });

        gender.setOnCheckedChangeListener(this);


        return rootView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.male:
                w = Double.parseDouble(String.valueOf(weight.getText()));
                h = Double.parseDouble(String.valueOf(height.getText()));
                a = Double.parseDouble(String.valueOf(age.getText()));
                calories = ((10 * w) + (6.25 * h) - (5 * a) + 5);
                break;
            case R.id.female:
                w = Double.parseDouble(String.valueOf(weight.getText()));
                h = Double.parseDouble(String.valueOf(height.getText()));
                a = Double.parseDouble(String.valueOf(age.getText()));
                calories = ((10 * w) + (6.25 * h) - (5 * a) - 161);
                break;

        }
    }
}


