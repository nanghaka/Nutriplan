package com.app.cryptotunnel.nutriplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WeightLoss extends Fragment {
	//LineChartView lineChartView;
	Button graph;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.weightloss, container, false);
//		lineChartView = (LineChartView) rootView.findViewById(R.id.chart);
//		lineChartView.setInteractive(true);
//		lineChartView.setZoomEnabled(true);
//		lineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
//		//lineChartView.setContainerScrollEnabled(true,container);
//
//		List<PointValue> values = new ArrayList<PointValue>();
//		values.add(new PointValue(8, 5));
//		values.add(new PointValue(8, 8));
//		values.add(new PointValue(2, 1));
//		values.add(new PointValue(3, 4));
//
//		//In most cased you can call data model methods in builder-pattern-like manner.
//		Line line = new Line(values).setColor(R.color.blue).setCubic(true);
//		List<Line> lines = new ArrayList<Line>();
//		lines.add(line);
//
//		LineChartData data = new LineChartData();
//		data.setLines(lines);
//
////		LineChartView chart = new LineChartView(context);
////		chart.setLineChartData(data);
//		lineChartView.setLineChartData(data);

		graph= (Button) rootView.findViewById(R.id.graph);
		graph.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), LineChartActivity.class));
			}
		});




		return rootView;
	}
}
