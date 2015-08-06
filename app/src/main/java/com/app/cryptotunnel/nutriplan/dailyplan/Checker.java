package com.app.cryptotunnel.nutriplan.dailyplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.scanner.ScannerActivity;

public class Checker extends Fragment {
	Button button;
	FloatingActionButton fab;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.checker, container, false);

		button= (Button) rootView.findViewById(R.id.click);
		fab = (FloatingActionButton) rootView.findViewById(R.id.fab);



		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
			//	View content = null;
				Snackbar.make(rootView, "FAB Clicked", Snackbar.LENGTH_SHORT).show();
			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				startActivity(new Intent(getActivity(), ScannerActivity.class));
			}
		});

		return rootView;
	}
}
