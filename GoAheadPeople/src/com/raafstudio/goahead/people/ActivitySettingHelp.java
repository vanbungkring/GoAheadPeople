package com.raafstudio.goahead.people;
 
import android.os.Bundle;
import android.os.Message;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.helper.so;

public class ActivitySettingHelp extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_help);
		bindToolbar();
		getSupportActionBar().setTitle("Help / Tutorial");
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
