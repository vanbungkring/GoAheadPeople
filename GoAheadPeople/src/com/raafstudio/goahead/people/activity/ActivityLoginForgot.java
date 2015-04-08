package com.raafstudio.goahead.people.activity;

import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLoginForgot extends ActivityBase{
	
	
	Button BtForgot;
	EditText EtEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_forgot);
		EtEmail = (EditText)findViewById(R.id.EtEmail);
		BtForgot = (Button)findViewById(R.id.BtForgot);
		BtForgot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
