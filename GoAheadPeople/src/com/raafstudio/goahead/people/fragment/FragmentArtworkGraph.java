package com.raafstudio.goahead.people.fragment;

import java.io.IOException;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamByteArrayLoader;
import com.raaf.rDialog;
import com.raaf.rIO;
import com.raaf.rImaging;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.component.ImageviewBox;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.component.TouchView;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Artwork;

public class FragmentArtworkGraph extends FragmentBase implements
		OnClickListener {
	ImageviewNormal ImgArtwork;
	protected static final String msg = "abc";
	ImageView Img1, Img2, Img3, Img4, Img5;
	String[] listBody, listPatern, listShape;
	LinearLayout lg;
	TouchView tv;
	ViewGroup _root;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_artwork_graphic, container,
				false);
		_root = (ViewGroup) rView.findViewById(R.id.root);

		lg = (LinearLayout) rView.findViewById(R.id.LayoutArtGraph);
		ImgArtwork = (ImageviewNormal) rView.findViewById(R.id.ImgArtwork);
		Img1 = (ImageView) rView.findViewById(R.id.ImgArtGraph1);
		Img2 = (ImageView) rView.findViewById(R.id.ImgArtGraph2);
		Img3 = (ImageView) rView.findViewById(R.id.ImgArtGraph3);
		Img4 = (ImageView) rView.findViewById(R.id.ImgArtGraph4);
		Img5 = (ImageView) rView.findViewById(R.id.ImgArtGraph5);
		Img1.setOnClickListener(this);
		Img2.setOnClickListener(this);
		Img3.setOnClickListener(this);
		Img4.setOnClickListener(this);
		Img5.setOnClickListener(this);
		AssetManager assetManager = getActivity().getAssets();
		try {
			listBody = assetManager.list("graph/body");
			listPatern = assetManager.list("graph/pattern");
			listShape = assetManager.list("graph/shape");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// for assets/subFolderInAssets add only subfolder name
		ChangeCategory(Img2);
		Img2.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_pattern_on));
		LoadGraph(2);
		// ImgPattern.setOnTouchListener(this);
		return rView;
	}

	@Override
	public void onResume() {
		super.onResume();
		ImgArtwork.setImageBitmap(rImaging.getImageFromFile(so
				.getFileArtLayout()));
		so.apply_image = false;
		rDialog.CloseProgressDialog();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ImgArtGraph1:
		//	ChangeCategory(Img1);
		//	Img1.setImageDrawable(getResources().getDrawable(
		//			R.drawable.ic_btn_pattern_on));
		//	LoadGraph(1);
			break;
		case R.id.ImgArtGraph2:
			ChangeCategory(Img2);
			Img2.setImageDrawable(getResources().getDrawable(
					R.drawable.ic_btn_pattern_on));
			LoadGraph(2);
			break;
		case R.id.ImgArtGraph3:
			ChangeCategory(Img3);
			Img3.setImageDrawable(getResources().getDrawable(
					R.drawable.ic_btn_body_on));
			LoadGraph(3);

			break;
		case R.id.ImgArtGraph4:
			ChangeCategory(Img4);
			Img4.setImageDrawable(getResources().getDrawable(
					R.drawable.ic_btn_shape_on));
			LoadGraph(4);
			break;
		case R.id.ImgArtGraph5:
			ChangeCategory(Img5);
			break;
		}
	}

	private void ChangeCategory(ImageView Img) {

		//Img1.setBackground(getResources().getDrawable(
		//		R.drawable.btn_art_graph_off));
		Img2.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));
		Img3.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));
		Img4.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));
		Img5.setBackground(getResources().getDrawable(
				R.drawable.btn_art_graph_off));
		//Img1.setImageDrawable(getResources().getDrawable(
		//		R.drawable.ic_btn_pattern_off));
		Img2.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_pattern_off));
		Img3.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_body_off));
		Img4.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_btn_shape_off));
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
			data = listPatern;
			pat = "graph/pattern/";
			break;
		case 2:
			data = listPatern;
			pat = "graph/pattern/";
			break;
		case 3:
			data = listBody;
			pat = "graph/body/";
			break;
		case 4:
			data = listShape;
			pat = "graph/shape/";
			break;
		case 5:
			data = listShape;
			pat = "graph/shape/";
			break;
		}
		for (String graph : data) {
			View v = inflater.inflate(R.layout.item_artwork_graphic, null);
			ImageviewNormal img = (ImageviewNormal) v
					.findViewById(R.id.ImgArtGraph);
			img.setImageBitmap(rImaging.getPreview(
					rImaging.getBitmapFromAssets(getActivity(), pat + graph),
					200));
			if (pos == 2)
				img.setTag("graph/patterns/" + graph);
			else if (pos == 4)
				img.setTag("graph/shapes/" + graph);
			else
				img.setTag(pat + graph);
			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Drawable d = new BitmapDrawable(getResources(), rImaging
							.getBitmapFromAssets(getActivity(), v.getTag()
									.toString()));
					tv = new TouchView(getActivity(), d);
					tv.setLayoutParams(new LayoutParams(ImgArtwork.getWidth(),
							ImgArtwork.getHeight()));
					_root.removeAllViews();
					_root.addView(ImgArtwork);
					_root.addView(tv);
					so.apply_image = true;
					// ImgPattern.setImageBitmap(rImaging.getBitmapFromAssets(
					// getActivity(), v.getTag().toString()));
				}
			});
			lg.addView(v);
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		if (so.apply_image) {
			tv.Save(so.getFileArtGraph() + "png");
			Bitmap bmp = rImaging.getImageFromFile(so.getFileArtLayout());
			Bitmap bmp1 = rImaging.getImageFromFile(so.getFileArtGraph()
					+ "png");
			bmp1 = rImaging.getPreview(bmp1, bmp.getWidth());
			Bitmap bmpov = rImaging.doOverlay(bmp, bmp1, 0, 0);
			bmp.recycle();
			bmp1.recycle();
			rImaging.SaveImageToFile(bmpov, so.getFileArtGraph(),
					CompressFormat.JPEG, 100);
			bmpov.recycle();
		} else
			rIO.copyFile(so.getFileArtLayout(), so.getFileArtGraph());
		super.onPause();
	}
}
