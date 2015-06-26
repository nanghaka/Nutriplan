package com.app.cryptotunnel.nutriplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeeklyPlan extends Fragment {
//
//    TextView textView;
//    Button button;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.first_layout, container, false);


       // textView = (TextView) rootView.findViewById(R.id.words);
//        button= (Button) rootView.findViewById(R.id.click);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("you have clicked");
//            }
//        });


		return rootView;
	}


}
