package com.raafstudio.goahead.people;

import java.io.File;

import com.raaf.rIO;
import com.raaf.rIntent;
import com.raafstudio.goahead.people.activity.product.ActivityProductBuy;
import com.raafstudio.goahead.people.activity.product.ActivityProductImage;
import com.raafstudio.goahead.people.activity.product.ActivityProductSell;
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
	int template_id, template_type;
	String template, template_type_name;

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
				Intent it = new Intent(DialogMarket.this,
						ActivityProductSell.class);
				it.putExtra("template", template);
				it.putExtra("template_type_name", template_type_name);
				it.putExtra("template_type", template_type);
				it.putExtra("template_id", template_id);
				startActivity(it);

				finish();
			}
		});
		BtBuy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(DialogMarket.this,
						ActivityProductBuy.class);
				it.putExtra("template", template);
				it.putExtra("template_type_name", template_type_name);
				it.putExtra("template_type", template_type);
				it.putExtra("template_id", template_id);
				startActivity(it);
				finish();
			}
		});
		template = getIntent().getStringExtra("template");
		template_type_name = getIntent().getStringExtra("template_type_name");
		template_type = getIntent().getIntExtra("template_type", 0);
		template_id = getIntent().getIntExtra("template_id", 0);
	}
}
