package com.app.cryptotunnel.nutriplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.view.LineChartView;

public class WeightLoss extends Fragment {
	LineChartView lineChartView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.weightloss, container, false);
		lineChartView = (LineChartView) rootView.findViewById(R.id.chart);
		lineChartView.setInteractive(true);
		lineChartView.setZoomEnabled(true);
		lineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
		//lineChartView.setContainerScrollEnabled(true,container);



		return rootView;
	}
}
