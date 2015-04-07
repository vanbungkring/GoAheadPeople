package com.raafstudio.goahead.people.fragment;

import java.io.File;
import java.util.ArrayList;

import com.raaf.rDialog;
import com.raaf.rSecurity;
import com.raafstudio.goahead.people.ActivityArtwork;
import com.raafstudio.goahead.people.ActivityMain;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.adapter.DiscoverAdapter;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Artwork;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragmentDiscover extends FragmentBase implements OnClickListener {
	ObjectAnimator anim1, anim2, anim3, anim4, anim5, anim6;
	CircleImageView Launcher, LauncherShop, LauncherAdd, LauncherEye;
	ListView list;
	DiscoverAdapter adapter;
	Boolean isloading = false;
	private int previousTotal = 0;
	private int currentPage = 0;
	private int visibleThreshold = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_discover, container, false);

		list = (ListView) rView.findViewById(R.id.ListArt);

		View v = inflater.inflate(R.layout.footer_discover, null);
		list.addFooterView(v);
		list.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (isloading) {
					if (totalItemCount > previousTotal) {
						isloading = false;
						previousTotal = totalItemCount;
						currentPage++;
					}
				}
				if (!isloading
						&& (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
					// I load the next page of gigs using a background task,
					// but you can call any function here.
					LoadData(last_key);
					isloading = true;
				}
			}
		});
		adapter = new DiscoverAdapter(getActivity(), R.layout.item_discover,
				so.getDiscoverArtworks(), handler);
		list.setAdapter(adapter);
		InitLauncher();
		
		return rView;
	}

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
		((ActivityMain) getActivity()).ShowDemo(1);
	}

	String last_key = "";

	public void LoadData(String key) {
		if (isAdded()) {
			if (key != last_key)
				so.getDiscoverArtworks().clear();
			if (!API.cekInet(getActivity()))

				((TextView) rView.findViewById(R.id.TvNoNetwork))
						.setVisibility(View.VISIBLE);
			else
				API.DiscoverGet(key, so.getDiscoverArtworks().size(), handler);
			last_key = key;
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
			((ActivityMain) getActivity()).ChangeModule(5);
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
			anim4.start();
			anim5.start();
			anim6.start();
			break;
		default:

			break;
		}
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			adapter.notifyDataSetChanged();
			if (so.meta.getModul() == so.modul_discover) {
				switch (so.meta.getMode()) {
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;
				case 6:
					// rDialog.SetToast(getActivity(), "like " +
					// so.meta.getJson());
					adapter.notifyDataSetChanged();
					break;
				}
			}
		} else
			rDialog.SetToast(getActivity(),
					so.meta.getCode() + ":" + so.meta.getErrorDetail());
	}
}
