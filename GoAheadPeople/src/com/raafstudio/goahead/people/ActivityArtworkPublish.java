package com.raafstudio.goahead.people;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.Ragnarok.BitmapFilter;

import com.bumptech.glide.util.Util;
import com.raaf.rDialog;
import com.raaf.rImaging;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.so;

public class ActivityArtworkPublish extends ActivityBase {

	ImageviewNormal ImgArtwork;
	ImageView ImgBackground;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork_publish);
		ImgArtwork = (ImageviewNormal) findViewById(R.id.ImgArtwork);
		ImgBackground = (ImageView) findViewById(R.id.ImgBackground);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Bitmap bmp = rImaging.getImageFromFile(so.getFileArtFrame());
		ImgArtwork.setImageBitmap(bmp);
		ImgBackground.setImageBitmap(BitmapFilter.changeStyle(bmp,
				BitmapFilter.MOTION_BLUR_STYLE, 10, 1));
		rDialog.CloseProgressDialog();
		so.artwork_published = true;
	}

}