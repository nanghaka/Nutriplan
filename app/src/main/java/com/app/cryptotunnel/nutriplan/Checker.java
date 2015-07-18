package com.app.cryptotunnel.nutriplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.cryptotunnel.nutriplan.scanner.ScannerActivity;

public class Checker extends Fragment {
	Button button;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.checker, container, false);

		//startActivity(new Intent(getActivity(),LineChartActivity.class));


		// textView = (TextView) rootView.findViewById(R.id.words);
		button= (Button) rootView.findViewById(R.id.click);
//
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//textView.setText("you have clicked");
				//startActivity(new Intent(getActivity(), SimpleScannerActivity.class));
				startActivity(new Intent(getActivity(), ScannerActivity.class));
			}
		});



		return rootView;
	}
}
