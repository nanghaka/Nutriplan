package com.app.cryptotunnel.nutriplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit.client.OkClient;

public class Mealplan extends Fragment implements  View.OnClickListener{


    ImageButton next, previous;
    TextView dayoftheweek, breakfastFood, lunchFood, dinnerFood;
    String days[] ={
      "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    int counter = 0;
    final int n=days.length;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.mealplan, container, false);

        dayoftheweek= (TextView) rootView.findViewById(R.id.weekDay);
        breakfastFood = (TextView) rootView.findViewById(R.id.breakfastFood);
        lunchFood = (TextView) rootView.findViewById(R.id.lunchFood);
        dinnerFood = (TextView) rootView.findViewById(R.id.dinnerFood);
        next = (ImageButton) rootView.findViewById(R.id.next);
        previous = (ImageButton) rootView.findViewById(R.id.previous);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);

		return rootView;
	}


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.next:
                    counter++;

                    if (counter < n && counter >= 0) {
                        updatetext();
                    } else {
                        counter = 0;
                        updatetext();
                    }

                    break;
                case R.id.previous:
                    counter--;


                    if (counter <= n && counter >= 0) {
                        updatetext();
                    } else counter = n;


//                        Toast.makeText(getActivity(), "In progress..", Toast.LENGTH_SHORT).show();
//                        counter--;
//                        dayoftheweek.setText(days[counter]);
                    break;


            }

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            Log.d("Meal plan", "ARRAYBUG" + e.toString());
        }
    }

    private void updatetext () {
           // Toast.makeText(getActivity(), "In progress..", Toast.LENGTH_SHORT).show();
            dayoftheweek.setText(days[counter]);
    }



}