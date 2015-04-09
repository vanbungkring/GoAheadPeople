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

	TextView TvBasePrice, TvPublishPrice;
	EditText EtProfit;
	Button BtPublish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_sell);
		bindToolbar();
		getSupportActionBar().setTitle("Sell on Marketplace");
		TvBasePrice = (TextView) findViewById(R.id.TvBasePrice);
		TvPublishPrice = (TextView) findViewById(R.id.TvPublishPrice);
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
				String sPrice = TvBasePrice.getText().toString();
				sPrice = sPrice.replace("Rp", "");
				sPrice = sPrice.replace(",", "");
				sPrice = sPrice.replace(".", "");
				sPrice = sPrice.replace(" ", "");
				int basePrice = Integer.parseInt(sPrice);

				TvPublishPrice.setText("Rp. "
						+ String.format("%,d", (basePrice + profit)));
			}
		});
		TvBasePrice.setText("Rp. " + String.format("%,d", 100000));
		TvPublishPrice.setText("Rp. " + String.format("%,d", 100000));
	}
}
