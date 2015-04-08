package com.raafstudio.goahead.people.activity.artwork;
 
import com.raaf.rIO;
import com.raaf.rImaging; 
import com.raafstudio.goahead.people.DialogCapture;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.drawable;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.activity.ActivityBase;
import com.raafstudio.goahead.people.fragment.FragmentArtworkFrame;
import com.raafstudio.goahead.people.fragment.FragmentArtworkGraph;
import com.raafstudio.goahead.people.fragment.FragmentArtworksLayout;
import com.raafstudio.goahead.people.fragment.FragmentDiscover;
import com.raafstudio.goahead.people.fragment.FragmentNotification;
import com.raafstudio.goahead.people.fragment.FragmentSetting;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;
import com.raaf.rDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class ActivityArtwork extends ActivityBase   {
	private FragmentTabHost myTabHost; 
	Boolean newArt = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork);
		bindToolbar();
		TvNext.setVisibility(View.VISIBLE);
		TvNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Util.showProgres(ActivityArtwork.this);
				if (myTabHost.getCurrentTab() == 2) {
					 
					if (so.apply_image) {
						Bitmap bmp = rImaging.getImageFromFile(so
								.getFileArtGraph());
						Bitmap bmp1 = rImaging.getBitmapFromAssets(
								ActivityArtwork.this, so.CurrentFrame);
						bmp1 = rImaging.getPreview(bmp1, bmp.getWidth());
						Bitmap bmpov = rImaging.doOverlay(bmp, bmp1, 0, 0);
						bmp.recycle();
						bmp1.recycle();
						rImaging.SaveImageToFile(bmpov, so.getFileArtFrame(),
								CompressFormat.JPEG, 100);
						bmpov.recycle();
					} else
						rIO.copyFile(so.getFileArtGraph(), so.getFileArtFrame());
					startActivity(new Intent(ActivityArtwork.this,
							ActivityArtworkFilter.class));
				} else
					myTabHost.setCurrentTab(myTabHost.getCurrentTab() + 1);
			}
		});
		 
		getSupportActionBar().setTitle("Edit Layout");

		myTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		myTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		myTabHost.addTab(
				myTabHost.newTabSpec("tab1").setIndicator(
						"",
						getResources().getDrawable(
								R.drawable.ic_btn_edit_layout)),
				FragmentArtworksLayout.class, null);
		myTabHost.addTab(
				myTabHost.newTabSpec("tab2").setIndicator(
						"",
						getResources().getDrawable(
								R.drawable.ic_btn_add_graphic)),
				FragmentArtworkGraph.class, null);
		myTabHost.addTab(
				myTabHost.newTabSpec("tab3")
						.setIndicator(
								"",
								getResources().getDrawable(
										R.drawable.ic_btn_add_frame)),
				FragmentArtworkFrame.class, null);
		TabWidget widget = myTabHost.getTabWidget();
		for (int i = 0; i < widget.getChildCount(); i++) {
			View v = widget.getChildAt(i);

			TextView tv = (TextView) v.findViewById(android.R.id.title);
			if (tv == null) {
				continue;
			}
			v.setBackgroundResource(R.drawable.tab_bg);
		}
		myTabHost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Util.showProgres(ActivityArtwork.this);
			}
		});
		myTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
			
				if (tabId.equals("tab1")) {
					getSupportActionBar().setTitle("Edit Layout");
				} else if (tabId.equals("tab2")) {
					getSupportActionBar().setTitle("Add Graphic");
				} else {
					getSupportActionBar().setTitle("Add Frame");

				}
				rDialog.CloseProgressDialog();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (so.artwork_published){
			finish();
			return;
		}
		if (so.requester == 12)
			finish();
		else if (so.requester == 11) {
			newArt = false;

		} else if (myTabHost.getCurrentTab() == 0) {
			so.requester = 2;
			startActivity(new Intent(ActivityArtwork.this, DialogCapture.class));

		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (myTabHost.getCurrentTab() == 0)
			return super.onOptionsItemSelected(menuItem);
		else {
			if (menuItem.getItemId() == android.R.id.home) {
				myTabHost.setCurrentTab(myTabHost.getCurrentTab() - 1);
				return true;
			} else
				return super.onOptionsItemSelected(menuItem);
		}
	}

	@Override
	public void onBackPressed() {
		if (myTabHost.getCurrentTab() == 0) {
			if (!newArt) {
				so.requester = 2;
				startActivity(new Intent(ActivityArtwork.this,
						DialogCapture.class));
			} else
				super.onBackPressed();
		} else {
			myTabHost.setCurrentTab(myTabHost.getCurrentTab() - 1);
		}
	}

	 
}
