package com.app.cryptotunnel.nutriplan.dailyplan;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.app.cryptotunnel.nutriplan.R;
import com.app.cryptotunnel.nutriplan.database.DatabaseHandler;
import com.app.cryptotunnel.nutriplan.database.WeightTrackerContract;

import java.util.ArrayList;
import java.util.List;

public class WeightTracker extends Fragment implements  View.OnClickListener {
	View rootView;
	ListView recyclerView;
    ArrayList<String> al = new ArrayList<String>();
    String[] nutriArray;
	private static final String TAG = "Floating Action Button";
	private static final String TRANSLATION_Y = "translationY";

	private ImageButton fab;

	private boolean expanded = false;

	private View fabAction1;
	private View fabAction2;
	private View fabAction3;

	private float offset1;
	private float offset2;
	private float offset3;



    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.weight_tracker, container, false);


		fabAction1 = rootView.findViewById(R.id.fab_action_1);
		fabAction2 = rootView.findViewById(R.id.fab_action_2);
		fabAction3 = rootView.findViewById(R.id.fab_action_3);

		recyclerView = (ListView) rootView.findViewById(R.id.lvToDoList);

		fabAction1.setOnClickListener(this);
		fabAction2.setOnClickListener(this);
		fabAction3.setOnClickListener(this);

		final ViewGroup fabContainer = (ViewGroup) rootView.findViewById(R.id.fab_container);
		fab = (ImageButton) rootView.findViewById(R.id.fab);

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				expanded = !expanded;
				if (expanded) {
					expandFab();
				} else {
					collapseFab();
				}
			}
		});
		fabContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
				offset1 = fab.getY() - fabAction1.getY();
				fabAction1.setTranslationY(offset1);
				offset2 = fab.getY() - fabAction2.getY();
				fabAction2.setTranslationY(offset2);
				offset3 = fab.getY() - fabAction3.getY();
				fabAction3.setTranslationY(offset3);
				return true;
			}
		});

        DatabaseHandler db = new DatabaseHandler(getActivity());


        final List<WeightTrackerContract> wtc = db.getAllWeights();

        for (WeightTrackerContract cn : wtc) {
//            String log = "Weights: :-)" + cn.get_weight();
//            // Writing Contacts to log
//            Log.d("Weights: ", log);
//            al.add(cn.get_weight());
            String log = " Time: " + cn.get_weight_time()+"\n"+"Weights: " + cn.get_weight();
            // Writing Contacts to log
            Log.d(TAG, log);
            al.add(log);
        }

        nutriArray = new String[al.size()];
        nutriArray = al.toArray(nutriArray);
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, nutriArray);

        // Assign adapter to ListView
        recyclerView.setAdapter(adapter);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
//			case R.id.save:
//				DatabaseHandler db = new DatabaseHandler(getActivity());
//				Log.d("SQL Insert: ", "Inserting ..");
//				String storedWeight = currentWeight.getText().toString();
//
//				if (storedWeight.equals("")){
//					try {
//						throw new InvalidValueException(storedWeight);
//					} catch (InvalidValueException e) {
//						e.printStringError(storedWeight);
//						Log.e("SQL BUG", e.toString());
//						//Snackbar.make(rootView, "Please enter your current weight", Snackbar.LENGTH_SHORT).show();
//						snackBar("Please enter your current weight");
//					}
//				}else {
//					db.addWeight(new WeightTrackerContract(storedWeight, NoteEditorActivity.getTime()));
//				}
//				break;

			case R.id.fab_action_1:
				Log.d(TAG, "Action 1");
				//snackBar("Action 1");
				startActivity(new Intent(getActivity(), History.class));
				break;
			case R.id.fab_action_2:
				Log.d(TAG, "Action 2");
				//snackBar("Action 2");
				startActivity(new Intent(getActivity(), LineChartActivity.class));

				break;
			case R.id.fab_action_3:
				Log.d(TAG, "Action 3");
				snackBar("Action 3");
				break;
		}
	}


	public void snackBar(String message){
		Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT);
		View snackbarView = snackbar.getView();
		snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
		snackbar.show();
	}

	private void collapseFab() {
		fab.setImageResource(R.drawable.animated_minus);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(createCollapseAnimator(fabAction1, offset1),
				createCollapseAnimator(fabAction2, offset2),
				createCollapseAnimator(fabAction3, offset3));
		animatorSet.start();
		animateFab();
	}

	private void expandFab() {
		fab.setImageResource(R.drawable.animated_plus);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(createExpandAnimator(fabAction1, offset1),
				createExpandAnimator(fabAction2, offset2),
				createExpandAnimator(fabAction3, offset3));
		animatorSet.start();
		animateFab();
	}

	private Animator createCollapseAnimator(View view, float offset) {
		return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
				.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
	}

	private Animator createExpandAnimator(View view, float offset) {
		return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
				.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
	}

	private void animateFab() {
		Drawable drawable = fab.getDrawable();
		if (drawable instanceof Animatable) {
			((Animatable) drawable).start();
		}
	}
}
