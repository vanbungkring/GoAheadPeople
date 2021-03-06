package com.raafstudio.goahead.people.activity;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
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
	TextView TvForgot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		onlogin = true;
		setContentView(R.layout.activity_login);
		TvForgot = (TextView) findViewById(R.id.TvForgotPassword);
		TvEmail = (EditText) findViewById(R.id.EtEmail);
		TvPassword = (EditText) findViewById(R.id.EtPassword);
		TvForgot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ActivityLogin.this, ActivityLoginForgot.class));
			}
		});
		((Button) findViewById(R.id.BtSignIn))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// /"maulfi.02@gmail.com", "kumahaaing", "",
						if (TvEmail.getText().length() == 0
								|| TvPassword.getText().length() == 0) {
							rDialog.SetToast(ActivityLogin.this,
									"Email / Password cannot empty");
							return;
						}
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
			// startActivity(new Intent(ActivityLogin.this,
			// ActivityMain.class));
			finish();
		} else
			rDialog.SetToast(ActivityLogin.this, so.meta.getErrorDetail());
		rDialog.CloseProgressDialog();
	}

}
