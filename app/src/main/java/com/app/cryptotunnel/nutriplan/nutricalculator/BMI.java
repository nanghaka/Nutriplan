package com.app.cryptotunnel.nutriplan.nutricalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.customexception.InvalidValueException;

public class BMI extends Fragment {

    private double w;
    private double h;
    private double value;
	private EditText weight;
    private EditText height;
	private TextInputLayout textInputLayoutHeight;
    private TextInputLayout textInputLayoutWeight;
    View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.bmi, container, false);

		weight = (EditText) rootView.findViewById(R.id.weight);
		height = (EditText) rootView.findViewById(R.id.height);

		textInputLayoutHeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_height);
		textInputLayoutWeight = (TextInputLayout) rootView.findViewById(R.id.text_input_layout_weight);

        Button calculate = (Button) rootView.findViewById(R.id.calculate);
        Button reset = (Button) rootView.findViewById(R.id.reset);

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
                       // Toast.makeText(getActivity(), "Clicked button " + value, Toast.LENGTH_SHORT).show();
                      //  snackBar("your BMI is "+(value*10000));
                        showDialog("your BMI is "+(value*10000));
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


	private void editTextError(){
		Resources res = getResources();
		textInputLayoutHeight.setError(res.getString(R.string.height_required));
		textInputLayoutWeight.setError(res.getString(R.string.weight_required));
	}

    private void snackBar(String message){
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.black));
        snackbar.show();
    }

    public void showDialog(String message){
        // Creating alert Dialog with one Button

        AlertDialog alertDialog = new AlertDialog.Builder(
                getActivity()).create();

        // Setting Dialog Title
        alertDialog.setTitle("Results");

        // Setting Dialog Message
        alertDialog.setMessage(message);

//                // Setting Icon to Dialog
//                alertDialog.setIcon(R.drawable.);

        // Setting OK Button
        alertDialog.setButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int which) {
//                        // Write your code here to execute after dialog
//                        // closed
//                        Toast.makeText(getApplicationContext(),
//                                "You clicked on OK", Toast.LENGTH_SHORT)
//                                .show();
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }
}
