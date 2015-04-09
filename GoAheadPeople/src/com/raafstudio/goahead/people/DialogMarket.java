package com.raafstudio.goahead.people;

import java.io.File;

import com.raaf.rIO;
import com.raaf.rIntent;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.helper.so;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogMarket extends Activity {

	Button BtSell, BtBuy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		so.apply_image = false;
		setContentView(R.layout.dialog_market);
		BtSell = (Button) findViewById(R.id.BtSell);
		BtBuy = (Button) findViewById(R.id.BtBuy);
		BtSell.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		BtBuy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}
}
