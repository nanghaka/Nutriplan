package com.app.cryptotunnel.nutriplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Calories extends Fragment {

//    NumberPicker np;
//    TextView tv1, tv2;
    EditText weight,height,age;
    Button calculate;
    double w,h,a,caloriesm,caloriesf;
    TextView show;


	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.first_layout, container, false);


        weight= (EditText) rootView.findViewById(R.id.editText);
        height= (EditText) rootView.findViewById(R.id.editText2);
        age= (EditText) rootView.findViewById(R.id.editText3);
        calculate=(Button) rootView.findViewById(R.id.calculate);
        show=(TextView) rootView.findViewById(R.id.show);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w=Double.parseDouble(String.valueOf(weight.getText()));
                h=Double.parseDouble(String.valueOf(height.getText()));
                a=Double.parseDouble(String.valueOf(age.getText()));
                caloriesm=((10*w)+(6.25*h)-(5*a)+5);
                caloriesf=((10*w)+(6.25*h)-(5*a)-161);

               show.setText(String.valueOf(caloriesm));
            }
        });



//        np = (NumberPicker) rootView.findViewById(R.id.numberPicker1);
//        tv1 = (TextView) rootView.findViewById(R.id.textView2);
//        tv2 = (TextView) rootView.findViewById(R.id.textView3);
//
//        np.setMinValue(0);
//        np.setMaxValue(10);
//        np.setWrapSelectorWheel(false);
//
//        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                // TODO Auto-generated method stub
//
//                String Old = "Old Value : ";
//                String New = "New Value : ";
//
//                tv1.setText(Old.concat(String.valueOf(oldVal)));
//                tv2.setText(New.concat(String.valueOf(newVal)));
//            }
//        });


//        textView = (TextView) rootView.findViewById(R.id.words);
//        button= (Button) rootView.findViewById(R.id.click);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("you have clicked");
//            }
//        });


		return rootView;
	}
//    public void show()
//    {
//
//        final Dialog d = new Dialog(MainActivity.this);
//        d.setTitle("NumberPicker");
//        d.setContentView(R.layout.dialog);
//        Button b1 = (Button) d.findViewById(R.id.button1);
//        Button b2 = (Button) d.findViewById(R.id.button2);
//        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
//        np.setMaxValue(100);
//        np.setMinValue(0);
//        np.setWrapSelectorWheel(false);
//        np.setOnValueChangedListener(this);
//        b1.setOnClickListener(new OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                tv.setText(String.valueOf(np.getValue()));
//                d.dismiss();
//            }
//        });
//        b2.setOnClickListener(new OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                d.dismiss();
//            }
//        });
//        d.show();
//
//
//    }
//
//
//    @Override
//    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//
//    }
}
