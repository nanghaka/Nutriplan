package com.app.cryptotunnel.nutriplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Proteins extends Fragment implements RadioGroup.OnCheckedChangeListener {

    RadioGroup energyLevel;
    EditText weightEdit;
    Button calculate;
    Double weight,proteinResult;
    TextView show;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.second_layout, container, false);

        calculate = (Button) rootView.findViewById(R.id.calculate);
        weightEdit = (EditText) rootView.findViewById(R.id.weightedit);
        energyLevel = (RadioGroup) rootView.findViewById(R.id.energyLevel);
        show = (TextView) rootView.findViewById(R.id.show);

        energyLevel.setOnCheckedChangeListener(this);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText(proteinResult.toString());
            }
        });

		return rootView;
	}

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.simple:
                proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.4);
                //show.setText(proteinResult.toString());
                break;
            case R.id.moderate:
                //proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.4);
                proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.6);
                //show.setText(proteinResult.toString());
                break;
            case R.id.active:
               // proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.6);
                proteinResult=(Double.parseDouble(weightEdit.getText().toString())*0.9);
                break;

        }

    }

}
