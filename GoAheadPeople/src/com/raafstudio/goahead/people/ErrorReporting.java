package com.raafstudio.goahead.people;
 

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ErrorReporting extends Activity {
	String eror;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eror_reporting);
		eror = getIntent().getStringExtra("error");
		((TextView) findViewById(R.id.TvError)).setText(eror);
		((Button) findViewById(R.id.BtSendError))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.setType("text/plain");
						shareIntent.putExtra(
								android.content.Intent.EXTRA_SUBJECT,
								"GAP Error");
						shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
								eror);
						startActivity(Intent.createChooser(shareIntent,
								"Error Reporting"));

					}
				});
	}
}
