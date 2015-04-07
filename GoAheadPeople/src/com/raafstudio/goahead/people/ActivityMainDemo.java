package com.raafstudio.goahead.people;

import com.raafstudio.goahead.people.helper.so;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ActivityMainDemo extends Activity {
	int fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_demo);
		LinearLayout layout = (LinearLayout) findViewById(R.id.DemoMain);
		fragment = getIntent().getIntExtra("fragment", 1);
		switch (fragment) {
		case 2:
			layout.setBackgroundResource(R.drawable.market_mark);
			break;

		default:
			layout.setBackgroundResource(R.drawable.discover_mark);
			break;
		}
		
		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				so.setParamBoolean("fragment_" + fragment, true);
				finish();
			}
		});
	}
}
