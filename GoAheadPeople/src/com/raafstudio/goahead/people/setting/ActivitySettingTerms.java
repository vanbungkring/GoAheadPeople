package com.raafstudio.goahead.people.setting;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.activity.ActivityBase;
import com.raafstudio.goahead.people.helper.so;
 


import android.os.Bundle;
import android.os.Message;

public class ActivitySettingTerms extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_terms);
		bindToolbar();
		getSupportActionBar().setTitle("Terms & Conditions");
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {

		} else
			rDialog.SetToast(this, so.meta.getErrorDetail());
	}
}
