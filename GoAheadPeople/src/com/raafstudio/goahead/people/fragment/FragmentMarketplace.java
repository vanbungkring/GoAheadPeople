package com.raafstudio.goahead.people.fragment;

import java.io.File;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog;
import com.raaf.rSecurity;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityMain;
import com.raafstudio.goahead.people.activity.ActivityProfile;
import com.raafstudio.goahead.people.activity.artwork.ActivityArtwork;
import com.raafstudio.goahead.people.adapter.NotificationAdapter;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.component.CustomScrollView;
import com.raafstudio.goahead.people.component.CustomScrollView.OnScrollViewListener;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

public class FragmentMarketplace extends FragmentBase implements
		OnClickListener {
	CustomScrollView SvMarketplace;
	ObjectAnimator anim1, anim2, anim3, anim4, anim5, anim6;
	CircleImageView Launcher, LauncherShop, LauncherAdd, LauncherEye;
	LinearLayout mListProduct, mListProduct1, mListProduct2;
	ProgressBar Pb1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_marketplace, container,
				false);
		mListProduct = (LinearLayout) rView.findViewById(R.id.LayListProducts);
		mListProduct1 = (LinearLayout) rView
				.findViewById(R.id.LayListProducts1);
		mListProduct2 = (LinearLayout) rView
				.findViewById(R.id.LayListProducts2);
		Pb1 = (ProgressBar) rView.findViewById(R.id.progressBar1);
		SvMarketplace = (CustomScrollView) rView
				.findViewById(R.id.SvMarketplace);
		SvMarketplace.setOnScrollViewListener(new OnScrollViewListener() {

			@Override
			public void onScrollChanged(CustomScrollView scrollView, int x,
					int y, int oldl, int oldt) {
				// We take the last son in the scrollview
				View view = (View) scrollView.getChildAt(scrollView
						.getChildCount() - 1);
				int diff = (view.getBottom() - (scrollView.getHeight() + scrollView
						.getScrollY()));

				// if diff is zero, then the bottom has been reached
				if (diff == 0 && !isshow) {
					// rDialog.SetToast(getActivity(), "end " + x + " - " +
					// oldl);
					if (so.load_more && !isLoadApi) {
						if (lastrequest < so.getMarketplace().size()) {
							lastrequest = so.getMarketplace().size();
							Pb1.setVisibility(View.VISIBLE);

							API.MarketLanding(((ActivityMain) getActivity())
									.getMarketCategory(), 10, lastrequest, "",
									handler);
						}
						isLoadApi = true;
					}
					isshow = true;
				} else {
					isshow = false;
					Pb1.setVisibility(View.GONE);
				}
			}
		});

		InitLauncher();

		return rView;
	}

	int lastrequest = -1;
	boolean isshow = false;
	boolean isLoadApi = false;

	private void InitLauncher() {
		// TODO Auto-generated method stub
		Launcher = (CircleImageView) rView.findViewById(R.id.Launcher);
		LauncherShop = (CircleImageView) rView
				.findViewById(R.id.LauncherMerchant);
		LauncherAdd = (CircleImageView) rView.findViewById(R.id.LauncherAdd);
		LauncherEye = (CircleImageView) rView
				.findViewById(R.id.LauncherDiscover);
		Launcher.setOnClickListener(this);
		LauncherShop.setOnClickListener(this);
		LauncherAdd.setOnClickListener(this);
		LauncherEye.setOnClickListener(this);
		anim1 = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
				R.animator.flipping);
		anim2 = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
				R.animator.flipping);
		anim3 = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
				R.animator.flipping);
		anim1.setTarget(rView.findViewById(R.id.LauncherAdd));
		anim2.setTarget(rView.findViewById(R.id.LauncherMerchant));
		anim3.setTarget(rView.findViewById(R.id.LauncherDiscover));
		anim1.setDuration(500);
		anim2.setDuration(500);
		anim3.setDuration(500);
		anim4 = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
				R.animator.flipping1);
		anim5 = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
				R.animator.flipping1);
		anim6 = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
				R.animator.flipping1);
		anim4.setTarget(rView.findViewById(R.id.LauncherAdd));
		anim5.setTarget(rView.findViewById(R.id.LauncherMerchant));
		anim6.setTarget(rView.findViewById(R.id.LauncherDiscover));
		anim4.setDuration(500);
		anim5.setDuration(500);
		anim6.setDuration(500);

		anim4.addListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				LauncherAdd.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

		});
		anim5.addListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				LauncherShop.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

		});
		anim6.addListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				LauncherEye.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// if (so.getMarketplace().size() == 0 && lastrequest == -1) {
		// lastrequest = so.getMarketplace().size();
		// API.MarketLanding(
		// ((ActivityMain) getActivity()).getMarketCategory(), 10, 0,
		// "", handler);
		// } else {
		// last_i = 0;
		// loadProduct();
		// }
		if (so.getMarketplace().size() == 0)
			Reload();
		else {
			last_i = 0;
			loadProduct();
		}
		if (so.getUserOther().getUser_id() == so.getUser().getUser_id()) {
			if (so.getUserOther().getArtworks().size() == 0)
				API.getProfileArtwork(so.getUserOther().getUser_id(), 100, 0,
						handler);
		} else
			API.getProfile(so.getUser().getUser_id(), handler);
		((ActivityMain) getActivity()).ShowDemo(2);
		Pb1.setVisibility(View.GONE);
	}

	public void Reload() {
		mListProduct1.removeAllViews();
		mListProduct2.removeAllViews();
		so.getMarketplace().clear();
		last_i = 0;
		lastrequest = 0;
		API.MarketLanding(((ActivityMain) getActivity()).getMarketCategory(),
				10, 0, "", handler);
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_marketplace)
				loadProduct();
			else if (so.meta.getModul() == so.modul_base) {
				if (so.getUserOther().getArtworks().size() == 0)
					API.getProfileArtwork(so.getUserOther().getUser_id(), 100,
							0, handler);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Launcher:
			if (LauncherShop.getVisibility() == View.GONE) {
				LauncherAdd.setVisibility(View.VISIBLE);
				LauncherShop.setVisibility(View.VISIBLE);
				LauncherEye.setVisibility(View.VISIBLE);
				anim1.start();
				anim2.start();
				anim3.start();
			} else {
				anim4.start();
				anim5.start();
				anim6.start();
			}
			break;
		case R.id.LauncherMerchant:
			anim4.start();
			anim5.start();
			anim6.start();

			break;
		case R.id.LauncherAdd:
			so.requester = 2;
			so.apply_image = false;
			so.artwork_published = false;
			File imageFile = new File(so.PicturePath);
			if (imageFile.exists())
				imageFile.delete();
			imageFile = new File(so.getFileArtSource());
			if (imageFile.exists())
				imageFile.delete();
			imageFile = new File(Util.getDirArt() + "/"
					+ rSecurity.getMD5("new_art"));
			if (imageFile.exists())
				imageFile.delete();
			startActivity(new Intent(getActivity(), ActivityArtwork.class));

			break;
		case R.id.LauncherDiscover:
			((ActivityMain) getActivity()).ChangeModule(0);
			break;
		default:

			break;
		}
	}

	int last_i = 0;

	private String getUrlMedia(String url) {
		String[] data = url.split("/");
		String retval = "";
		for (int i = 0; i < data.length - 1; i++) {
			retval += data[i] + "/";
		}
		return retval;
	}

	private void loadProduct() {

		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		Boolean isLeft = true;
		if (mListProduct1.getMeasuredHeight() >= mListProduct2
				.getMeasuredHeight())
			isLeft = false;
		for (int i = last_i; i < so.getMarketplace().size(); i++) {
			View v = inflater.inflate(R.layout.item_product, null);
			final ImageviewNormal img = (ImageviewNormal) v
					.findViewById(R.id.ImgProduct);
			final TextView TvProductTitle = (TextView) v
					.findViewById(R.id.TvProductTitle);
			TextView TvProductUser = (TextView) v
					.findViewById(R.id.TvProductUser);
			final TextView TvMissing = (TextView) v
					.findViewById(R.id.TvMissing);
			final ProgressBar PbProduct = (ProgressBar) v
					.findViewById(R.id.PbProduct);
			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// rDialog.SetToast(getActivity(),
					// TvProductTitle.getTag().toString());
					String[] data = TvProductTitle.getTag().toString()
							.split(";");
					((ActivityMain) getActivity()).ShowProduct(
							Integer.parseInt(data[0]), data[1]);
				}
			});
			Glide.with(this)
					.load(getUrlMedia(so.getMarketplace().get(i).getImage())
							+ so.getMarketplace().get(i).getProduct_thumbnail())
					.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.skipMemoryCache(true)
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
							// rDialog.SetToast(getActivity(), arg1);
							TvMissing.setText("missing image");
							img.setVisibility(View.INVISIBLE);
							PbProduct.setVisibility(View.INVISIBLE);
							return false;
						}

					}).placeholder(R.drawable.trans).into(img);
			TvProductTitle.setText(so.getMarketplace().get(i)
					.getProduct_title());
			TvProductTitle.setTag(so.getMarketplace().get(i).getUser_id() + ";"
					+ so.getMarketplace().get(i).getString_id());
			TvProductUser.setText("By "
					+ so.getMarketplace().get(i).getFullname());

			if (isLeft)
				mListProduct1.addView(v);
			else
				mListProduct2.addView(v);
			isLeft = !isLeft;
			last_i++;
		}
		isLoadApi = false;
	}
}
