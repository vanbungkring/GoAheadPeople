package com.raafstudio.goahead.people.activity;

import java.text.NumberFormat;
import java.util.Locale;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog; 
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.drawable;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.adapter.ArtworkAdapter;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.component.ImageviewBox;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivityProfile extends ActivityBase {

	CircleImageView ImgAvatar;
	Button BtUserArtworks, BtUserProducts, BtFollow;
	ImageView ImgAvatarBackground;
	GridView GvArtworks;
	ArtworkAdapter ArtAdapter;
	LinearLayout mListArtwork, mListProduct, mListProduct1, mListProduct2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_profile);
		bindToolbar();
		getSupportActionBar().setTitle("Profile");
		ImgAvatar = (CircleImageView) findViewById(R.id.ImgAvatar);
		ImgAvatarBackground = (ImageView) findViewById(R.id.ImgAvatarBackground);
		BtUserArtworks = (Button) findViewById(R.id.BtUserArtworks);
		BtUserProducts = (Button) findViewById(R.id.BtUserProducts);
		BtFollow = (Button) findViewById(R.id.BtUserFollow);
		BtUserArtworks.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				mListArtwork.setVisibility(View.VISIBLE);
				mListProduct.setVisibility(View.GONE);
			}
		});
		BtUserProducts.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				mListArtwork.setVisibility(View.GONE);
				mListProduct.setVisibility(View.VISIBLE);
			}
		});
		BtFollow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				API.doProfileFollow(so.getUserOther().getProfile_id(), handler);
			}
		});
		mListArtwork = (LinearLayout) findViewById(R.id.LayListArtworks);
		mListProduct = (LinearLayout) findViewById(R.id.LayListProducts);
		mListProduct1 = (LinearLayout) findViewById(R.id.LayListProducts1);
		mListProduct2 = (LinearLayout) findViewById(R.id.LayListProducts2);
		mListArtwork.setVisibility(View.VISIBLE);
		mListProduct.setVisibility(View.GONE);
		User_id = getIntent().getIntExtra("user_id", 0);
		if (so.getUser().getUser_id() == User_id)
			((Button) findViewById(R.id.BtUserFollow)).setVisibility(View.GONE);
		((TextView) findViewById(R.id.TvUserFullName)).setText(so
				.getUserOther().getFullname());
		((TextView) findViewById(R.id.TvUserJob)).setText(so.getUserOther()
				.getJob_profile());
		((TextView) findViewById(R.id.TvUserJobLocation)).setText(so
				.getUserOther().getJob_location());
		((TextView) findViewById(R.id.TvUserFollower)).setText(NumberFormat
				.getNumberInstance(Locale.US).format(
						so.getUserOther().getFollowers())
				+ " followers");
		((TextView) findViewById(R.id.TvUserFollowing)).setText(NumberFormat
				.getNumberInstance(Locale.US).format(
						so.getUserOther().getFollowing())
				+ " following");
		if (so.getUserOther().getIs_follow() == 1)
			BtFollow.setText("UNFOLLOW");
		else
			BtFollow.setText("FOLLOW");
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_profile) {
				switch (so.meta.getMode()) {
				case 2:
					loadArtwork();
					break;
				case 3:
					loadProduct();
					break;
				case 6:
					if (BtFollow.getText().toString().equals("FOLLOW"))
						BtFollow.setText("UNFOLLOW");
					else
						BtFollow.setText("FOLLOW");
					break;
				}

			}
		} else
			rDialog.SetToast(this,
					so.meta.getCode() + " - " + so.meta.getErrorDetail());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (so.getToken() != "") {
			if (User_id > 0)
				API.getProfileArtwork(User_id, 100, 0, handler);
			else
				loadArtwork();
			if (User_id > 0)
				API.getProfileProduct(User_id, 100, 0, handler);
			else
				loadProduct();
			Glide.with(this).load(so.getUserOther().getUser_avatar())
					.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.into(new SimpleTarget<Bitmap>(200, 200) {
						@Override
						public void onResourceReady(Bitmap bitmap,
								GlideAnimation anim) {
							ImgAvatar.setImageBitmap(bitmap);
							ImgAvatarBackground.setImageBitmap(Util
									.getBlurBitmap(bitmap));

						}
					});
			rDialog.CloseProgressDialog();
		} else
			finish();
	}

	int artloop = 0;

	private void loadArtwork() {
		BtUserArtworks.setText(so.getUserOther().getArtworks().size()
				+ " ARTWORKS");
		artloop = 0;
		mListArtwork.removeAllViews();
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = null;
		for (int i = 0; i < so.getUserOther().getArtworks().size(); i++) {
			final String sid = so.getUserOther().getArtworks().get(i)
					.getString_id();
			switch (artloop) {
			case 0:
				v = inflater.inflate(R.layout.item_profil_artwork, null);
				ImageviewBox img1 = (ImageviewBox) v
						.findViewById(R.id.ImgArtwork1);

				Glide.with(this)
						.load(so.getUserOther().getArtworks().get(i).getImage())
						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.placeholder(R.drawable.trans).crossFade().into(img1);
				img1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ShowArt(User_id, sid);
					}
				});
				break;
			case 1:
				ImageviewBox img2 = (ImageviewBox) v
						.findViewById(R.id.ImgArtwork2);
				// ((ProgressBar) v.findViewById(R.id.ArtPb2))
				// .setVisibility(View.VISIBLE);
				Glide.with(this)
						.load(so.getUserOther().getArtworks().get(i).getImage())
						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.placeholder(R.drawable.trans).crossFade().centerCrop()
						.into(img2);
				img2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ShowArt(User_id, sid);
					}
				});
				break;
			case 2:
				ImageviewBox img3 = (ImageviewBox) v
						.findViewById(R.id.ImgArtwork3);
				// ((ProgressBar) v.findViewById(R.id.ArtPb3))
				// .setVisibility(View.VISIBLE);
				Glide.with(this)
						.load(so.getUserOther().getArtworks().get(i).getImage())
						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.placeholder(R.drawable.trans).crossFade().centerCrop()
						.into(img3);
				img3.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						ShowArt(User_id, sid);
					}
				});
				break;
			}
			artloop++;
			if (artloop == 3) {
				artloop = 0;
				mListArtwork.addView(v);
			}
		}
		if (artloop > 0)
			mListArtwork.addView(v);
	}

	private void loadProduct() {
		BtUserProducts.setText(so.getUserOther().getProducts().size()
				+ " PRODUCTS");
		mListProduct1.removeAllViews();
		mListProduct2.removeAllViews();
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Boolean isLeft = true;
		for (int i = 0; i < so.getUserOther().getProducts().size(); i++) {
			final String sid = so.getUserOther().getArtworks().get(i)
					.getString_id();
			View v = inflater.inflate(R.layout.item_product, null);
			ImageviewNormal img = (ImageviewNormal) v
					.findViewById(R.id.ImgProduct);
			TextView TvProductTitle = (TextView) v
					.findViewById(R.id.TvProductTitle);
			TextView TvProductUser = (TextView) v
					.findViewById(R.id.TvProductUser);

			Glide.with(this)
					.load(so.getUserOther().getProducts().get(i)
							.getProduct_thumbnail()).asBitmap()
					.diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.listener(new RequestListener<String, Bitmap>() {

						@Override
						public boolean onResourceReady(Bitmap arg0,
								String arg1, Target<Bitmap> arg2, boolean arg3,
								boolean arg4) {

							return false;
						}

						@Override
						public boolean onException(Exception arg0, String arg1,
								Target<Bitmap> arg2, boolean arg3) {
							// TODO Auto-generated method stub
							rDialog.SetToast(ActivityProfile.this, arg1);
							return false;
						}

					}).placeholder(R.drawable.trans).into(img);
			TvProductTitle.setText(so.getUserOther().getProducts().get(i)
					.getProduct_title());
			TvProductUser.setText("By "
					+ so.getUserOther().getProducts().get(i).getFullname());
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ShowProduct(User_id, sid);
				}
			});
			if (isLeft)
				mListProduct1.addView(v);
			else
				mListProduct2.addView(v);
			isLeft = !isLeft;

		}

	}
}
