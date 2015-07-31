package com.app.cryptotunnel.nutriplan.dailyplan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.cryptotunnel.nutriplan.dailyplan.Checker;
import com.app.cryptotunnel.nutriplan.dailyplan.Mealplan;
import com.app.cryptotunnel.nutriplan.dailyplan.WeightLoss;

public class TabsPagerAdapterDailyPlan extends FragmentPagerAdapter {

	public TabsPagerAdapterDailyPlan(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new Mealplan();
		case 1:
			return new WeightLoss();
		case 2:
			return new Checker();

		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

}
