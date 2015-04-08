package com.raafstudio.goahead.people.setting;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jhlabs.image.DilateFilter;
import com.raaf.rDialog;
import com.raaf.rImaging;
import com.raaf.rSecurity;
import com.raaf.io.rFile;
import com.raafstudio.goahead.people.DialogCapture;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.activity.ActivityBase;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

public class ActivitySettingBasic extends ActivityBase {

	EditText EtUserName, EtUserOccup, EtUserLocation, EtUserAbout;
	Button BtSave;
	CircleImageView ImgAvatar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_basic);
		bindToolbar();
		getSupportActionBar().setTitle("Basic Info");
		EtUserName = (EditText) findViewById(R.id.EtUserName);
		EtUserOccup = (EditText) findViewById(R.id.EtUserOccupation);
		EtUserLocation = (EditText) findViewById(R.id.EtUserLocation);
		EtUserAbout = (EditText) findViewById(R.id.EtUserAbout);
		ImgAvatar = (CircleImageView) findViewById(R.id.ImgAvatar);
		((TextView) findViewById(R.id.TvChangeAvatar))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						so.requester = 1;
						startActivity(new Intent(ActivitySettingBasic.this,
								DialogCapture.class));

					}
				});
		((Button) findViewById(R.id.BtSave))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						rFile r = new rFile();
						File f = new File(filename);
						r.setFile(f);
						r.setMimeType("image/jpeg");
						r.setFileName(rSecurity.getMD5(so.getUser()
								.getUser_id() + ""));
						r.setParam("user_avatar");
						API.SettingEditProfile(
								EtUserAbout.getText().toString(), EtUserOccup
										.getText().toString(), EtUserLocation
										.getText().toString(), r, handler);
						rDialog.ShowProgressDialog(ActivitySettingBasic.this,
								"Please Wait", "Updating Basic Info", true);
					}
				});
		so.apply_image = false;
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_setting) {
				API.MenuGetUser(handler);
				rDialog.SetToast(this, so.meta.getErrorDetail());
			} else
				finish();
		} else
			rDialog.SetToast(this, "eror:" + so.meta.getCode() + " - "
					+ so.meta.getErrorDetail());
		rDialog.CloseProgressDialog();
	}

	String filename;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		EtUserName.setText(so.getUser().getFullname());
		EtUserOccup.setText(so.getUser().getJob_profile());
		EtUserLocation.setText(so.getUser().getJob_location());
		EtUserAbout.setText(Html.fromHtml(so.getUser().getBio()));

		if (so.apply_image)
			filename = Util.getDir() + "/"
					+ rSecurity.getMD5(so.getUser().getUser_id() + "")
					+ ".jpeg";
		else
			filename = Util.getDir() + "/"
					+ rSecurity.getMD5(so.getUser().getUser_id() + "");
		ImgAvatar.setImageBitmap(rImaging.getImageFromFile(filename));

	}
}
