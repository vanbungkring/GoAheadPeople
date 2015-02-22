package com.raafstudio.goahead.people.fragment;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.raaf.rDialog;
import com.raaf.rImaging;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.component.TouchView;
import com.raafstudio.goahead.people.helper.so;

public class FragmentArtworkFrame extends FragmentBase implements
		OnClickListener {
	ImageviewNormal ImgArtwork;
	ImageView ImgPlus, ImgBasic, ImgCustom, ImgCrop;
	String[] listBasic, listCustom, listCrop;
	LinearLayout lg;
	ViewGroup _root;
	Bitmap bmpBackgroud;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_artwork_frame, container,
				false);
		_root = (ViewGroup) rView.findViewById(R.id.root);

		lg = (LinearLayout) rView.findViewById(R.id.LayoutArtGraph);
		ImgArtwork = (ImageviewNormal) rView.findViewById(R.id.ImgArtwork);
		ImgPlus = (ImageView) rView.findViewById(R.id.ImgArtPlus);
		ImgBasic = (ImageView) rView.findViewById(R.id.ImgArtFrameBasic);
		ImgCustom = (ImageView) rView.findViewById(R.id.ImgArtFrameCustom);
		ImgCrop = (ImageView) rView.findViewById(R.id.ImgArtFrameCrop);

		ImgPlus.setOnClickListener(this);
		ImgBasic.setOnClickListener(this);
		ImgCustom.setOnClickListener(this);
		ImgCrop.setOnClickListener(this);
		AssetManager assetManager = getActivity().getAssets();
		try {
			listBasic = assetManager.list("frame/basic");
			listCustom = assetManager.list("frame/custom");
			listCrop = assetManager.list("frame/crop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// for assets/subFolderInAssets add only subfolder name

		// ImgPattern.setOnTouchListener(this);

		return rView;
	}

	@Override
	public void onResume() {
		super.onResume();
		Drawable d = new BitmapDrawable(getResources(),
				rImaging.getImageFromFile(so.getFileArtGraph()));
		ImgArtwork.setBackground(d);
		ImgArtwork.setImageBitmap(rImaging.getImageFromFile(so
				.getFileArtGraph()));
		bmpBackgroud = rImaging.getPreview(
				rImaging.getImageFromFile(so.getFileArtGraph()), 200);
		ChangeCategory(ImgBasic);
		ImgBasic.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_frame_basic_on));
		LoadGraph(2);
		rDialog.CloseProgressDialog();
		so.apply_image = false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ImgArtFrameBasic:
			ChangeCategory(ImgBasic);
			ImgBasic.setImageDrawable(getResources().getDrawable(
					R.drawable.ic_btn_frame_basic_on));
			LoadGraph(1);
			break;
		case R.id.ImgArtFrameCustom:
			ChangeCategory(ImgCustom);
			ImgCustom.setImageDrawable(getResources().getDrawable(
					R.drawable.ic_btn_frame_custom_on));
			LoadGraph(2);
			break;
		case R.id.ImgArtFrameCrop:
			ChangeCategory(ImgCrop);
			ImgCrop.setImageDrawable(getResources().getDrawable(
					R.drawable.ic_btn_frame_crop_on));
			LoadGraph(3);

			break;

		}
	}

	private void ChangeCategory(ImageView Img) {

		ImgBasic.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));
		ImgCustom.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));
		ImgCrop.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));

		ImgBasic.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_frame_basic_off));
		ImgCustom.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_frame_custom_off));
		ImgCrop.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_frame_crop_off));
		Img.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_on));

	}

	int cur_pos, cok;

	private void LoadGraph(int pos) {
		cur_pos = pos;
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		lg.removeAllViews();
		String[] data = null;
		String pat = "art/";
		switch (pos) {
		case 1:
			data = listBasic;
			pat = "frame/basic/";
			break;
		case 2:
			data = listCustom;
			pat = "frame/custom/";
			break;
		case 3:
			data = listCrop;
			pat = "frame/crop/";
			break;

		}
		for (String graph : data) {
			View v = inflater.inflate(R.layout.item_artwork_graphic, null);
			ImageviewNormal img = (ImageviewNormal) v
					.findViewById(R.id.ImgArtGraph);
			img.setImageBitmap(rImaging.getPreview(
					rImaging.getBitmapFromAssets(getActivity(), pat + graph),
					200));
			Drawable d = new BitmapDrawable(getResources(), bmpBackgroud);
			// .getBitmapFromAssets(getActivity(), v.getTag()
			// .toString()));
			img.setBackground(d);

			img.setTag(pat + graph);
			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Drawable d = new BitmapDrawable(getResources(), rImaging
					// .getBitmapFromAssets(getActivity(), v.getTag()
					// .toString()));
					// tv = new TouchView(getActivity(), d);
					//
					// tv.setLayoutParams(new ViewGroup.LayoutParams(
					// ViewGroup.LayoutParams.MATCH_PARENT,
					// ViewGroup.LayoutParams.MATCH_PARENT));
					// _root.removeAllViews();
					// _root.addView(ImgArtwork);
					// _root.addView(tv);
					so.apply_image = true;
					ImgArtwork.setImageBitmap(rImaging.getBitmapFromAssets(
							getActivity(), v.getTag().toString()));
					so.CurrentFrame = v.getTag().toString();
				}
			});
			lg.addView(v);
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

		super.onPause();
	}
}
