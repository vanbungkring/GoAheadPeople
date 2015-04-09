package com.raafstudio.goahead.people.activity.product;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raaf.rDialog;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.drawable;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.activity.ActivityBase;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Product;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityProductDetail extends ActivityBase {
	ImageviewNormal ImgProduct;
	String string_id;
	TextView TvProductTitle, TvProductBy, TvProductDesc;
	Button BtWant, BtBuy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		ImgProduct = (ImageviewNormal) findViewById(R.id.ImgProduct);
		TvProductTitle = (TextView) findViewById(R.id.TvProductTitle);
		TvProductBy = (TextView) findViewById(R.id.TvProductBy);
		TvProductDesc = (TextView) findViewById(R.id.TvProductDesc);
		BtWant = (Button) findViewById(R.id.BtWant);
		BtBuy = (Button) findViewById(R.id.BtBuy);
		bindToolbar();
		getSupportActionBar().setTitle("Marketplace");
		TvProductTitle.setTag("");
		BtWant.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TvProductTitle.getTag().toString().length() > 0)
					API.MarketLike(TvProductTitle.getTag().toString(), handler);
			}
		});
		BtBuy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		string_id = getIntent().getStringExtra("string_id");
		API.MarketProduct(string_id, handler);
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getMode() == 2) {
				for (Product p : so.getUserOther().getProducts()) {
					if (p.getString_id().equals(string_id)) {
						Glide.with(this).load(p.getImage()).asBitmap()
								.diskCacheStrategy(DiskCacheStrategy.SOURCE)
								.placeholder(R.drawable.trans).into(ImgProduct);
						TvProductTitle.setTag(p.getEncrypted_id());
						TvProductTitle.setText(p.getProduct_title());
						TvProductBy.setText(p.getFullname());
						TvProductDesc.setText(p.getProduct_description());
						BtWant.setText(p.getIs_want().equals("NO") ? "Want"
								: "Unwant");
					}
				}
			} else if (so.meta.getMode() == 3) {

				BtWant.setText(BtWant.getText().toString().equals("Unwant") ? "Want"
						: "Unwant");
			//	rDialog.SetToast(ActivityProductDetail.this, so.meta.getJson());
			}
		}
		rDialog.CloseProgressDialog();
	}
}
