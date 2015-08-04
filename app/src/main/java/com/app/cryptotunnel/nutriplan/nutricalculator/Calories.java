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


public class Calories extends Fragment implements RadioGroup.OnCheckedChangeListener {

    Button calculate;
    RadioGroup gender;
    double w, h, a, calories;
    EditText weight,height,age;
    TextInputLayout textInputLayoutHeight,textInputLayoutWeight,textInputLayoutAge;

    Resources res = getResources();

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

        textInputLayoutHeight.setErrorEnabled(true);
        textInputLayoutWeight.setErrorEnabled(true);
        textInputLayoutAge.setErrorEnabled(true);

        gender.setOnCheckedChangeListener(this);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show.setText(String.valueOf(calories));
               // showMessageDialog("Contents = " + rawResult.getText() + ", Format = " + rawResult.getBarcodeFormat().toString());
                Toast.makeText(getActivity(), "Clicked button", Toast.LENGTH_SHORT).show();
            }
        });




        return rootView;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

            case R.id.male:
                try {
                    w = Double.parseDouble(String.valueOf(weight.getText()));
                    h = Double.parseDouble(String.valueOf(height.getText()));
                    a = Double.parseDouble(String.valueOf(age.getText()));
                    if (w > 0 && h > 0 && a > 0){
                        calories = ((10 * w) + (6.25 * h) - (5 * a) + 5);
                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                    Log.e("OnCheckChanged", e.toString());
                   editTextError();
                }
                break;

            case R.id.female:
                try {
                    w = Double.parseDouble(String.valueOf(weight.getText()));
                    h = Double.parseDouble(String.valueOf(height.getText()));
                    a = Double.parseDouble(String.valueOf(age.getText()));
                    if (w > 0 && h > 0 && a > 0){
                        calories = ((10 * w) + (6.25 * h) - (5 * a) - 161);
                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                    Log.e("OnCheckChanged", e.toString());
                    editTextError();
                }
                break;
        }
    }


    public void editTextError(){
        textInputLayoutHeight.setError(res.getString(R.string.height_required));
        textInputLayoutWeight.setError(res.getString(R.string.weight_required));
        textInputLayoutAge.setError(res.getString(R.string.age_required));
    }
//    public void showMessageDialog(String message) {
//        DialogFragment fragment = MessageDialogFragment.newInstance("Scan Results", message, this);
//        fragment.show(getSupportFragmentManager(), "scan_results");
//    }
//    /**
//     * Return the FragmentManager for interacting with fragments associated
//     * with this activity.
//     */
//    public FragmentManager getSupportFragmentManager() {
//        return mFragments;
//    }
}


