package com.raafstudio.goahead.people.fragment;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog;
import com.raaf.rIO;
import com.raaf.rImaging;
import com.raaf.rSecurity;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.component.TouchView;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

public class FragmentArtworksLayout extends FragmentBase implements
		OnClickListener {
	ImageviewNormal ImgArtwork, Img1, Img2, Img3, Img4, Img5;
	TouchView tv;
	ViewGroup _root;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_artwork_layout, container,
				false);
		_root = (ViewGroup) rView.findViewById(R.id.root);
		ImgArtwork = (ImageviewNormal) rView.findViewById(R.id.ImgArtwork);
		Img1 = (ImageviewNormal) rView.findViewById(R.id.ImgArtLay1);
		Img2 = (ImageviewNormal) rView.findViewById(R.id.ImgArtLay2);
		Img3 = (ImageviewNormal) rView.findViewById(R.id.ImgArtLay3);
		Img4 = (ImageviewNormal) rView.findViewById(R.id.ImgArtLay4);
		Img5 = (ImageviewNormal) rView.findViewById(R.id.ImgArtLay5);
		Img1.setOnClickListener(this);
		Img2.setOnClickListener(this);
		Img3.setOnClickListener(this);
		Img4.setOnClickListener(this);
		Img5.setOnClickListener(this);
		return rView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (so.apply_image && so.requester == 11) {

			// rDialog.SetToast(getActivity(), so.PicturePath);

			File imageFile = new File(so.PicturePath);
			Bitmap bitmap = null;
			double pembanding = 0;
			if (imageFile.exists()) {
				bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
				Boolean isLandscape = (bitmap.getWidth() > bitmap.getHeight());
				if (isLandscape) {
					if ((0.75 * (double) bitmap.getWidth()) > (double) bitmap
							.getHeight()) {
						double newWidth = (4 * (double) bitmap.getHeight()) / 3;
						double newX = ((double) bitmap.getWidth() - newWidth) / 2;
						bitmap = Bitmap.createBitmap(bitmap, (int) newX, 0,
								(int) newWidth, bitmap.getHeight());
					}
					pembanding = (double) bitmap.getHeight()
							/ (double) bitmap.getWidth();
					bitmap = rImaging.ScaleImage(bitmap, 1000,
							(int) (1000 * pembanding));
				} else {
					double newHeight = (double) bitmap.getWidth();
					double newY = ((double) bitmap.getHeight() - newHeight) / 2;
					bitmap = Bitmap.createBitmap(bitmap, 0, (int) newY,
							bitmap.getWidth(), (int) newHeight);

					bitmap = rImaging.ScaleImage(bitmap, 1000, 1000);
				}

				imageFile = new File(so.getFileArtSource());
				if (imageFile.exists())
					imageFile.delete();
				rImaging.SaveImageToFile(bitmap, so.getFileArtSource(),
						CompressFormat.JPEG, 70);
				bitmap.recycle();
			}
			// Drawable d = new BitmapDrawable(getResources(),
			// rImaging.getImageFromFile(filename));
			// ImgArtwork.setBackground(d);

			so.apply_image = false;
			so.requester = 2;
		}
		ImgArtwork.setImageBitmap(rImaging.getImageFromFile(so
				.getFileArtSource()));
		rDialog.CloseProgressDialog();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ImgArtLay1:
			ChangeCategory(Img1);
			Img1.setBackground(getResources().getDrawable(
					R.drawable.btn_art_layout_on));
			tv = new TouchView(getActivity(), getActivity().getResources()
					.getDrawable(R.drawable.layout_typo1_active));

			break;
		case R.id.ImgArtLay2:
			ChangeCategory(Img2);
			Img2.setBackground(getResources().getDrawable(
					R.drawable.btn_art_layout_on));
			tv = new TouchView(getActivity(), getActivity().getResources()
					.getDrawable(R.drawable.layout_typo2_active));
			break;
		case R.id.ImgArtLay3:
			ChangeCategory(Img3);
			Img3.setBackground(getResources().getDrawable(
					R.drawable.btn_art_layout_on));
			tv = new TouchView(getActivity(), getActivity().getResources()
					.getDrawable(R.drawable.layout_typo3_active));
			break;
		case R.id.ImgArtLay4:
			ChangeCategory(Img4);
			Img4.setBackground(getResources().getDrawable(
					R.drawable.btn_art_layout_on));
			tv = new TouchView(getActivity(), getActivity().getResources()
					.getDrawable(R.drawable.layout_typo4_active));
			break;
		case R.id.ImgArtLay5:
			ChangeCategory(Img5);
			Img5.setBackground(getResources().getDrawable(
					R.drawable.btn_art_layout_on));
			tv = new TouchView(getActivity(), getActivity().getResources()
					.getDrawable(R.drawable.layout_typo5_active));
			break;
		}
		tv.setLayoutParams(new LayoutParams(ImgArtwork.getWidth(), ImgArtwork
				.getHeight()));
		// Drawable d = new BitmapDrawable(getResources(),
		// rImaging.getImageFromFile(so.getFileArtSource()));
		// tv.setBackground(d);
		so.apply_image = true;
		_root.removeAllViews();
		_root.addView(ImgArtwork);
		_root.addView(tv);
	}

	private void ChangeCategory(ImageView Img) {

		Img1.setBackground(getResources().getDrawable(
				R.drawable.btn_art_layout_off));
		Img2.setBackground(getResources().getDrawable(
				R.drawable.btn_art_layout_off));
		Img3.setBackground(getResources().getDrawable(
				R.drawable.btn_art_layout_off));
		Img4.setBackground(getResources().getDrawable(
				R.drawable.btn_art_layout_off));
		Img5.setBackground(getResources().getDrawable(
				R.drawable.btn_art_layout_off));
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		if (so.apply_image) {
			tv.Save(so.getFileArtLayout() + "png");
			Bitmap bmp = rImaging.getImageFromFile(so.getFileArtSource());
			Bitmap bmp1 = rImaging.getImageFromFile(so.getFileArtLayout()
					+ "png");
			bmp1 = rImaging.getPreview(bmp1, bmp.getWidth());
			Bitmap bmpov = rImaging.doOverlay(bmp, bmp1, 0, 0);
			bmp.recycle();
			bmp1.recycle();
			rImaging.SaveImageToFile(bmpov, so.getFileArtLayout(),
					CompressFormat.JPEG, 100);
			bmpov.recycle();
		} else {
			rIO.copyFile(so.getFileArtSource(), so.getFileArtLayout());
		}
		super.onPause();
	}
}
