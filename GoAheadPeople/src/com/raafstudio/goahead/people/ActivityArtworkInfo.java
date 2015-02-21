package com.raafstudio.goahead.people;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;

import com.raaf.rDialog;
import com.raaf.rImaging;
import com.raaf.rSecurity;
import com.raaf.io.rFile;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;

public class ActivityArtworkInfo extends ActivityBase {
 
	EditText EtTitle, EtDesc;
	Spinner SpType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork_info);
		bindToolbar();
		TvNext.setVisibility(View.VISIBLE);
		TvNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Publish();
			}
		});
		TvNext.setText("Publish");
		getSupportActionBar().setTitle("Edit Info");
		EtTitle = (EditText) findViewById(R.id.EtArtworkTitle);
		EtDesc = (EditText) findViewById(R.id.EtArtworkDesc);
		SpType = (Spinner) findViewById(R.id.SpCategory);

	}

	protected void Publish() {
		// TODO Auto-generated method stub
		rFile file = new rFile();
		file.setFile(new File(so.getFileArtFilter()));
		file.setParam("content");
		file.setMimeType("image/jpeg");
		file.setFileName(rSecurity.getMD5(so.getUser().getUser_id() + ""));
		String type = "STYLE";
		switch (SpType.getSelectedItemPosition()) {
		case 0:
			type = "VA";
			break;
		case 1:
			type = "PHOTO";
			break;
		}
		API.DiscoverPostArtwork(file, EtTitle.getText().toString(), EtDesc
				.getText().toString(), type, handler);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			startActivity(new Intent(ActivityArtworkInfo.this,
					ActivityArtworkPublish.class));
			finish();
		} else
			rDialog.SetToast(ActivityArtworkInfo.this,
					so.meta.getCode() + " : " + so.meta.getErrorDetail()
							+ "\n\n" + so.meta.getJson());
	}
}