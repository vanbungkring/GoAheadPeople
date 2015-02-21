package com.raafstudio.goahead.people;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.bumptech.glide.util.Util;
import com.raaf.rImaging;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.so;

public class ActivityArtworkPublish extends ActivityBase {

	ImageviewNormal ImgArtwork;
	LinearLayout lay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork_publish);
		ImgArtwork = (ImageviewNormal) findViewById(R.id.ImgArtwork);
		lay = (LinearLayout) findViewById(R.id.LayoutResult);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Bitmap bmp = rImaging.getImageFromFile(so.getFileArtFrame());
		ImgArtwork.setImageBitmap(bmp);
		Drawable d = new BitmapDrawable(getResources(),
				com.raafstudio.goahead.people.helper.Util.getBlurBitmap(bmp));
		lay.setBackground(d);
	}
}