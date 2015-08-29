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

public class Proteins extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private EditText weightEdit;
    private TextInputLayout textInputLayoutWeight;
    private int numberSentByRadioButton = 0;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.proteins, container, false);

        RadioGroup energyLevel = (RadioGroup) rootView.findViewById(R.id.energylevel);

        weightEdit = (EditText) rootView.findViewById(R.id.weight);

        textInputLayoutWeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_weight);

        Button calculate = (Button) rootView.findViewById(R.id.calculate);
        Button reset = (Button) rootView.findViewById(R.id.reset);

        textInputLayoutWeight.setErrorEnabled(true);

        energyLevel.setOnCheckedChangeListener(this);

        calculate.setOnClickListener(this);
        reset.setOnClickListener(this);

        return rootView;
    }


        @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){

            case R.id.simple:
                Log.d("CHECKBOXCLICK", "you have checked box one"+1);
                resultFromRadioButtons(1);
                break;

            case R.id.moderate:
                Log.d("CHECKBOXCLICK", "you have checked box "+2);
                resultFromRadioButtons(2);
                break;

            case R.id.active:
                Log.d("CHECKBOXCLICK", "you have checked box one"+3);
                resultFromRadioButtons(3);
                break;
        }
    }


    private void editTextError() {
        Resources res = getResources();
        textInputLayoutWeight.setError(res.getString(R.string.weight_required));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calculate:
            ///////////////////////////////////////////////////////
                Log.d("CALCULATE BUTTON", "you have clicked the calculate button");
                switch (numberSentByRadioButton) {
                    case 1:
                        try {
                            Log.d("CHECKBOX", "you have checked box one");
                            changeStringToDouble();
                            Toast.makeText(getActivity(), "Clicked button " , Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
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
                            Log.d("CHECKBOX", "you have checked box one");
                            changeStringToDouble();
                            Toast.makeText(getActivity(), "Clicked button ", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            Log.e("ONCHECKCLICKED_BUG", e.toString());
                            editTextError();
                        } catch (InvalidValueException e) {
                            e.printStackTrace();
                            Log.e("ONCHECKCLICKED_BUG", e.toString());
                            editTextError();
                        }
                        break;

                    case 3:
                        try {
                            Log.d("CHECKBOX", "you have checked box one");
                            changeStringToDouble();
                            Toast.makeText(getActivity(), "Clicked button ", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
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

            /////// ////////////////////////////////////////////////////
            case R.id.reset:
                weightEdit.setText("");
                break;

        }
    }


    private void changeStringToDouble() throws InvalidValueException {
        double proteinResult = Double.parseDouble(String.valueOf(weightEdit.getText()));

        if (proteinResult <= 0){
           // Toast.makeText(getActivity(), "Clicked button "+ proteinResult, Toast.LENGTH_SHORT).show();
            throw new InvalidValueException(proteinResult);
        }
    }


    private void resultFromRadioButtons(int r){
        numberSentByRadioButton = r;
    }
}

