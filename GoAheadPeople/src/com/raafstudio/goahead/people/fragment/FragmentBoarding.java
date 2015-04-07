package com.raafstudio.goahead.people.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.adapter.DiscoverAdapter;
import com.raafstudio.goahead.people.helper.so;

public class FragmentBoarding extends FragmentBase {
	ImageView ImgBoarding;
	int Pos = 0;

	public FragmentBoarding(int pos) {
		Pos = pos;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_boarding, container, false);
		ImgBoarding = (ImageView) rView.findViewById(R.id.ImgBoarding);
		switch (Pos) {
		case 1:
			ImgBoarding.setImageResource(R.drawable.boarding_create);
			break;
		case 2:
			ImgBoarding.setImageResource(R.drawable.boarding_market);
			break;

		default:
			ImgBoarding.setImageResource(R.drawable.boarding_discover);
			break;
		}
		return rView;
	}
 
}
