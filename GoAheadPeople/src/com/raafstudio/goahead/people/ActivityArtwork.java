package com.raafstudio.goahead.people;

import com.raaf.rColorPicker;
import com.raaf.rColorPicker.OnColorChangedListener;
import com.raaf.rDialog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class ActivityArtwork extends ActivityBase implements
		OnColorChangedListener {
	private TabHost myTabHost;
	rColorPicker rc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork);
		bindToolbar();
		rc = new rColorPicker(ActivityArtwork.this, this, "", Color.BLACK,
				Color.WHITE);
		getSupportActionBar().setTitle("Edit Layout");
		myTabHost = (TabHost) findViewById(R.id.TabHost01);
		myTabHost.setup();
		TabSpec spec = myTabHost.newTabSpec("tab_1");
		spec.setIndicator("",
				getResources().getDrawable(R.drawable.ic_btn_edit_layout));
		spec.setContent(R.id.onglet1);
		myTabHost.addTab(spec);
		myTabHost.addTab(myTabHost
				.newTabSpec("tab_2")
				.setIndicator(
						"",
						getResources().getDrawable(
								R.drawable.ic_btn_add_graphic))
				.setContent(R.id.Onglet2));
		myTabHost
				.addTab(myTabHost
						.newTabSpec("tab_3")
						.setIndicator(
								"",
								getResources().getDrawable(
										R.drawable.ic_btn_add_frame))
						.setContent(R.id.Onglet3));
		TabWidget widget = myTabHost.getTabWidget();
		for (int i = 0; i < widget.getChildCount(); i++) {
			View v = widget.getChildAt(i);

			// Look for the title view to ensure this is an indicator and not a
			// divider.
			TextView tv = (TextView) v.findViewById(android.R.id.title);
			if (tv == null) {
				continue;
			}
			v.setBackgroundResource(R.drawable.tab_bg);
		}
		myTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("tab_1")) {
					getSupportActionBar().setTitle("Edit Layout");
				} else if (tabId.equals("tab_2")) {
					getSupportActionBar().setTitle("Add Graphic");
				} else {
					getSupportActionBar().setTitle("Add Frame");
					rc.show();
				}
			}
		});
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
		if (myTabHost.getCurrentTab() == 0)
			super.onBackPressed();
		else {

			myTabHost.setCurrentTab(myTabHost.getCurrentTab() - 1);
		}
	}

	@Override
	public void colorChanged(String key, int color) {
		// TODO Auto-generated method stub
		rDialog.SetToast(this, key + " " + color);
	}
}
