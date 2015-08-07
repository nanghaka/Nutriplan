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

public class BMI extends Fragment {

    Button calculate, reset;
    double w,h,value;
	EditText weight,height;
	TextInputLayout textInputLayoutHeight,textInputLayoutWeight;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.bmi, container, false);

		weight = (EditText) rootView.findViewById(R.id.weight);
		height = (EditText) rootView.findViewById(R.id.height);

		textInputLayoutHeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_height);
		textInputLayoutWeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_weight);

		calculate = (Button) rootView.findViewById(R.id.calculate);
		reset = (Button) rootView.findViewById(R.id.reset);

		textInputLayoutHeight.setErrorEnabled(true);
		textInputLayoutWeight.setErrorEnabled(true);


		calculate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					w = Double.parseDouble(String.valueOf(weight.getText()));
					h = Double.parseDouble(String.valueOf(height.getText()));

					if (w > 0 && h > 0) {
						value = (w / (h * h));
						Toast.makeText(getActivity(), "Clicked button " + value, Toast.LENGTH_SHORT).show();
					} else {
						throw new InvalidValueException(value);
					}

				} catch (NumberFormatException e) {
					e.printStackTrace();
					Log.e("ONCHECKCLICKED_BUG", e.toString());
					editTextError();
				} catch (InvalidValueException e) {
					e.printStackTrace();
					Log.e("ONCHECKCLICKED_BUG", e.toString());
					editTextError();
				}
			}
		});

		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				weight.setText("");
				height.setText("");
			}
		});

		return rootView;
	}


	public void editTextError(){
		Resources res = getResources();
		textInputLayoutHeight.setError(res.getString(R.string.height_required));
		textInputLayoutWeight.setError(res.getString(R.string.weight_required));
	}
}
