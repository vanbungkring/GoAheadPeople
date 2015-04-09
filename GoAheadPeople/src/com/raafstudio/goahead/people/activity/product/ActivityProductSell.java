package com.raafstudio.goahead.people.activity.product;

import android.os.Bundle;
import android.view.View;

import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityBase;

public class ActivityProductSell extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_sell);
		bindToolbar(); 
		getSupportActionBar().setTitle("Sell on Marketplace");
	}
}
