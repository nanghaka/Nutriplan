package com.app.cryptotunnel.nutriplan.dailyplan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

		//getting users input from settings screen
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		String input = prefs.getString(getString(R.string.pref_age_key), getString(R.string.pref_default_age_key));
		String listpref = prefs.getString("gender", "1");
		String syncpref = prefs.getString("sync_frequency", "180");
		String physical_activity = prefs.getString("pref_physical_activity", "1");

		Toast.makeText(getActivity(), "list value " + "#" + listpref + "#" + syncpref+ "#" + physical_activity+ "#" + input, Toast.LENGTH_SHORT).show();





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
