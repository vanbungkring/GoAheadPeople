package com.raafstudio.goahead.people;

import cn.Ragnarok.BitmapFilter;

import com.raaf.rColorPicker;
import com.raaf.rDialog;
import com.raaf.rIO;
import com.raaf.rImaging;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.component.TouchView;
import com.raafstudio.goahead.people.helper.so;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ActivityArtworkFilter extends ActivityBase {

	ImageviewNormal ImgArtwork;
	LinearLayout lg;
	Bitmap newBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork_filter);
		bindToolbar();
		TvNext.setVisibility(View.VISIBLE);
		TvNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (so.apply_image) {
					rImaging.SaveImageToFile(newBitmap, so.getFileArtFilter(),
							CompressFormat.JPEG, 70);
					newBitmap.recycle();
				} else
					rIO.copyFile(so.getFileArtFrame(), so.getFileArtFilter());
				startActivity(new Intent(ActivityArtworkFilter.this,
						ActivityArtworkInfo.class));
			}
		});
		getSupportActionBar().setTitle("Edit Filter");
		ImgArtwork = (ImageviewNormal) findViewById(R.id.ImgArtwork);
		lg = (LinearLayout) findViewById(R.id.LayoutArtGraph);
	}

	Bitmap preview;

	@Override
	protected void onResume() {
		super.onResume();
		ImgArtwork.setImageBitmap(rImaging.getImageFromFile(so
				.getFileArtFrame()));
		preview = rImaging.getPreview(
				rImaging.getImageFromFile(so.getFileArtFrame()), 200);
		LoadGraph();
		rDialog.CloseProgressDialog();
		so.apply_image = false;
	}

	private void LoadGraph() {
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		lg.removeAllViews();
		for (int i = 0; i < BitmapFilter.TOTAL_FILTER_NUM; i++) {
			View v = inflater.inflate(R.layout.item_artwork_graphic, null);
			ImageviewNormal img = (ImageviewNormal) v
					.findViewById(R.id.ImgArtGraph);
			img.setImageBitmap(BitmapFilter.changeStyle(preview, i + 1));
			img.setTag(i + 1);
			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					newBitmap = applyStyle(
							Integer.parseInt(v.getTag().toString()),
							rImaging.getImageFromFile(so.getFileArtFrame()));
					ImgArtwork.setImageBitmap(newBitmap);
					so.apply_image = true;
				}
			});
			lg.addView(v);
		}

	}

	private Bitmap applyStyle(int styleNo, Bitmap originBitmap) {
		Bitmap changeBitmap;
		switch (styleNo) {
		case BitmapFilter.AVERAGE_BLUR_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.AVERAGE_BLUR_STYLE, 5); // maskSize, must odd
			break;
		case BitmapFilter.GAUSSIAN_BLUR_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.GAUSSIAN_BLUR_STYLE, 1.2); // sigma
			break;
		case BitmapFilter.SOFT_GLOW_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.SOFT_GLOW_STYLE, 0.6);
			break;
		case BitmapFilter.LIGHT_STYLE:
			int width = originBitmap.getWidth();
			int height = originBitmap.getHeight();
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.LIGHT_STYLE, width / 3, height / 2, width / 2);
			break;
		case BitmapFilter.LOMO_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.LOMO_STYLE,
					(originBitmap.getWidth() / 2.0) * 95 / 100.0);
			break;
		case BitmapFilter.NEON_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.NEON_STYLE, 200, 100, 50);
			break;
		case BitmapFilter.PIXELATE_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.PIXELATE_STYLE, 10);
			break;
		case BitmapFilter.MOTION_BLUR_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.MOTION_BLUR_STYLE, 10, 1);
			break;
		case BitmapFilter.OIL_STYLE:
			changeBitmap = BitmapFilter.changeStyle(originBitmap,
					BitmapFilter.OIL_STYLE, 5);
			break;
		default:
			changeBitmap = BitmapFilter.changeStyle(originBitmap, styleNo);
			break;
		}
		// originBitmap.recycle();
		return changeBitmap;
	}
}
