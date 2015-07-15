package com.app.cryptotunnel.nutriplan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapterDailyPlan extends FragmentPagerAdapter {

	public TabsPagerAdapterDailyPlan(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new WeeklyPlan();
		case 1:
			return new WeightLoss();
		case 2:
			return new CookingTimer();

		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

}