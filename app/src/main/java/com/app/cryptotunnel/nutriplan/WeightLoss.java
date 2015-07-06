package com.app.cryptotunnel.nutriplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
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

		List<PointValue> values = new ArrayList<PointValue>();
		values.add(new PointValue(8, 5));
		values.add(new PointValue(8, 8));
		values.add(new PointValue(2, 1));
		values.add(new PointValue(3, 4));

		//In most cased you can call data model methods in builder-pattern-like manner.
		Line line = new Line(values).setColor(R.color.blue).setCubic(true);
		List<Line> lines = new ArrayList<Line>();
		lines.add(line);

		LineChartData data = new LineChartData();
		data.setLines(lines);

//		LineChartView chart = new LineChartView(context);
//		chart.setLineChartData(data);
		lineChartView.setLineChartData(data);


		return rootView;
	}
}
