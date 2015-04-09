package com.raafstudio.goahead.people.activity.product;

import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityBase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ActivityProductTemplate extends ActivityBase implements
		OnClickListener {
	ImageView ImgCanvas, ImgSmartphone, ImgIpad, ImgMac, ImgPillow, ImgBag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_template);
		bindToolbar();
		TvNext.setVisibility(View.VISIBLE);
		getSupportActionBar().setTitle("Choose Product");
		ImgCanvas = (ImageView) findViewById(R.id.ImgCanvas);
		ImgSmartphone = (ImageView) findViewById(R.id.ImgSmartphone);
		ImgIpad = (ImageView) findViewById(R.id.ImgIpad);
		ImgMac = (ImageView) findViewById(R.id.ImgMac);
		ImgPillow = (ImageView) findViewById(R.id.ImgPillow);
		ImgBag = (ImageView) findViewById(R.id.ImgBag);

		ImgCanvas.setOnClickListener(this);
		ImgSmartphone.setOnClickListener(this);
		ImgIpad.setOnClickListener(this);
		ImgMac.setOnClickListener(this);
		ImgPillow.setOnClickListener(this);
		ImgBag.setOnClickListener(this);
		TvNext.setOnClickListener(this);
	}

	String template_name = "";

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ImgCanvas:
			setActive(1);
			template_name = "Canvas";
			break;
		case R.id.ImgSmartphone:
			template_name = "Smartphone";
			setActive(2);
			break;
		case R.id.ImgIpad:
			template_name = "iPad";
			setActive(3);
			break;
		case R.id.ImgMac:
			template_name = "Macbook";
			setActive(4);
			break;
		case R.id.ImgPillow:
			template_name = "Pillow";
			setActive(5);
			break;
		case R.id.ImgBag:
			template_name = "Tote Bag";
			setActive(6);
			break;
		default:
			Intent it = new Intent(this, ActivityProductImage.class);
			it.putExtra("template", template_name);
			startActivity(it);
			break;
		}
	}

	private void setActive(int pos) {
		ImgCanvas.setImageResource(R.drawable.template_canvas);
		ImgSmartphone.setImageResource(R.drawable.template_smartphone);
		ImgIpad.setImageResource(R.drawable.template_ipad);
		ImgMac.setImageResource(R.drawable.template_mac);
		ImgPillow.setImageResource(R.drawable.template_pillow);
		ImgBag.setImageResource(R.drawable.template_bag);
		ImgCanvas.setBackgroundColor(R.color.white);
		ImgSmartphone.setBackgroundColor(R.color.white);
		ImgIpad.setBackgroundColor(R.color.white);
		ImgMac.setBackgroundColor(R.color.white);
		ImgPillow.setBackgroundColor(R.color.white);
		ImgBag.setBackgroundColor(R.color.white);
		switch (pos) {
		case 2:
			ImgSmartphone
					.setImageResource(R.drawable.template_smartphone_active);
			ImgSmartphone.setBackgroundColor(R.color.red_background_product);
			break;
		case 3:
			ImgIpad.setImageResource(R.drawable.template_ipad_active);
			ImgIpad.setBackgroundColor(R.color.red_background_product);
			break;
		case 4:
			ImgMac.setImageResource(R.drawable.template_mac_active);
			ImgMac.setBackgroundColor(R.color.red_background_product);
			break;
		case 5:
			ImgPillow.setImageResource(R.drawable.template_pillow_active);
			ImgPillow.setBackgroundColor(R.color.red_background_product);
			break;
		case 6:
			ImgBag.setImageResource(R.drawable.template_bag_active);
			ImgBag.setBackgroundColor(R.color.red_background_product);
			break;
		default:
			ImgCanvas.setImageResource(R.drawable.template_canvas_active);
			ImgCanvas.setBackgroundColor(R.color.red_background_product);
			break;
		}
	}
}
