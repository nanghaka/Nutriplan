package com.app.cryptotunnel.nutriplan.dailyplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.nutridiary.NoteEditorActivity;
import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.database.WeightTrackerContract;

public class WeightTracker extends Fragment implements  View.OnClickListener {
	Button graph,history,save;
	EditText currentWeight;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.weight_tracker, container, false);

		save = (Button) rootView.findViewById(R.id.save);
		currentWeight = (EditText) rootView.findViewById(R.id.currentWeightEdit);
		history = (Button) rootView.findViewById(R.id.history);
		graph= (Button) rootView.findViewById(R.id.graph);
		graph.setOnClickListener(this);
		history.setOnClickListener(this);
		save.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.history:
				startActivity(new Intent(getActivity(), History.class));
				break;

			case R.id.graph:
				startActivity(new Intent(getActivity(), LineChartActivity.class));
				break;

			case R.id.save:
				DatabaseHandler db = new DatabaseHandler(getActivity());
				Log.d("SQL Insert: ", "Inserting ..");
				db.addWeight(new WeightTrackerContract(currentWeight.getText().toString(), NoteEditorActivity.getTime()));


				break;

		}


	}
}
