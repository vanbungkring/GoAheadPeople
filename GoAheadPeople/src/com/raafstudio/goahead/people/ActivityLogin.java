package com.raafstudio.goahead.people;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends ActivityBase {
	EditText TvEmail, TvPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		TvEmail = (EditText) findViewById(R.id.EtEmail);
		TvPassword = (EditText) findViewById(R.id.EtPassword);
		((Button) findViewById(R.id.BtSignIn))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// /"maulfi.02@gmail.com", "kumahaaing", "",
						API.doLogin(TvEmail.getText().toString(), TvPassword
								.getText().toString(), "", handler);
						rDialog.ShowProgressDialog(ActivityLogin.this,
								"Sign In", "please wait ..", true);
					}
				});
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200 && so.meta.getModul() == so.modul_login) {
			startActivity(new Intent(ActivityLogin.this, ActivityMain.class));
			finish();
		} else
			rDialog.SetToast(ActivityLogin.this, so.meta.getErrorDetail());
		rDialog.CloseProgressDialog();
	}

}
