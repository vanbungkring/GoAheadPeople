package com.raafstudio.goahead.people.activity.product;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityBase;
import com.raafstudio.goahead.people.helper.so;

public class ActivityProductPublished extends ActivityBase {

	Button BtPublished;
	ImageView ImgProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_published);
		so.product_published = true;
		ImgProduct = (ImageView) findViewById(R.id.ImgProduct);
		BtPublished = (Button) findViewById(R.id.BtPublished);
		BtPublished.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Glide.with(this)
				.load(so.getUserOther().getArtworks().get(0).getImage_ori())
				.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(ImgProduct);
	}
}
