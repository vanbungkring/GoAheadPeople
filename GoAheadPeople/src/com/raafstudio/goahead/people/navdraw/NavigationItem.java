package com.raafstudio.goahead.people.navdraw;

import android.graphics.drawable.Drawable;

/**
 * Created by poliveira on 24/10/2014.
 */
public class NavigationItem {
	private String mText;
	private String mInfo;
	private Drawable mDrawable;

	public NavigationItem(String text, Drawable drawable, String info) {
		mText = text;
		mDrawable = drawable;
		mInfo = info;
	}

	public String getText() {
		return mText;
	}

	public void setText(String text) {
		mText = text;
	}

	public Drawable getDrawable() {
		return mDrawable;
	}

	public void setDrawable(Drawable drawable) {
		mDrawable = drawable;
	}

	public String getInfo() {
		return mInfo;
	}

	public void setInfo(String info) {
		this.mInfo = info;
	}

}
