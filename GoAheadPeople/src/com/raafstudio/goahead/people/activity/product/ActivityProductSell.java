package com.raafstudio.goahead.people.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityBase;

public class ActivityProductSell extends ActivityBase {

	TextView TvBasePrice, TvPublishPrice, TvTemplateName;
	EditText EtProfit;
	Button BtPublish;
	int template_id, template_type;
	String template, template_type_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_sell);
		bindToolbar();
		getSupportActionBar().setTitle("Sell on Marketplace");
		TvBasePrice = (TextView) findViewById(R.id.TvBasePrice);
		TvPublishPrice = (TextView) findViewById(R.id.TvPublishPrice);
		TvTemplateName = (TextView) findViewById(R.id.TvTemplateName);
		EtProfit = (EditText) findViewById(R.id.EtProfit);
		BtPublish = (Button) findViewById(R.id.BtPublish);
		BtPublish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ActivityProductSell.this,
						ActivityProductPublished.class));
				finish();
			}
		});
		EtProfit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				int profit = 0;
				if (s.toString().length() > 0)
					profit = Integer.parseInt(s.toString());
				else
					EtProfit.setText("0");

				TvPublishPrice.setText("Rp. "
						+ String.format("%,d", (basePrice + profit)));
			}
		});

	}

	int basePrice = 0;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		template = getIntent().getStringExtra("template");
		template_type_name = getIntent().getStringExtra("template_type_name");
		template_type = getIntent().getIntExtra("template_type", 0);
		template_id = getIntent().getIntExtra("template_id", 0);
		TvTemplateName.setText(template_type_name);
		switch (template_id) {
		case 1:
			basePrice = 100000;
			
			break;

		case 2:
			switch (template_type) {
			case 0:
			case 1:
			case 3:
				basePrice = 200000;
				break;
			case 2:
			case 4:
			case 5:
			case 6:
				basePrice = 150000;
				break;
			case 7:
				basePrice = 1750000;
				break;
			}
			break;
		case 3:
			switch (template_type) {
			case 0:
			case 1:
				basePrice = 250000;
				break;
			case 2:
			case 3:
				basePrice = 150000;
				break;
			}
			break;
		case 4:
			switch (template_type) {
			case 0:
				basePrice = 250000;
				break;
			case 1:
				basePrice = 200000;
				break;
			}
			break;
		case 5:
			basePrice = 130000;
			break;
		case 6:
			basePrice = 125000;
			break;
		}
		TvBasePrice.setText("Rp. " + String.format("%,d", basePrice));
		TvPublishPrice.setText("Rp. " + String.format("%,d", basePrice));
	}
}
