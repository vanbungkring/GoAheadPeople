package com.raafstudio.goahead.people.activity;

import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.fragment.FragmentBase;
import com.raafstudio.goahead.people.fragment.FragmentBoarding;
import com.raafstudio.goahead.people.helper.so;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivitySplash extends FragmentActivity {
	Button BtRegister, BtLogin;
	ViewPager mViewPager;
	SectionsPagerAdapter mSectionsPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boarding);
		mViewPager = (ViewPager) findViewById(R.id.VpPanel);
		BtLogin = (Button) findViewById(R.id.BtLogin);
		BtRegister = (Button) findViewById(R.id.BtRegister);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager.setAdapter(mSectionsPagerAdapter);

		BtLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(ActivitySplash.this,
						ActivityLogin.class));
			}
		});
		BtRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (so.getToken().length() > 0) {
			startActivity(new Intent(ActivitySplash.this, ActivityMain.class));
			finish();
		}
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(
				android.support.v4.app.FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			FragmentBoarding fragment = new FragmentBoarding(position);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String tittle = "";

			return tittle;
		}
	}
}
