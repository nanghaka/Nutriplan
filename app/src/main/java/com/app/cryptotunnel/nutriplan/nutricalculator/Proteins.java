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
import android.widget.TextView;
import android.widget.Toast;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.customexception.InvalidValueException;

public class Proteins extends Fragment implements RadioGroup.OnCheckedChangeListener {

    RadioGroup energyLevel;
    EditText weightEdit;
    double proteinResult;
    Button calculate;
    TextInputLayout textInputLayoutWeight;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.proteins, container, false);

        energyLevel = (RadioGroup) rootView.findViewById(R.id.energylevel);

        weightEdit = (EditText) rootView.findViewById(R.id.weight);

        textInputLayoutWeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_weight);

        calculate = (Button) rootView.findViewById(R.id.calculate);

        textInputLayoutWeight.setErrorEnabled(true);

        energyLevel.setOnCheckedChangeListener(this);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Clicked button" + proteinResult, Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }


        @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){

            case R.id.simple:
                try {

                    proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.4);

                    if (proteinResult > 0){
                        Toast.makeText(getActivity(), "Clicked button "+ proteinResult, Toast.LENGTH_SHORT).show();
                    }else {
                        throw new InvalidValueException(proteinResult);
                    }

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

            case R.id.moderate:

                try {

                    proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.6);

                    if (proteinResult > 0){
                        Toast.makeText(getActivity(), "Clicked button "+ proteinResult, Toast.LENGTH_SHORT).show();
                    }else {
                        throw new InvalidValueException(proteinResult);
                    }

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

            case R.id.active:

                try {

                    proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.9);

                    if (proteinResult > 0){
                        Toast.makeText(getActivity(), "Clicked button "+ proteinResult, Toast.LENGTH_SHORT).show();
                    }else {
                       throw new InvalidValueException(proteinResult);
                    }

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
    }


    public void editTextError() {
        Resources res = getResources();
        textInputLayoutWeight.setError(res.getString(R.string.weight_required));
    }
}

