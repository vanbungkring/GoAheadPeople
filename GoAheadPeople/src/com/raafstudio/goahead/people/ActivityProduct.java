package com.raafstudio.goahead.people;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raaf.rDialog;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Product;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

public class ActivityProduct extends ActivityBase {
	ImageviewNormal ImgProduct;
	String string_id;
	TextView TvProductTitle, TvProductBy, TvProductDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		ImgProduct = (ImageviewNormal) findViewById(R.id.ImgProduct);
		TvProductTitle = (TextView) findViewById(R.id.TvProductTitle);
		TvProductBy = (TextView) findViewById(R.id.TvProductBy);
		TvProductDesc = (TextView) findViewById(R.id.TvProductDesc);
		bindToolbar();
		getSupportActionBar().setTitle("Marketplace");
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
			for (Product p : so.getUserOther().getProducts()) {
				if (p.getString_id().equals(string_id)) {
					Glide.with(this).load(p.getImage()).asBitmap()
							.diskCacheStrategy(DiskCacheStrategy.SOURCE)
							.placeholder(R.drawable.trans).into(ImgProduct);
					TvProductTitle.setText(p.getProduct_title());
					TvProductBy.setText(p.getFullname());
					TvProductDesc.setText(p.getProduct_description());
				}
			}

		}
		rDialog.CloseProgressDialog();
	}
}
