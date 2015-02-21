package com.raafstudio.goahead.people.helper;

import java.util.ArrayList;

import com.raaf.rSecurity;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.model.Artwork;
import com.raafstudio.goahead.people.model.Brand;
import com.raafstudio.goahead.people.model.Meta;
import com.raafstudio.goahead.people.model.Notification;
import com.raafstudio.goahead.people.model.Product;
import com.raafstudio.goahead.people.model.Province;
import com.raafstudio.goahead.people.model.SubBrand;
import com.raafstudio.goahead.people.model.User;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract.Constants;

public class so {
	public static String[] styles = { "GrayScale", "Relief", "Average Blur",
			"Oil Painting", "Neon", "Pixelate", "Old TV", "Invert Color",
			"Block", "Aged photo", "Sharpen", "Light", "Lomo", "HDR",
			"Gaussian Blur", "Soft Glow", "Sketch", "Motion Blur", "Gotham" };

	public static final int modul_base = 0;
	public static final int modul_login = 1;
	public static final int modul_profile = 2;
	public static final int modul_setting = 3;
	public static final int modul_discover = 4;
	public static final int modul_marketplace = 5;
	public static final int modul_store = 6;
	public static final int modul_brand = 7;
	public static final int modul_commercial = 8;
	public static final int modul_menu = 9;
	public static final int modul_notification = 10;

	public static final String PREF_GCM_REG_ID = "PREF_GCM_REG_ID";
	public static final String PREF_APP_VERSION = "PREF_APP_VERSION";
	public static final String GCM_SENDER_ID = "470363429504";
	public static Boolean apply_image = false;
	public static int requester = 0;
	public static String PicturePath = "";
	static ArrayList<Artwork> DiscoverArtworks;
	static ArrayList<Province> Provinces;
	static ArrayList<Brand> Brands;
	static ArrayList<SubBrand> SubBrands;

	static Util util;
	static User user;
	static User user1;
	static ArrayList<Notification> notification;
	public static Meta meta;
	public static Context ctx;
	public static String CurrentFrame;

	public static SharedPreferences getSp() {
		if (ctx == null)
			return null;
		else
			return ctx.getSharedPreferences(ctx.getString(R.string.app_name),
					Activity.MODE_PRIVATE);
	}

	public static String getAppName() {
		if (ctx == null)
			return "";
		else
			return ctx.getString(R.string.app_name);
	}

	public static String getAPIUrl() {
		if (ctx == null)
			return "";
		else
			return ctx.getString(R.string.api_url);
	}

	public static User getUser() {
		if (user == null)
			user = new User();
		return user;
	}

	public static User getUserOther() {
		if (user1 == null)
			user1 = new User();
		return user1;
	}

	public static String getToken() {
		if (ctx == null)
			return "";
		else
			return getSp().getString("token", "");
	}

	public static String getApiUrl() {
		return getAPIUrl();
	}

	public static void setToken(String token) {
		SharedPreferences.Editor editor = getSp().edit();
		editor.putString("token", token);
		editor.commit();
	}

	public static void setParam(String param_name, String value) {
		SharedPreferences.Editor editor = getSp().edit();
		editor.putString(param_name, value);
		editor.commit();
	}

	public static void setParamInt(String param_name, int value) {
		SharedPreferences.Editor editor = getSp().edit();
		editor.putInt(param_name, value);
		editor.commit();
	}

	public static int getParamInt(String param_name, int defValue) {
		if (ctx == null)
			return 0;
		else
			return getSp().getInt(param_name, defValue);
	}

	public static void setParamBoolean(String param_name, Boolean value) {
		SharedPreferences.Editor editor = getSp().edit();
		editor.putBoolean(param_name, value);
		editor.commit();
	}

	public static ArrayList<Artwork> getDiscoverArtworks() {
		if (DiscoverArtworks == null)
			DiscoverArtworks = new ArrayList<Artwork>();
		return DiscoverArtworks;
	}

	public static ArrayList<Province> getProvinces() {
		if (Provinces == null) {
			Provinces = new ArrayList<Province>();
		}
		return Provinces;
	}

	public static ArrayList<Brand> getBrands() {
		if (Brands == null)
			Brands = new ArrayList<Brand>();
		return Brands;
	}

	public static ArrayList<SubBrand> getSubBrands() {
		if (SubBrands == null)
			SubBrands = new ArrayList<SubBrand>();
		return SubBrands;
	}

	public static ArrayList<Notification> getNotifications() {
		if (notification == null)
			notification = new ArrayList<Notification>();
		return notification;
	}

	public static String getFileArtSource() {
		return Util.getDirArt() + "/"
				+ rSecurity.getMD5(so.getUser().getUser_id() + "");
	}

	public static String getFileArtLayout() {
		return Util.getDirArt() + "/"
				+ rSecurity.getMD5(so.getUser().getUser_id() + "layout");
	}

	public static String getFileArtGraph() {
		return Util.getDirArt() + "/"
				+ rSecurity.getMD5(so.getUser().getUser_id() + "graph");
	}

	public static String getFileArtFrame() {
		return Util.getDirArt() + "/"
				+ rSecurity.getMD5(so.getUser().getUser_id() + "frame");
	}

	public static String getFileArtFilter() {
		return Util.getDirArt() + "/"
				+ rSecurity.getMD5(so.getUser().getUser_id() + "filter")
				+ ".jpeg";
	}
}
