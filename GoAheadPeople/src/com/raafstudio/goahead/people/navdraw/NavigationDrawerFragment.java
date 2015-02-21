package com.raafstudio.goahead.people.navdraw;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog;
import com.raaf.rImaging;
import com.raaf.rSecurity; 
import com.raafstudio.goahead.people.ActivityMain;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.R.color;
import com.raafstudio.goahead.people.R.drawable;
import com.raafstudio.goahead.people.R.id;
import com.raafstudio.goahead.people.R.layout;
import com.raafstudio.goahead.people.R.string;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.fragment.FragmentBase;
import com.raafstudio.goahead.people.fragment.FragmentDiscover;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

/**
 * Created by poliveira on 24/10/2014.
 */
public class NavigationDrawerFragment extends FragmentBase implements
		NavigationDrawerCallbacks {
	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
	private static final String PREFERENCES_FILE = "my_app_settings"; // TODO:
																		// change
																		// this
																		// to
																		// your
																		// file
	private NavigationDrawerCallbacks mCallbacks;
	private RecyclerView mDrawerList;
	private View mFragmentContainerView, ViewProfile;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private boolean mUserLearnedDrawer;
	private boolean mFromSavedInstanceState;
	private int mCurrentSelectedPosition;

	List<NavigationItem> navigationItems;
	NavigationDrawerAdapter adapter;
	CircleImageView ImgAvatar;
	TextView TvUserName, TvUserPoint;
	ImageView ImgAvatarBackground;
	LinearLayout NavdrawHeader;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_navigation_drawer,
				container, false);
		mDrawerList = (RecyclerView) view.findViewById(R.id.drawerList);
		ImgAvatar = (CircleImageView) view.findViewById(R.id.ImgAvatar);
		ImgAvatarBackground = (ImageView) view
				.findViewById(R.id.ImgAvatarBackground);
		TvUserName = (TextView) view.findViewById(R.id.TvUserFullName);
		TvUserPoint = (TextView) view.findViewById(R.id.TvUserPoint);
		ViewProfile = (View) view.findViewById(R.id.vProfile);
		NavdrawHeader = (LinearLayout) view.findViewById(R.id.NavdrawHeader);
		LinearLayoutManager layoutManager = new LinearLayoutManager(
				getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mDrawerList.setLayoutManager(layoutManager);
		mDrawerList.setHasFixedSize(true);

		navigationItems = getMenu();
		adapter = new NavigationDrawerAdapter(navigationItems);
		adapter.setNavigationDrawerCallbacks(this);
		mDrawerList.setAdapter(adapter);
		if (so.getUser().getUser_id() == 0)
			API.MenuGetUser(handler);
		else
			ShowProfile();
		ViewProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((ActivityMain) getActivity()).ShowProfile(so.getUser()
						.getUser_id());
			}
		});

		return view;
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_menu) {

				ShowProfile();
			}
		}
	}

	private void ShowProfile() {
		((ActivityMain) getActivity()).ShowDemo();
		TvUserName.setText(so.getUser().getFullname());
		TvUserPoint.setText(NumberFormat.getNumberInstance(Locale.US).format(
				so.getUser().getPoint())
				+ " pts");
		navigationItems.get(1).setInfo(so.getUser().getNotifcount() + "");
		adapter.notifyDataSetChanged();
		Glide.with(this).load(so.getUser().getUser_avatar()).asBitmap()
				.listener(new RequestListener<String, Bitmap>() {

					@Override
					public boolean onResourceReady(Bitmap arg0, String arg1,
							Target<Bitmap> arg2, boolean arg3, boolean arg4) {
						String filename = rSecurity.getMD5(so.getUser()
								.getUser_id() + "");
						rImaging.SaveImageToFile(arg0, Util.getDir() + "/"
								+ filename, CompressFormat.JPEG, 100);
						ImgAvatarBackground.setImageBitmap(Util
								.getBlurBitmap(arg0));
						 
						if (NavdrawHeader.getMeasuredHeight() > 0)
							so.setParamInt("menu_header",
									NavdrawHeader.getMeasuredHeight());

						ImgAvatarBackground.getLayoutParams().height = so
								.getParamInt("menu_header", 0);
						return false;
					}

					@Override
					public boolean onException(Exception arg0, String arg1,
							Target<Bitmap> arg2, boolean arg3) {
						// TODO Auto-generated method stub
						return false;
					}
				}).into(ImgAvatar);
		ImgAvatarBackground.getLayoutParams().height = so.getParamInt(
				"menu_header", 0);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUserLearnedDrawer = Boolean.valueOf(readSharedSetting(getActivity(),
				PREF_USER_LEARNED_DRAWER, "false"));
		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState
					.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	public ActionBarDrawerToggle getActionBarDrawerToggle() {
		return mActionBarDrawerToggle;
	}

	public void setActionBarDrawerToggle(
			ActionBarDrawerToggle actionBarDrawerToggle) {
		mActionBarDrawerToggle = actionBarDrawerToggle;
	}

	public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(
				R.color.myPrimaryDarkColor));

		mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),
				mDrawerLayout, toolbar, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded())
					return;
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded())
					return;
				if (!mUserLearnedDrawer) {
					mUserLearnedDrawer = true;
					saveSharedSetting(getActivity(), PREF_USER_LEARNED_DRAWER,
							"true");
				}

				getActivity().invalidateOptionsMenu();
			}
		};

		if (!mUserLearnedDrawer && !mFromSavedInstanceState)
			mDrawerLayout.openDrawer(mFragmentContainerView);

		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mActionBarDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
	}

	public void openDrawer() {
		mDrawerLayout.openDrawer(mFragmentContainerView);
	}

	public void closeDrawer() {
		mDrawerLayout.closeDrawer(mFragmentContainerView);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	public List<NavigationItem> getMenu() {
		List<NavigationItem> items = new ArrayList<NavigationItem>();
		// items.add(new NavigationItem("item 2", getResources().getDrawable(
		// R.drawable.ic_menu_check)));
		items.add(new NavigationItem("Home", null, ""));
		items.add(new NavigationItem("Notification", null, "8"));
		items.add(new NavigationItem("Earnings", null, ""));
		items.add(new NavigationItem("Setting", null, ""));
		items.add(new NavigationItem("Sign Out", null, ""));
		return items;
	}

	public void selectItem(int position) {
		mCurrentSelectedPosition = position;
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
		((NavigationDrawerAdapter) mDrawerList.getAdapter())
				.selectPosition(position);
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null
				&& mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mActionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		selectItem(position);
	}

	public DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	public void setDrawerLayout(DrawerLayout drawerLayout) {
		mDrawerLayout = drawerLayout;
	}

	public static void saveSharedSetting(Context ctx, String settingName,
			String settingValue) {
		SharedPreferences sharedPref = ctx.getSharedPreferences(
				PREFERENCES_FILE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(settingName, settingValue);
		editor.apply();
	}

	public static String readSharedSetting(Context ctx, String settingName,
			String defaultValue) {
		SharedPreferences sharedPref = ctx.getSharedPreferences(
				PREFERENCES_FILE, Context.MODE_PRIVATE);
		return sharedPref.getString(settingName, defaultValue);
	}
}
