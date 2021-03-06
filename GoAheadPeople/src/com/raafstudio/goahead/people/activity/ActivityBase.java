package com.raafstudio.goahead.people.activity;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.ExceptionHandler;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.activity.artwork.ActivityArtworkDetail;
import com.raafstudio.goahead.people.activity.product.ActivityProductDetail;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityBase extends ActionBarActivity {
	// protected MyApp mMyApp;
	protected Toolbar mToolbar;
	protected TextView TvNext;
	Boolean onlogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// mMyApp = (MyApp) this.getApplicationContext();
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		if (so.ctx == null)
			so.ctx = getApplicationContext();
	}

	protected void onResume() {
		super.onResume();
		// mMyApp.setCurrentActivity(this);
	}

	protected void onPause() {
		clearReferences();
		super.onPause();
	}

	protected void onDestroy() {
		clearReferences();
		super.onDestroy();
	}

	private void clearReferences() {
		// Activity currActivity = mMyApp.getCurrentActivity();
		// if (currActivity != null && currActivity.equals(this))
		// mMyApp.setCurrentActivity(null);
	}

	public void bindToolbar() {
		mToolbar = (Toolbar) findViewById(R.id.Toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		TvNext = (TextView) findViewById(R.id.TollbarBtNext);

	}

	protected final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			handlerResponse(msg);
		}
	};

	protected void handlerResponse(Message msg) {
		API.ApiParser(msg);
		if (so.meta.getCode() == 401) {
			if (!onlogin)
				doLogin();
		} else if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_base && isBase) {
				so.getUserOther().getArtworks().clear();
				so.getUserOther().getProducts().clear();
				loadData();
			}
		} else
			rDialog.SetToast(this,
					so.meta.getErrorType() + "\n" + so.meta.getErrorDetail());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		if (menuItem.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(menuItem);
	}

	int what_user = 1;
	protected int User_id;
	protected String String_id;

	public void doLogin() {
		startActivity(new Intent(this, ActivityBoarding.class));
		finish();
	}

	public void ShowProfile(int userid) {
		isBase = true;
		User_id = userid;
		rDialog.ShowProgressDialog(this, "loading profile", "please wait..",
				true);
		what_user = 1;
		if (userid == so.getUserOther().getUser_id())
			loadData();
		else {
			API.getProfile(userid, handler);
		}
	}

	public void ShowArt(int userid, String string_id) {
		isBase = true;
		rDialog.ShowProgressDialog(this, "loading artwork", "please wait..",
				true);
		what_user = 2;
		String_id = string_id;
		if (userid == so.getUserOther().getUser_id())
			loadData();
		else {
			API.getProfile(userid, handler);
		}
	}

	public void ShowProduct(int userid, String string_id) {
		isBase = true;
		rDialog.ShowProgressDialog(this, "loading product", "please wait..",
				true);
		what_user = 3;
		String_id = string_id;
		if (userid == so.getUserOther().getUser_id())
			loadData();
		else {
			API.getProfile(userid, handler);
		}
	}

	Boolean isBase = false;

	private void loadData() {

		Intent it = null;
		switch (what_user) {
		case 1:
			it = new Intent(this, ActivityProfile.class);
			break;
		case 2:
			it = new Intent(this, ActivityArtworkDetail.class);
			break;
		case 3:
			it = new Intent(this, ActivityProductDetail.class);
			break;
		}
		it.putExtra("user_id", User_id);
		it.putExtra("string_id", String_id);
		startActivity(it);

	}
}
