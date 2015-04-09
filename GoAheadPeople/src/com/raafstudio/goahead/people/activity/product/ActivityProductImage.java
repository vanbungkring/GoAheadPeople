package com.raafstudio.goahead.people.activity.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raaf.rDialog;
import com.raafstudio.goahead.people.DialogCapture;
import com.raafstudio.goahead.people.DialogMarket;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityBase;
import com.raafstudio.goahead.people.component.ImageviewBox;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Artwork;
import com.raafstudio.goahead.people.setting.ActivitySettingBasic;

public class ActivityProductImage extends ActivityBase {
	LinearLayout mListArtwork;
	ImageviewNormal ImgProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_image);
		bindToolbar();
		TvNext.setVisibility(View.VISIBLE);
		getSupportActionBar().setTitle(getIntent().getStringExtra("template"));
		mListArtwork = (LinearLayout) findViewById(R.id.ArtLayout);
		ImgProduct = (ImageviewNormal) findViewById(R.id.ImgProduct);
		loadArtwork();
		TvNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ActivityProductImage.this,
						DialogMarket.class));

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (so.product_published)
			finish();
	}

	private void loadArtwork() {
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mListArtwork.removeAllViews();
		View v = null;
		int i = 0;
		for (Artwork art : so.getUserOther().getArtworks()) {
			if (i == 0)
				Glide.with(ActivityProductImage.this).load(art.getImage_ori())
						.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.into(ImgProduct);
			i++;
			v = inflater.inflate(R.layout.item_artwork, null);
			ImageviewBox img = (ImageviewBox) v.findViewById(R.id.ImgArtwork);
			final View vi = (View) v.findViewById(R.id.view1);
			vi.setTag(art.getImage_ori());
			Glide.with(this).load(art.getImage()).asBitmap()
					.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
			final String sid = art.getString_id();
			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// rDialog.SetToast(ActivityProductImage.this, vi.getTag()
					// .toString());
					Glide.with(ActivityProductImage.this)
							.load(vi.getTag().toString()).asBitmap()
							.diskCacheStrategy(DiskCacheStrategy.SOURCE)
							.into(ImgProduct);
				}
			});
			mListArtwork.addView(v);
		}
	}
}
