package com.app.cryptotunnel.nutriplan.nutricalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.cryptotunnel.nutriplan.R;

public class BMI extends Fragment {

    EditText heightEdit,weightEdit;
    Button calculate;
    TextView mshow;
    double w,h,value;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.bmi, container, false);

//        heightEdit= (EditText) rootView.findViewById(R.id.heightedit);
//        weightEdit= (EditText) rootView.findViewById(R.id.weightedit);
//        calculate = (Button) rootView.findViewById(R.id.calculate);
//        mshow = (TextView) rootView.findViewById(R.id.show);
//
//        calculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                w=Double.parseDouble(String.valueOf(weightEdit.getText()));
//                h=Double.parseDouble(String.valueOf(heightEdit.getText()));
//                value=(w/(h*h));
//                //value=math.log()
//
//               mshow.setText(String.valueOf(value));
//
//            }
//        });

		return rootView;
	}
}
