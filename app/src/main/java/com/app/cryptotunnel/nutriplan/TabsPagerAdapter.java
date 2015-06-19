package com.app.cryptotunnel.nutriplan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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


		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

}
