package com.raafstudio.goahead.people.fragment;
 
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.adapter.DiscoverAdapter;
import com.raafstudio.goahead.people.adapter.NotificationAdapter;
import com.raafstudio.goahead.people.component.ImageviewBox;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

public class FragmentNotification extends FragmentBase {

	ListView list;
	NotificationAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_notifications, container,
				false);
		list = (ListView) rView.findViewById(R.id.ListNotification);
		adapter = new NotificationAdapter(getActivity(),
				R.layout.item_notification, so.getNotifications());
		list.setAdapter(adapter);
		return rView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (so.getNotifications().size() == 0) {
			if (API.cekInet(getActivity()))
				API.getNotifiation(10, 0, handler);
			else {
				((TextView) rView.findViewById(R.id.TvNoNetwork))
						.setVisibility(View.VISIBLE);
				((ProgressBar) rView.findViewById(R.id.pb))
						.setVisibility(View.GONE);
			}
		} else
			adapter.notifyDataSetChanged();
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			adapter.notifyDataSetChanged();
		}
	}

}
