package com.app.cryptotunnel.nutriplan.nutricalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.cryptotunnel.nutriplan.nutricalculator.BMI;
import com.app.cryptotunnel.nutriplan.nutricalculator.Calories;
import com.app.cryptotunnel.nutriplan.nutricalculator.Proteins;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new Calories();
		case 1:
			return new Proteins();
        case 2:
            return new BMI();



		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

}