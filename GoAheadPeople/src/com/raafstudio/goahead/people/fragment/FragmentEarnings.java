package com.raafstudio.goahead.people.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raafstudio.goahead.people.R;

public class FragmentEarnings extends FragmentBase {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_earnings, container,
				false);
		return rootView;
	}
}
