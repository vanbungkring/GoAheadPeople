package com.raafstudio.goahead.people.activity.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
	Spinner SpTemplateType;
	TextView TvTemplateType;
	int template_id, template_type = 0;
	String template;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_image);
		bindToolbar();
		TvNext.setVisibility(View.VISIBLE);

		mListArtwork = (LinearLayout) findViewById(R.id.ArtLayout);
		ImgProduct = (ImageviewNormal) findViewById(R.id.ImgProduct);
		SpTemplateType = (Spinner) findViewById(R.id.SpTemplateType);
		TvTemplateType = (TextView) findViewById(R.id.TvTemplateType);
		loadArtwork();
		TvNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ActivityProductImage.this,
						DialogMarket.class);
				it.putExtra("template", template);
				it.putExtra("template_type",
						SpTemplateType.getSelectedItemPosition());
				switch (template_id) {
				case 1:
				case 5:
				case 6:
					it.putExtra("template_type_name", TvTemplateType.getText().toString());
					break;
				case 2:
				case 3:
				case 4:
					it.putExtra("template_type_name", SpTemplateType
							.getSelectedItem().toString());
					break;
				}
				
				it.putExtra("template_id", template_id);
				startActivity(it);

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (so.product_published)
			finish();
		else {
			template = getIntent().getStringExtra("template");
			template_id = getIntent().getIntExtra("template_id", 0);
			getSupportActionBar().setTitle(template);
			if (template_id == 0)
				finish();
			else {
				String[] data = null;
				switch (template_id) {
				case 1:
					TvTemplateType.setText("30 x 30 Canvas");
					break;
				case 2:
					data = new String[] { "Case iPhone 5", "Case iPhone 4",
							"Case Galaxy s4", "Case Galaxy s5",
							"Skin iPhone 5/5s", "Skin iPhone 4",
							"Skin Galaxy s4", "Skin Galaxy s5" };
					break;
				case 3:
					data = new String[] { "Case iPad Mini", "Case iPad 4",
							"Skin iPad Mini", "Skin iPad 4" };
					break;
				case 4:
					data = new String[] { "Skin Macbook 15\"",
							"Skin Macbook 13\"" };
					break;
				case 5:
					TvTemplateType.setText("40 x 40 Pillow");
					break;
				case 6:
					TvTemplateType.setText("40 x 40 Tote Bag");
					break;
				}
				switch (template_id) {
				case 1:
				case 5:
				case 6:
					TvTemplateType.setVisibility(View.VISIBLE);
					break;
				case 2:
				case 3:
				case 4:
					SpTemplateType.setVisibility(View.VISIBLE);
					ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
							this, android.R.layout.simple_spinner_item, data);
					spinnerArrayAdapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					SpTemplateType.setAdapter(spinnerArrayAdapter);
					break;
				}
			}
		}

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
