package com.raafstudio.goahead.people;

import com.raaf.rDialog;
import com.raaf.custom.DemoDialog;
import com.raafstudio.goahead.people.fragment.FragmentDiscover;
import com.raafstudio.goahead.people.fragment.FragmentEarnings;
import com.raafstudio.goahead.people.fragment.FragmentMarketplace;
import com.raafstudio.goahead.people.fragment.FragmentNotification;
import com.raafstudio.goahead.people.fragment.FragmentSetting;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.navdraw.NavigationDrawerCallbacks;
import com.raafstudio.goahead.people.navdraw.NavigationDrawerFragment;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ActivityMain extends ActivityBase implements
		NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private ImageView ToolbarImg;
	private Spinner SpDiscover, SpMarket;
	private LinearLayout LayoutToolbar;

	FragmentDiscover fDiscover;
	FragmentMarketplace fMarketplace;
	FragmentNotification fNotification;
	FragmentEarnings fEarnings;
	FragmentSetting fSetting;

	String[] cmd_discover, cmd_market;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (so.getToken().length() == 0) {
			doLogin();
		} else {

			setContentView(R.layout.activity_main);
			bindToolbar();
			ToolbarImg = (ImageView) findViewById(R.id.ToolbarImg);
			SpDiscover = (Spinner) findViewById(R.id.ToolbarSpDiscover);
			SpMarket = (Spinner) findViewById(R.id.ToolbarSpMarket);
			LayoutToolbar = (LinearLayout) findViewById(R.id.ToolbarLayout);

			getSupportActionBar().setTitle("");
			// ArrayAdapter<CharSequence> adapter =
			// ArrayAdapter.createFromResource(
			// this, R.array.toolbar_spinner, R.layout.my_spinner);

			// SpToolbar.setAdapter(adapter);
			mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
					.findFragmentById(R.id.fragment_drawer);
			mNavigationDrawerFragment.setup(R.id.fragment_drawer,
					(DrawerLayout) findViewById(R.id.drawer), mToolbar);
			ToolbarImg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (mNavigationDrawerFragment.getCurrentPosition() == 0)
						startActivity(new Intent(ActivityMain.this,
								ActivitySearch.class));
					else
						startActivity(new Intent(ActivityMain.this,
								ActivityProduct.class));
				}
			});
			fDiscover = new FragmentDiscover();
			if (savedInstanceState == null)
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.container, fDiscover).commit();
			if (mNavigationDrawerFragment.isDrawerOpen())
				mNavigationDrawerFragment.closeDrawer();
			cmd_discover = getResources().getStringArray(
					R.array.spinner_discover);
			cmd_market = getResources().getStringArray(R.array.spinner_market);
			SpDiscover.setAdapter(new MyAdapter(this, R.layout.my_spinner,
					cmd_discover));
			SpMarket.setAdapter(new MyAdapter(this, R.layout.my_spinner,
					cmd_market));
			SpDiscover.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					if (fDiscover != null) {
						switch (position) {
						case 1:
							fDiscover.LoadData("feed");
							break;
						case 2:
							fDiscover.LoadData("visual-art");
							break;
						case 3:
							fDiscover.LoadData("style");
							break;
						case 4:
							fDiscover.LoadData("photo");
							break;
						default:
							fDiscover.LoadData("");
							break;
						}
					}
					// rDialog.SetToast(ActivityMain.this,
					// SpToolbar.getSelectedItem().toString() + " ; " + id);
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});
			SpMarket.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					if (fMarketplace != null) {
						fMarketplace.Reload();
					}
					// rDialog.SetToast(ActivityMain.this,
					// SpToolbar.getSelectedItem().toString() + " ; " + id);
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	public class MyAdapter extends ArrayAdapter<String> {
		String[] spinnerSubs;

		public MyAdapter(Context ctx, int txtViewResourceId, String[] objects) {
			super(ctx, txtViewResourceId, objects);
			spinnerSubs = objects;
		}

		@Override
		public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
			return getCustomView(position, cnvtView, prnt, R.layout.my_spinner1);
		}

		@Override
		public View getView(int pos, View cnvtView, ViewGroup prnt) {
			return getCustomView(pos, cnvtView, prnt, R.layout.my_spinner);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent, int layout) {
			LayoutInflater inflater = getLayoutInflater();
			View mySpinner = inflater.inflate(layout, parent, false);
			TextView main_text = (TextView) mySpinner
					.findViewById(R.id.TextSpinner);
			main_text.setText(spinnerSubs[position]);

			return mySpinner;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public void ShowDemo() {
		if (!so.getSp().getBoolean("activity_main", false)) {
			DemoDialog dd = new DemoDialog(this, getResources().getDrawable(
					R.drawable.discover_mark));
			dd.show();
			// startActivity(new Intent(ActivityMain.this,
			// ActivityMainDemo.class));
			so.setParamBoolean("activity_main", true);
		}
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);

	}

	int last_id = 0;

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment f = null;
		switch (position) {
		case 0:
			getSupportActionBar().setTitle("");
			LayoutToolbar.setVisibility(View.VISIBLE);
			if (fDiscover == null)
				fDiscover = new FragmentDiscover();
			f = fDiscover;
			SpDiscover.setVisibility(View.VISIBLE);
			SpMarket.setVisibility(View.GONE);
			ToolbarImg
					.setImageResource(R.drawable.abc_ic_search_api_mtrl_alpha);
			break;
		case 1:
			getSupportActionBar().setTitle("Notification");
			LayoutToolbar.setVisibility(View.GONE);
			if (fNotification == null)
				fNotification = new FragmentNotification();
			f = fNotification;
			break;
		case 2:
			getSupportActionBar().setTitle("Earnings");
			LayoutToolbar.setVisibility(View.GONE);
			if (fEarnings == null)
				fEarnings = new FragmentEarnings();
			f = fEarnings;
			break;
		case 3:
			getSupportActionBar().setTitle("Setting");
			LayoutToolbar.setVisibility(View.GONE);
			if (fSetting == null)
				fSetting = new FragmentSetting();
			f = fSetting;
			break;
		case 5:
			getSupportActionBar().setTitle("");
			LayoutToolbar.setVisibility(View.VISIBLE);
			if (fMarketplace == null)
				fMarketplace = new FragmentMarketplace();
			f = fMarketplace;
			SpDiscover.setVisibility(View.GONE);
			SpMarket.setVisibility(View.VISIBLE);
			ToolbarImg
			.setImageResource(R.drawable.ic_action_plus);
			break;
		default:
			rDialog.ConfirmDialog(this, "Sign Out Aplication",
					"Are you sure want to Sign Out ?", "Yes", "No",
					handler_logout);
			break;
		}
		if (f != null)
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, f).commit();
		last_id = position;
	}

	private final Handler handler_logout = new Handler() {
		public void handleMessage(Message msg) {
			rDialog.ShowProgressDialog(getApplicationContext(), "Sign Out",
					"Please wait...", false);
			API.doLogOut(null);
			so.setToken("");
			startActivity(new Intent(ActivityMain.this, ActivityLogin.class));
			finish();
			rDialog.CloseProgressDialog();
		}
	};

	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen())
			mNavigationDrawerFragment.closeDrawer();
		else if (last_id > 0)
			mNavigationDrawerFragment.selectItem(0);
		else
			super.onBackPressed();
	}

	public void ChangeModule(int position) {
		mNavigationDrawerFragment.selectItem(position);
	}

	public int getMarketCategory() {
		return SpMarket.getSelectedItemPosition() + 1;
	}
}
